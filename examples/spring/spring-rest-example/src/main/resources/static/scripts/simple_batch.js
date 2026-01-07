let latestExport = null;

    async function fetchJobs() {
      const res = await fetch('/batch_simple/jobs');
      return res.ok ? res.json() : [];
    }

    async function fetchLatestExport() {
      const res = await fetch('/batch_simple/exports/latest');
      if (!res.ok) return null;
      const data = await res.json();
      return data && data.filename ? data : null;
    }

    function badgeFor(status) {
      if (!status) return '<span class="badge badge-pending">PENDING</span>';
      status = status.toUpperCase();
      switch(status) {
        case 'RUNNING': return '<span class="badge badge-running">RUNNING</span>';
        case 'COMPLETED': return '<span class="badge badge-completed">COMPLETED</span>';
        case 'CANCELLED': return '<span class="badge badge-cancelled">CANCELLED</span>';
        case 'FAILED': return '<span class="badge badge-failed">FAILED</span>';
        default: return '<span class="badge badge-pending">PENDING</span>';
      }
    }

    function renderJob(job) {
      const deps = job.dependencies && job.dependencies.length ? job.dependencies.join(', ') : '—';
      const depsText = '<div class="graph"><strong>depends:</strong> ' + deps + '</div>';
      const status = (job.status || '').toUpperCase();
      const statusBadge = badgeFor(status);
      const started = job.startedAt ? new Date(job.startedAt).toLocaleTimeString() : '';
      const completed = job.completedAt ? new Date(job.completedAt).toLocaleTimeString() : '';

      const canCancel = status === 'RUNNING';
      const canStart = status !== 'RUNNING';

      const startAttr = canStart ? '' : 'disabled';
      const cancelAttr = canCancel ? '' : 'disabled';

      let exportLinkHtml = '';
      if (job.id === '5' && status === 'COMPLETED' && latestExport && latestExport.filename) {
        exportLinkHtml = `<div style="margin-top:8px;"><a class="btn btn-primary" href="${encodeURI(latestExport.url)}" download>Download export (${escapeHtml(latestExport.filename)})</a></div>`;
      }

      return `<div class="job-card" id="job-${escapeHtml(job.id)}">
        <div class="job-header">
          <div>
            <div style="font-weight:700">${escapeHtml(job.name)}</div>
            <div class="small">ID: ${escapeHtml(job.id)}</div>
          </div>
          <div style="text-align:right">
            ${statusBadge}
            <div class="small">${started ? 'started: ' + started : ''}${completed ? '<br>finished: ' + completed : ''}</div>
          </div>
        </div>

        <div style="margin-top:10px;">
          <div class="small">${depsText}</div>
          <div style="margin-top:8px;" class="small"><strong>Dependents:</strong> ${job.dependents && job.dependents.length ? job.dependents.join(', ') : '—'}</div>
          <div class="controls">
            <button class="btn btn-primary" ${startAttr} onclick="startOrRerun('${encodeURIComponent(job.id)}','${status}')">Start</button>
            <button class="btn btn-danger" ${cancelAttr} onclick="cancelJob('${encodeURIComponent(job.id)}')">Cancel</button>
          </div>
          ${exportLinkHtml}
        </div>
      </div>`;
    }

    function escapeHtml(s){ return (s||'').toString().replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;'); }

    async function refresh() {
      latestExport = await fetchLatestExport();
      const jobs = await fetchJobs();
      const grid = document.getElementById('jobsGrid');
      grid.innerHTML = jobs.map(renderJob).join('');
    }

    async function startOrRerun(id, status) {
      await fetch('/batch_simple/jobs/' + id + '/start', { method: 'POST' });

      await refresh();
    }

    async function cancelJob(id) {
      await fetch('/batch_simple/jobs/' + id + '/cancel', { method: 'POST' });
      await refresh();
    }

    document.getElementById('refresh').addEventListener('click', refresh);
    refresh();
    setInterval(refresh, 3000);