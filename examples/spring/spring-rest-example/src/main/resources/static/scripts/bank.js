let credentials = null; // {username, password}
let isAdmin = false;
let txs = []; // client-visible transactions for current view
let users = []; // admin user list
let selectedUserId = null;
let currentUserId = null; // id of logged-in account (set when users are loaded)
const apiBase = '/api/transactions';
const userBase = '/api/users';
const STORAGE_KEY = 'bank_credentials_v1';

function showAlert(msg, timeout=5000) {
  const el = document.getElementById('alert');
  el.innerHTML = '<div class="alert">' + escapeHtml(msg) + '</div>';
  if (timeout) setTimeout(()=>el.innerHTML='', timeout);
}
function escapeHtml(s){ return String(s).replace(/[&<>"']/g, (m) => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[m])); }

function saveCredentialsToStorage(creds) {
  try { localStorage.setItem(STORAGE_KEY, JSON.stringify(creds)); } catch (e) {}
}
function loadCredentialsFromStorage() {
  try { const raw = localStorage.getItem(STORAGE_KEY); if (!raw) return null; return JSON.parse(raw); } catch (e) { return null; }
}
function clearCredentialsFromStorage() { try { localStorage.removeItem(STORAGE_KEY); } catch(e) {} }

function setAuthArea() {
  const a = document.getElementById('auth-area');
  if (!credentials) {
    a.innerHTML = `
      <div class="flex">
        <input id="username" placeholder="username" type="text" class="muted-small" />
        <input id="password" placeholder="password" type="password" class="muted-small" />
        <button class="btn" id="login-btn">Login</button>
        <button class="btn ghost" id="register-btn">Register</button>
      </div>
    `;
    document.getElementById('login-btn').addEventListener('click', async () => {
      const u = document.getElementById('username').value.trim();
      const p = document.getElementById('password').value;
      if (!u || !p) { showAlert('username and password required'); return; }
      const ok = await attemptLogin(u, p);
      if (ok) {
        credentials = { username: u, password: p };
        saveCredentialsToStorage(credentials);
        await afterLogin();
      }
    });
    document.getElementById('register-btn').addEventListener('click', async () => {
      const u = document.getElementById('username').value.trim();
      const p = document.getElementById('password').value;
      if (!u || !p) { showAlert('username and password required to register'); return; }
      try {
        const res = await fetch('/api/auth/register', {
          method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({username:u,password:p})
        });
        if (res.ok) {
          showAlert('Registered — signing in');
          const ok = await attemptLogin(u, p);
          if (ok) {
            credentials = { username: u, password: p };
            saveCredentialsToStorage(credentials);
            await afterLogin();
          } else {
            showAlert('Registration succeeded but login failed');
          }
        } else {
          const text = await res.text();
          showAlert('Registration failed: ' + (text || res.statusText));
        }
      } catch (e) { showAlert('Network error: ' + e.message); }
    });
  } else {
    a.innerHTML = `
      <div class="flex" style="align-items:center">
        <div class="muted-small">Signed in as <strong>${escapeHtml(credentials.username)}</strong></div>
        <button class="btn ghost" id="logout-btn">Logout</button>
      </div>
    `;
    document.getElementById('logout-btn').addEventListener('click', () => {
      credentials = null; isAdmin = false; txs = []; users = []; selectedUserId = null; currentUserId = null;
      clearCredentialsFromStorage();
      render();
    });
  }
}

function authHeaders(contentType=true) {
  if (!credentials) return {};
  const h = { 'Authorization': 'Basic ' + btoa(credentials.username + ':' + credentials.password) };
  if (contentType) h['Content-Type'] = 'application/json';
  return h;
}

async function attemptLogin(username, password) {
  const h = { 'Authorization': 'Basic ' + btoa(username + ':' + password) };
  try {
    const res = await fetch(`${apiBase}/user`, { headers: h });
    if (res.status === 401 || res.status === 403) {
      showAlert('Invalid credentials');
      return false;
    }
    if (!res.ok) {
      showAlert('Login check failed: ' + res.statusText);
      return false;
    }
    return true;
  } catch (e) {
    showAlert('Network error during login: ' + e.message);
    return false;
  }
}

async function detectAdmin() {
  if (!credentials) return false;
  try {
    const res = await fetch(userBase + '/isAdmin', { headers: authHeaders(false) });
    if (res.status === 401 || res.status === 403) {
        return false;
    }

    return res.ok;
  } catch (e) {
    return false;
  }
}

async function loadTransactions() {
  if (!credentials) return;
  try {
    if (!isAdmin) {
      const res = await fetch(`${apiBase}/user`, { headers: authHeaders(false) });
      if (!res.ok) { if (res.status===401) showAlert('Unauthorized'); txs = []; render(); return; }
      const data = await res.json();
      txs = data.map(t => ({ id:t.id, date:t.date, description:t.description, amount:Number(t.amount), status:t.status || 'VERIFIED', userId: t.userId || (t.user && t.user.id) }));
    } else {
      if (!selectedUserId) { txs = []; render(); return; }
      const res = await fetch(`${apiBase}/user/${selectedUserId}`, { headers: authHeaders(false) });
      if (!res.ok) { showAlert('Failed loading user transactions: ' + res.statusText); return; }
      const data = await res.json();
      txs = data.map(t => ({ id:t.id, date:t.date, description:t.description, amount:Number(t.amount), status:t.status || 'VERIFIED', userId: t.userId || (t.user && t.user.id) }));
    }
    txs.sort((a,b)=> b.date.localeCompare(a.date));
    render();
  } catch (e) { showAlert('Network error: ' + e.message); }
}

async function loadUsers() {
  try {
    const res = await fetch(userBase, { headers: authHeaders(false) });
    if (!res.ok) { showAlert('Failed to load users'); return; }
    users = await res.json();
    const me = users.find(u => u.username === credentials.username);
    currentUserId = me ? me.id : null;
    renderUserSelect();
  } catch (e) { showAlert('Network error: ' + e.message); }
}

function renderUserSelect() {
  const sel = document.getElementById('user-select');
  sel.innerHTML = '';
  const empty = document.createElement('option');
  empty.value = '';
  empty.textContent = '-- choose user --';
  sel.appendChild(empty);
  users.forEach(u => {
    const o = document.createElement('option');
    o.value = u.id;
    o.textContent = `${u.username} (id:${u.id})`;
    sel.appendChild(o);
  });
  sel.style.display = 'inline-block';
  sel.onchange = () => {
    const val = sel.value;
    if (!val) {
      selectedUserId = null;
      document.getElementById('selected-user').textContent = '';
      txs = [];
      render();
      return;
    }
    selectedUserId = Number(val);
    const u = users.find(x=>x.id===selectedUserId);
    document.getElementById('selected-user').textContent = `Viewing user: ${u ? u.username : selectedUserId} (id:${selectedUserId})`;
    loadTransactions();
  };
}

function render() {
  setAuthArea();
  document.getElementById('actions-card').style.display = credentials ? 'block' : 'none';
  document.getElementById('open-add-btn').style.display = (credentials && (!isAdmin || (isAdmin && selectedUserId && selectedUserId !== currentUserId))) ? 'inline-block' : 'none';
  if (!isAdmin) { document.getElementById('user-select').style.display = 'none'; }

  const tbody = document.getElementById('tx-body');
  tbody.innerHTML = '';
  for (const t of txs) {
    const tr = document.createElement('tr');
    tr.dataset.id = t.id;
    tr.innerHTML = `
      <td class="date-cell">${escapeHtml(t.date || '')}</td>
      <td class="desc-cell">${escapeHtml(t.description || '')}</td>
      <td class="right amount-cell">${formatCurrency(t.amount)}</td>
      <td><span class="status ${escapeHtml(t.status || '')}">${escapeHtml(t.status || '')}</span></td>
      <td class="action-cell"></td>
    `;
    tbody.appendChild(tr);
  }

  attachRowControls();
  updateTotals();
}

function attachRowControls() {
  const tbody = document.getElementById('tx-body');
  tbody.querySelectorAll('tr').forEach(tr => {
    const id = tr.dataset.id;
    const t = txs.find(x => String(x.id) === String(id));
    const actions = tr.querySelector('.action-cell');
    actions.innerHTML = '';

    const editBtn = document.createElement('button'); editBtn.className='btn ghost small'; editBtn.textContent='Edit';
    editBtn.addEventListener('click', () => openInlineEditor(tr, t));
    actions.appendChild(editBtn);

    const delBtn = document.createElement('button'); delBtn.className='btn danger small'; delBtn.textContent='Delete';
    delBtn.addEventListener('click', () => {
      if (isAdmin) adminDelete(t.id);
      else markDelete(t.id);
    });
    actions.appendChild(delBtn);

    if (isAdmin && (t.status === 'NEW' || t.status === 'PENDING_DELETION')) {
      const app = document.createElement('button'); app.className='btn small'; app.textContent='Approve';
      app.addEventListener('click', ()=> adminApprove(t.id));
      const den = document.createElement('button'); den.className='btn ghost small'; den.textContent='Deny';
      den.addEventListener('click', ()=> adminDeny(t.id));
      actions.appendChild(app); actions.appendChild(den);
    }
  });
}

function openInlineEditor(tr, t) {
  const dateCell = tr.querySelector('.date-cell');
  const descCell = tr.querySelector('.desc-cell');
  const amountCell = tr.querySelector('.amount-cell');
  const actionCell = tr.querySelector('.action-cell');

  const inputDate = document.createElement('input'); inputDate.type='date'; inputDate.value = serverDateToInput(t.date);
  const inputDesc = document.createElement('input'); inputDesc.type='text'; inputDesc.value = t.description; inputDesc.style.width='100%';
  const inputAmount = document.createElement('input'); inputAmount.type='number'; inputAmount.step='0.01'; inputAmount.value = t.amount;

  dateCell.innerHTML = ''; dateCell.appendChild(inputDate);
  descCell.innerHTML = ''; descCell.appendChild(inputDesc);
  amountCell.innerHTML = ''; amountCell.appendChild(inputAmount);

  actionCell.innerHTML = '';
  const saveBtn = document.createElement('button'); saveBtn.className='btn small'; saveBtn.textContent='Save';
  const cancelBtn = document.createElement('button'); cancelBtn.className='btn ghost small'; cancelBtn.textContent='Cancel';
  actionCell.appendChild(saveBtn); actionCell.appendChild(cancelBtn);

  cancelBtn.addEventListener('click', () => { render(); });
  saveBtn.addEventListener('click', async () => {
    const newDate = inputDate.value;
    const newDesc = inputDesc.value.trim();
    const newAmount = Number(inputAmount.value);
    if (!newDate || !newDesc || isNaN(newAmount)) { showAlert('All fields required'); return; }
    const payload = { date: newDate, description: newDesc, amount: newAmount };

    try {
      const res = await fetch(`${apiBase}/${t.id}`, { method:'PUT', headers: authHeaders(true), body: JSON.stringify(payload) });
      if (!res.ok) {
        const text = await res.text();
        showAlert('Update failed: ' + (text || res.statusText));
        return;
      }
      await loadTransactions(); // refresh list and totals
    } catch (e) { showAlert('Network error: ' + e.message); }
  });
}

async function markDelete(id) {
  try {
    const res = await fetch(`${apiBase}/${id}`, { method:'DELETE', headers: authHeaders(false) });
    if (res.status === 401 || res.status === 403) { showAlert('Unauthorized'); return; }
    if (res.status === 404) { txs = txs.filter(x=>String(x.id)!==String(id)); render(); return; }
    if (!res.ok) { showAlert('Delete failed: ' + res.statusText); return; }
    await loadTransactions();
  } catch (e) { showAlert('Network error: ' + e.message); }
}

async function adminApprove(id) {
  try {
    const res = await fetch(`${apiBase}/approve/${id}`, { method:'POST', headers: authHeaders(false) });
    if (!res.ok) { showAlert('Approve failed: ' + res.statusText); return; }
    await loadTransactions();
  } catch (e) { showAlert('Network error: ' + e.message); }
}
async function adminDeny(id) {
  try {
    const res = await fetch(`${apiBase}/deny/${id}`, { method:'POST', headers: authHeaders(false) });
    if (!res.ok) { showAlert('Deny failed: ' + res.statusText); return; }
    await loadTransactions();
  } catch (e) { showAlert('Network error: ' + e.message); }
}
async function adminDelete(id) {
  if (!confirm('Delete this transaction permanently?')) return;
  try {
    const res = await fetch(`${apiBase}/${id}`, { method:'DELETE', headers: authHeaders(false) });
    if (!res.ok) { showAlert('Admin delete failed: ' + res.statusText); return; }
    await loadTransactions();
  } catch (e) { showAlert('Network error: ' + e.message); }
}

function openAddModal() {
  document.getElementById('modal-backdrop').style.display = 'flex';
  document.getElementById('modal-backdrop').setAttribute('aria-hidden','false');
  const today = new Date().toISOString().slice(0,10);
  document.getElementById('modal-date').value = today;
  document.getElementById('modal-desc').value = '';
  document.getElementById('modal-amount').value = '';
}
function closeAddModal() {
  document.getElementById('modal-backdrop').style.display = 'none';
  document.getElementById('modal-backdrop').setAttribute('aria-hidden','true');
}

async function saveModal() {
  const dateVal = document.getElementById('modal-date').value;
  const desc = document.getElementById('modal-desc').value.trim();
  const amount = Number(document.getElementById('modal-amount').value);
  if (!dateVal || !desc || isNaN(amount)) { showAlert('All fields required'); return; }
  const payload = { date: dateVal, description: desc, amount };

  try {
    let res;
    if (!isAdmin) {
      res = await fetch(`${apiBase}`, { method:'POST', headers: authHeaders(true), body: JSON.stringify(payload) });
    } else {
      if (!selectedUserId) { showAlert('Select a user first'); return; }
      if (currentUserId && selectedUserId === currentUserId) { showAlert('Admins cannot create transactions for themselves'); return; }
      res = await fetch(`${apiBase}/${selectedUserId}`, { method:'POST', headers: authHeaders(true), body: JSON.stringify(payload) });
    }

    if (!res.ok) {
      const text = await res.text();
      showAlert('Add failed: ' + (text || res.statusText));
      return;
    }
    await loadTransactions();
    closeAddModal();
  } catch (e) { showAlert('Network error: ' + e.message); }
}

function dateInputToServer(inputVal) { return inputVal.replace(/-/g, '/'); } // yyyy-mm-dd -> yyyy/MM/dd
function serverDateToInput(serverVal) { return (serverVal || '').replace(/\//g, '-'); }

function formatCurrency(n) {
  const sign = n < 0 ? '-' : '';
  const abs = Math.abs(n);
  return sign + '$' + abs.toFixed(2);
}

function updateTotals() {
  const verified = txs.filter(t => t.status === 'VERIFIED').reduce((s,t)=> s + Number(t.amount), 0);
  const pendingAdd = txs.filter(t => t.status === 'NEW' || t.status === 'PENDING_DELETION').reduce((s,t)=> s + Number(t.amount), 0);
  const unverifiedNet = pendingAdd;
  document.getElementById('verified-total').textContent = formatCurrency(verified);
  document.getElementById('unverified-total').textContent = formatCurrency(unverifiedNet);
}

document.getElementById('open-add-btn').addEventListener('click', openAddModal);
document.getElementById('modal-close').addEventListener('click', closeAddModal);
document.getElementById('modal-cancel').addEventListener('click', closeAddModal);
document.getElementById('modal-save').addEventListener('click', saveModal);

document.getElementById('user-select').addEventListener('click', () => {
  if (isAdmin && users.length === 0) loadUsers();
});

async function afterLogin() {
  setAuthArea();
  isAdmin = await detectAdmin();
  if (isAdmin) {
    await loadUsers(); // populate users & set currentUserId
  } else {
    document.getElementById('user-select').style.display = 'none';
  }
  selectedUserId = null;
  document.getElementById('selected-user').textContent = '';
  await loadTransactions();
  render();
}

(async function init() {
  const saved = loadCredentialsFromStorage();
  if (saved && saved.username && saved.password) {
    const ok = await attemptLogin(saved.username, saved.password);
    if (ok) {
      credentials = saved;
      await afterLogin();
      return;
    } else {
      clearCredentialsFromStorage();
    }
  }
  setAuthArea();
  render();
})();

window._app = { setCredentials: (u,p)=>{ credentials={username:u,password:p}; saveCredentialsToStorage(credentials); afterLogin(); } };