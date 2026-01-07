function qs(name) {
      const u = new URL(window.location.href);
      return u.searchParams.get(name);
    }

    function jsonResToStr(obj) {
      try { return JSON.stringify(obj, null, 2); } catch (e) { return String(obj); }
    }

    function handleFetch(promise, onSuccess, onError) {
      const status = document.getElementById('status');
      status.textContent = 'Loading...';
      promise
        .then(async res => {
          const text = await res.text();
          let parsed;
          try { parsed = JSON.parse(text); } catch (e) { parsed = text || ''; }
          if (!res.ok) {
            status.textContent = 'Error ' + res.status + ' ' + res.statusText;
            if (onError) onError(res, parsed);
            return;
          }
          status.textContent = 'OK';
          if (onSuccess) onSuccess(parsed);
        })
        .catch(err => {
          status.textContent = 'Network/Error: ' + String(err);
          if (onError) onError(null, err);
        });
    }

    function formatDate(ms) {
      if (!ms) return '';
      try { return new Date(ms).toLocaleString(); } catch (e) { return String(ms); }
    }

    function isRunningStatus(status) {
      if (!status) return false;
      const s = String(status).toUpperCase();
      return s === 'STARTED' || s === 'STARTING' || s === 'STOPPING';
    }

    function renderHistory(payload) {
      const container = document.getElementById('history-container');
      container.innerHTML = '';
      if (!payload || !Array.isArray(payload) || payload.length === 0) {
        container.innerHTML = '<div class="card"><div class="muted">No history available.</div></div>';
        return;
      }
      for (const inst of payload) {
        const instEl = document.createElement('div');
        instEl.className = 'card instance';
        const header = document.createElement('div');
        header.innerHTML = `<div><strong>Instance:</strong> ${inst.instanceId} <span class="muted">(${escapeHtml(inst.jobName)})</span></div>`;
        instEl.appendChild(header);

        if (!inst.executions || inst.executions.length === 0) {
          const none = document.createElement('div');
          none.className = 'muted';
          none.textContent = 'No executions for this instance.';
          instEl.appendChild(none);
        } else {
          const table = document.createElement('table');
          table.innerHTML = `<thead><tr>
            <th>Execution ID</th><th>Status</th><th>Start</th><th>End</th><th>Exit</th><th>Actions</th>
          </tr></thead>`;
          const body = document.createElement('tbody');
          for (const exec of inst.executions) {
            const execId = exec.executionId || exec.id || '-';
            const statusStr = (exec.status || exec.batchStatus || '') || '';
            const running = isRunningStatus(statusStr);

            const tr = document.createElement('tr');
            tr.innerHTML = `
              <td>${execId}</td>
              <td>${escapeHtml(statusStr)}</td>
              <td>${formatDate(exec.startTime)}</td>
              <td>${formatDate(exec.endTime)}</td>
              <td>${escapeHtml(exec.exitCode || exec.exitStatus || '')}</td>
              <td></td>
            `;
            const actionsTd = tr.querySelector('td:last-child');

            const viewBtn = document.createElement('button');
            viewBtn.textContent = 'View';
            viewBtn.onclick = () => viewExecution(exec.executionId || exec.id);

            const stopBtn = document.createElement('button');
            stopBtn.textContent = 'Stop';
            stopBtn.className = 'secondary';
            stopBtn.disabled = !running;
            stopBtn.title = running ? 'Request stop for execution' : 'Cannot stop: execution not running';
            stopBtn.onclick = () => {
              if (stopBtn.disabled) return;
              stopExecution(exec.executionId || exec.id, actionsTd);
            };

            const abandonBtn = document.createElement('button');
            abandonBtn.textContent = 'Abandon';
            abandonBtn.className = 'warn';
            abandonBtn.disabled = !running;
            abandonBtn.title = running ? 'Abandon execution' : 'Cannot abandon: execution not running';
            abandonBtn.onclick = () => {
              if (abandonBtn.disabled) return;
              abandonExecution(exec.executionId || exec.id, actionsTd);
            };

            actionsTd.appendChild(viewBtn);
            actionsTd.appendChild(stopBtn);
            actionsTd.appendChild(abandonBtn);

            body.appendChild(tr);
          }
          table.appendChild(body);
          instEl.appendChild(table);
        }

        container.appendChild(instEl);
      }
    }

    function viewExecution(id) {
      if (!id) { alert('Missing execution id'); return; }
      handleFetch(fetch(`/api/batch/executions/${encodeURIComponent(id)}`),
        (payload) => {
          document.getElementById('exec-detail').textContent = jsonResToStr(payload);
          document.getElementById('detail-card').style.display = 'block';
          document.getElementById('detail-card').scrollIntoView({behavior:'smooth'});
        },
        () => alert('Failed to fetch execution details')
      );
    }

    function stopExecution(id, outTd) {
      if (!id) { alert('Missing id'); return; }
      if (!confirm('Request stop for execution ' + id + '?')) return;
      if (outTd) outTd.textContent = 'Stopping...';
      handleFetch(fetch(`/api/batch/executions/${encodeURIComponent(id)}/stop`, { method: 'POST' }),
        (payload) => {
          if (outTd) outTd.textContent = 'Stop requested';
          const p = document.createElement('div'); p.className='small muted'; p.textContent = jsonResToStr(payload);
          outTd.appendChild(p);
        },
        () => {
          if (outTd) outTd.textContent = 'Stop failed';
        }
      );
    }

    function abandonExecution(id, outTd) {
      if (!id) { alert('Missing id'); return; }
      if (!confirm('Abandon execution ' + id + '? This is irreversible.')) return;
      if (outTd) outTd.textContent = 'Abandoning...';
      handleFetch(fetch(`/api/batch/executions/${encodeURIComponent(id)}/abandon`, { method: 'POST' }),
        (payload) => {
          if (outTd) outTd.textContent = 'Abandoned';
          const p = document.createElement('div'); p.className='small muted'; p.textContent = jsonResToStr(payload);
          outTd.appendChild(p);
        },
        () => {
          if (outTd) outTd.textContent = 'Abandon failed';
        }
      );
    }

    function escapeHtml(str) {
      if (str === undefined || str === null) return '';
      return String(str).replace(/[&<>"']/g, s => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":"&#39;"}[s]));
    }

    document.getElementById('fetch-btn').addEventListener('click', function() {
      const name = document.getElementById('job-name').value.trim();
      const start = document.getElementById('start').value || '0';
      const count = document.getElementById('count').value || '20';
      if (!name) { alert('Please provide job name'); return; }
      const url = `/api/batch/jobs/${encodeURIComponent(name)}/history?start=${encodeURIComponent(start)}&count=${encodeURIComponent(count)}`;
      handleFetch(fetch(url), (payload) => renderHistory(payload), (res, err) => {
        document.getElementById('history-container').innerHTML = '<div class="muted">Failed to fetch history.</div>';
      });
    });

    document.getElementById('clear-btn').addEventListener('click', function() {
      document.getElementById('history-container').innerHTML = '';
      document.getElementById('status').textContent = 'Cleared';
      document.getElementById('detail-card').style.display = 'none';
    });

    document.getElementById('detail-close').addEventListener('click', function() {
      document.getElementById('detail-card').style.display = 'none';
    });

    document.getElementById('open-main').addEventListener('click', function() {
      window.open('/batch.html', '_self');
    });

    document.addEventListener('DOMContentLoaded', function() {
      const job = qs('job');
      if (job) {
        document.getElementById('job-name').value = job;
        document.getElementById('fetch-btn').click();
      }
    });