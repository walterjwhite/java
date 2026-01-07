import React, { useEffect, useRef, useState, useCallback } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

/**
 * Chat component: a React port of examples/spring/.../static/chat.html
 * - Connects to SockJS endpoint at /ws
 * - Subscribes to /topic/public
 * - Publishes to /app/chat.send and /app/chat.join
 *
 * Changed:
 * - Connection is delayed until the username input has been idle for 2s.
 * - The JOIN message is sent immediately on connect when connect was scheduled
 *   after typing (so we don't double-debounce).
 */
export default function Chat() {
  const [connected, setConnected] = useState(false);
  const [messages, setMessages] = useState([]); // {type, from, content, timestamp}
  const [username, setUsername] = useState('');
  const [input, setInput] = useState('');
  const [clientId, setClientId] = useState(null);
  const stompRef = useRef(null);
  const messagesEndRef = useRef(null);

  // Debounce/connect timer refs & last-sent username to avoid duplicate JOINs
  const joinTimerRef = useRef(null);
  const connectTimerRef = useRef(null);
  const lastSentUsernameRef = useRef(null);

  // scroll to bottom when messages change
  useEffect(() => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: 'smooth', block: 'end' });
    }
  }, [messages]);

  const escapeHtml = (unsafe) => {
    return (unsafe || '')
      .toString()
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/\"/g, '&quot;')
      .replace(/'/g, '&#039;');
  };

  const appendSystem = useCallback((text) => {
    setMessages((m) => [...m, { type: 'SYSTEM', content: text, timestamp: Date.now() }]);
  }, []);

  const appendMessage = useCallback((from, content, timestamp) => {
    setMessages((m) => [...m, { type: 'CHAT', from, content, timestamp: timestamp || Date.now() }]);
  }, []);

  // Debounced send JOIN: only actually publishes when called explicitly (used as fallback).
  const scheduleSendJoin = useCallback(() => {
    if (joinTimerRef.current) {
      clearTimeout(joinTimerRef.current);
    }

    joinTimerRef.current = setTimeout(() => {
      const from = (username || 'Anonymous').trim();
      if (lastSentUsernameRef.current === from) return;

      const payload = { from, type: 'JOIN', timestamp: Date.now() };

      if (stompRef.current && stompRef.current.active) {
        stompRef.current.publish({ destination: '/app/chat.join', body: JSON.stringify(payload) });
        lastSentUsernameRef.current = from;
        appendSystem(`Joined as ${from}`);
      } else {
        // If not connected, nothing to do — connect flow will handle join when it happens.
      }
    }, 2000);
  }, [username, appendSystem]);

  // Clear timers on unmount
  useEffect(() => {
    return () => {
      if (joinTimerRef.current) clearTimeout(joinTimerRef.current);
      if (connectTimerRef.current) clearTimeout(connectTimerRef.current);
    };
  }, []);

  // Connect function optionally accepts an initialJoinName. If provided, the client will
  // publish the JOIN immediately on connect using that name (to avoid double debounce).
  const connect = useCallback(
    (initialJoinName) => {
      if (stompRef.current && stompRef.current.active) return;

      const client = new Client({
        webSocketFactory: () => new SockJS('/ws'),
        reconnectDelay: 2000,
        onConnect: (frame) => {
          setConnected(true);
          appendSystem('Connected to server');
          client.subscribe('/topic/public', (message) => {
            try {
              const msg = JSON.parse(message.body);
              if (msg.type === 'SYSTEM' || msg.type === 'JOIN') {
                appendSystem(msg.type === 'JOIN' ? `${msg.from} joined` : msg.content);
              } else {
                appendMessage(msg.from || 'Anonymous', msg.content, msg.timestamp);
              }
            } catch (err) {
              console.error('Invalid message', err);
            }
          });

          // If we were asked to join with a specific name (scheduled connect after typing),
          // publish JOIN immediately with that name. Otherwise, fall back to the debounced join.
          if (initialJoinName) {
            const from = initialJoinName.trim() || 'Anonymous';
            if (lastSentUsernameRef.current !== from) {
              const payload = { from, type: 'JOIN', timestamp: Date.now() };
              client.publish({ destination: '/app/chat.join', body: JSON.stringify(payload) });
              lastSentUsernameRef.current = from;
              appendSystem(`Joined as ${from}`);
            }
          } else {
            // fallback: schedule the join after debounce interval
            scheduleSendJoin();
          }
        },
        onStompError: (frame) => {
          appendSystem('STOMP error: ' + (frame && frame.body ? frame.body : 'unknown'));
        },
        onWebSocketClose: () => {
          setConnected(false);
          appendSystem('Connection closed, retrying...');
        },
        onWebSocketError: () => {
          setConnected(false);
          appendSystem('WebSocket error');
        },
        debug: () => {}
      });

      stompRef.current = client;
      client.activate();
    },
    [appendSystem, appendMessage, scheduleSendJoin]
  );

  // Schedule connect to happen 2s after the user stops typing their username.
  const scheduleConnect = useCallback(() => {
    if (connectTimerRef.current) {
      clearTimeout(connectTimerRef.current);
    }

    connectTimerRef.current = setTimeout(() => {
      // If already connected, do nothing
      if (stompRef.current && stompRef.current.active) return;
      const name = (username || 'Anonymous').trim();
      connect(name);
    }, 2000);
  }, [username, connect]);

  // On mount, schedule an initial connect after 2s of inactivity (so it won't connect immediately while the user types).
  useEffect(() => {
    scheduleConnect();
    return () => {
      if (connectTimerRef.current) clearTimeout(connectTimerRef.current);
    };
    // scheduleConnect already depends on username and connect
  }, [scheduleConnect]);

  // When username changes, reset lastSentUsername only if name is different from last sent
  // and reschedule a connect (we won't connect until the user stops typing for 2s).
  useEffect(() => {
    // If the name being typed is different than last sent, clear lastSentUsernameRef so
    // a new JOIN can be sent after connect.
    if (lastSentUsernameRef.current && lastSentUsernameRef.current !== username.trim()) {
      lastSentUsernameRef.current = null;
    }
    scheduleConnect();
    // we intentionally don't call scheduleSendJoin here to avoid double-debounce
  }, [username, scheduleConnect]);

  // Traditional auto-cleanup of stomp on unmount
  useEffect(() => {
    return () => {
      try {
        if (stompRef.current) stompRef.current.deactivate();
      } catch (e) {
        // ignore
      }
    };
  }, []);

  const sendMessage = useCallback(() => {
    const client = stompRef.current;
    if (!client || !client.active) {
      appendSystem('Not connected.');
      return;
    }
    const from = (username || 'Anonymous').trim();
    const content = (input || '').trim();
    if (!content) return;
    const payload = { from, content, timestamp: Date.now() };
    client.publish({ destination: '/app/chat.send', body: JSON.stringify(payload) });
    setInput('');
  }, [username, input, appendSystem]);

  const forceReconnect = useCallback(() => {
    if (stompRef.current) {
      try { stompRef.current.deactivate(); } catch (e) {}
    }
    setConnected(false);
    // Reset lastSentUsername so JOIN will be re-sent after typing stops
    lastSentUsernameRef.current = null;
    appendSystem('Reconnecting...');
    // schedule a fresh connect using current username (will occur after 2s)
    if (connectTimerRef.current) clearTimeout(connectTimerRef.current);
    scheduleConnect();
  }, [appendSystem, scheduleConnect]);

  const clearMessages = () => setMessages([]);

  return (
    <div className="chat-root" style={{ maxWidth: 1100, margin: '20px auto', padding: '0 20px' }}>
      <header className="chat-header" style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: 12, marginBottom: 12 }}>
        <div>
          <h1 style={{ margin: 0 }}>Chat</h1>
        </div>
        <div style={{ display: 'flex', gap: 12, alignItems: 'center' }}>
          <div
            id="connStatus"
            className={connected ? 'status status-connected' : 'status status-disconnected'}
            aria-live="polite"
            style={{
              fontWeight: 600,
              padding: '6px 10px',
              borderRadius: 10,
              fontSize: '0.9rem',
              background: connected ? '#ecfdf5' : '#fff7ed',
              color: connected ? '#065f46' : '#92400e',
              border: connected ? '1px solid rgba(6,95,70,0.06)' : '1px solid rgba(249,115,22,0.06)'
            }}
          >
            {connected ? 'Connected' : 'Disconnected'}
          </div>
        </div>
      </header>

      <div className="chat-grid" style={{ display: 'grid', gridTemplateColumns: '1fr 340px', gap: 20, alignItems: 'start' }}>
        <section>
          <div id="messages" aria-live="polite" role="log" aria-atomic="false" style={{ minHeight: 200 }}>
            {messages.map((m, i) => {
              if (m.type === 'SYSTEM') {
                return (
                  <div className="msg system" key={i} style={{ padding: 10, marginBottom: 10, borderRadius: 8, background: 'linear-gradient(180deg,#f8fafc,#f1f5f9)', color: '#6b7280' }}>
                    <div className="meta" dangerouslySetInnerHTML={{ __html: escapeHtml(m.content) }} />
                  </div>
                );
              }
              const isMine = (username || 'Anonymous').trim() === (m.from || '').trim();
              const time = m.timestamp ? new Date(m.timestamp).toLocaleTimeString() : new Date().toLocaleTimeString();
              return (
                <div
                  className={`msg ${isMine ? 'mine' : 'other'}`}
                  key={i}
                  style={{
                    padding: 10,
                    marginBottom: 10,
                    borderRadius: 8,
                    background: isMine ? 'linear-gradient(180deg,#eef2ff,#eef2ff)' : 'linear-gradient(180deg,#f7f7ff,#fff)',
                    border: isMine ? '1px solid rgba(59,130,246,0.08)' : '1px solid rgba(15,23,42,0.04)'
                  }}
                >
                  <div className="meta" style={{ color: '#6b7280', fontSize: '0.85rem', marginBottom: 6 }}>
                    <strong dangerouslySetInnerHTML={{ __html: escapeHtml(m.from) }} /> <span style={{ marginLeft: 8, fontSize: '0.85rem', color: '#6b7280' }}>{time}</span>
                  </div>
                  <div dangerouslySetInnerHTML={{ __html: escapeHtml(m.content) }} />
                </div>
              );
            })}
            <div ref={messagesEndRef} />
          </div>

          <div className="composer" aria-label="Send message" style={{ display: 'flex', gap: 8, marginTop: 12 }}>
            <input
              id="username"
              type="text"
              placeholder="Your name"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              style={{ width: 180, padding: 10, borderRadius: 8, border: '1px solid rgba(15,23,42,0.08)' }}
            />
            <input
              id="messageInput"
              type="text"
              placeholder="Type a message…"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={(e) => { if (e.key === 'Enter') sendMessage(); }}
              style={{ flex: 1, padding: 10, borderRadius: 8, border: '1px solid rgba(15,23,42,0.08)' }}
            />
            <button onClick={sendMessage} disabled={!connected} style={{ padding: '10px 12px', borderRadius: 8, fontWeight: 600 }}>
              Send
            </button>
          </div>
        </section>

        <aside>
          <div className="panel" style={{ padding: 14 }}>
            <h3 style={{ margin: 0 }}>Info</h3>
            <p style={{ fontSize: '0.85rem', color: '#6b7280' }}>
              Connected clients subscribe to <code>/topic/public</code>. Messages are sent to <code>/app/chat.send</code>.
            </p>
            <div style={{ marginTop: 12 }}>
              <button onClick={forceReconnect} style={{ marginRight: 8 }}>Reconnect</button>
              <button onClick={clearMessages}>Clear</button>
            </div>
          </div>
        </aside>
      </div>
    </div>
  );
}