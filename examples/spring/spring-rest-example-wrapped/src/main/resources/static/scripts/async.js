const queuedEl = document.getElementById('queuedList');
const runningEl = document.getElementById('runningList');
const allEl = document.getElementById('allList');
const debugEl = document.getElementById('debugInfo');
const statusMsg = document.getElementById('statusMsg');

if (document.getElementById('submitBtn')) {
  document.getElementById('submitBtn').addEventListener('click', () => submitJob());
}
if (document.getElementById('refreshAll')) {
  document.getElementById('refreshAll').addEventListener('click', refresh);
}

function showModal(title, message, okText = 'OK') {
  setStatus(title + (message ? ': ' + message : ''));
  if (message) debugEl.textContent = message;
  return Promise.resolve(true);
}

function showConfirm(title, message, okText = 'Yes', cancelText = 'Cancel') {
  setStatus(title + (message ? ': ' + message : ''));
  if (message) debugEl.textContent = message;
  return Promise.resolve(true);
}

async function submitJob() {
  setStatus('Submitting...');
  const name = document.getElementById('jobName').value || 'job';
  const durationSeconds = parseInt(document.getElementById('jobDur').value || '5', 10);
  try {
    const res = await fetch('/async/submit', {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ name, durationSeconds })
    });
    if (res.status === 429) {
      const json = await res.json();
      await showModal('Rate limited', json.error || 'Too many requests');
    } else if (!res.ok) {
      const txt = await res.text();
      await showModal('Submit failed', txt || 'Unknown error');
    } else {
      const json = await res.json();
      setStatus('Submitted: ' + json.taskId);
      debugEl.textContent = 'Task submitted: ' + json.taskId;
    }
  } catch (e) {
    await showModal('Submit error', e.message || String(e));
  } finally {
    refresh();
    setTimeout(() => setStatus(''), 2000);
  }
}

function renderTasks(list) {
  if (!list || list.length === 0) return '<p class="small"><em>none</em></p>';
  return list.map(t => renderTaskItem(t)).join('');
}

function renderTaskItem(t) {
  const id = escapeHtml(t.id);
  const name = escapeHtml(t.name);
  const status = (t.status || '').toUpperCase();
  const submitted = t.submittedAt ? new Date(t.submittedAt).toLocaleTimeString() : '';
  const started = t.startedAt ? new Date(t.startedAt).toLocaleTimeString() : '';
  const completed = t.completedAt ? new Date(t.completedAt).toLocaleTimeString() : '';
  let badge = '<span class="badge badge-queued">QUEUED</span>';
  if (status === 'RUNNING') badge = '<span class="badge badge-running">RUNNING</span>';
  if (status === 'COMPLETED') badge = '<span class="badge badge-completed">COMPLETED</span>';
  if (status === 'CANCELLED') badge = '<span class="badge badge-cancelled">CANCELLED</span>';
  if (status === 'FAILED') badge = '<span class="badge badge-failed">FAILED</span>';

  const meta = `<div class="task-meta">${submitted}${started ? ' • ' + started : ''}${completed ? ' • ' + completed : ''}</div>`;

  const isActive = status === 'QUEUED' || status === 'RUNNING';
  const refreshBtn = isActive ? `<button class="btn btn-outline" onclick="refreshTask('${id}')">Refresh</button>` : '';
  const cancelBtn = isActive ? `<button class="btn btn-danger" onclick="cancelTask('${id}')">Cancel</button>` : '';

  const actions = `<div class="controls">
    ${refreshBtn}
    ${cancelBtn}
  </div>`;

  return `<div class="task-item">
    <div class="task-content">
      <div class="task-header">
        <div class="task-name">${name}</div>
        ${badge}
      </div>
      <div class="small">${meta}</div>
      <div class="task-id">ID: ${id}</div>
    </div>
    <div class="task-actions">${actions}</div>
  </div>`;
}

function escapeHtml(s){ return (s||'').toString().replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;'); }

async function refresh() {
  try {
    const [q, r, a, d] = await Promise.all([
      fetch('/async/queued').then(r => r.json()),
      fetch('/async/running').then(r => r.json()),
      fetch('/async/all').then(r => r.json()),
      fetch('/async/debug').then(r => r.json()).catch(()=>null)
    ]);
    queuedEl.innerHTML = renderTasks(q);
    runningEl.innerHTML = renderTasks(r);
    allEl.innerHTML = renderTasks(a.sort((x,y)=> new Date(y.submittedAt)-new Date(x.submittedAt)).slice(0,50));
    if (d) {
      debugEl.textContent = `queued=${d.queuedCount} running=${d.runningCount}`;
    }
  } catch (e) {
    console.error(e);
    setStatus('Refresh error');
  }
}

async function refreshTask(id) {
  try {
    const res = await fetch('/async/' + encodeURIComponent(id));
    if (!res.ok) {
      await showModal('Not found', 'Task ' + id + ' not found');
      return;
    }
    const t = await res.json();
    refresh();
    setStatus('Task ' + id + ' status: ' + t.status);
    debugEl.textContent = 'Task ' + id + ' status: ' + t.status;
  } catch (e) {
    await showModal('Error', e.message || String(e));
  }
}

async function cancelTask(id) {
  const ok = await showConfirm('Cancel task', 'Cancel task ' + id + '?');
  if (!ok) return;
  try {
    const res = await fetch('/async/' + encodeURIComponent(id) + '/cancel', { method: 'POST' });
    if (!res.ok) {
      await showModal('Cancel failed', 'Failed to cancel task ' + id);
      return;
    }
    const json = await res.json();
    setStatus('Cancel requested: ' + id);
    refresh();
    debugEl.textContent = 'Cancel requested for ' + id;
  } catch (e) {
    await showModal('Cancel error', e.message || String(e));
  } finally {
    setTimeout(()=>setStatus(''), 2000);
  }
}

function setStatus(msg) { statusMsg.textContent = msg || ''; }

refresh();
setInterval(refresh, 5000);

