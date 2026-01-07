(function(){
    var form = document.getElementById('contact-form');
    if (!form) return; // nothing to do

    var submitBtn = document.getElementById('contact-submit');
    var spinner = document.getElementById('contact-spinner');
    var messages = document.getElementById('ajax-messages');

    function clearMessages() {
      if (!messages) return;
      messages.innerHTML = '';
    }

    function scheduleAutoHideSuccess() {
      if (!messages) return;
      var successEl = messages.querySelector('.alert-success');
      if (!successEl) return;
      setTimeout(function(){
        var e = messages.querySelector('.alert-success');
        if (e) {
          e.remove();
        }
      }, 5000);
    }

    function showMessage(html) {
      if (!messages) return;
      messages.innerHTML = html;
      scheduleAutoHideSuccess();
    }

    if (messages && messages.querySelector('.alert-success')) {
      scheduleAutoHideSuccess();
    }

    form.addEventListener('submit', function(ev){
      ev.preventDefault();

      if (submitBtn) { submitBtn.disabled = true; submitBtn.setAttribute('aria-busy','true'); }
      if (spinner) { spinner.setAttribute('aria-hidden','false'); }

      clearMessages();

      var action = form.getAttribute('action') || window.location.href;
      var formData = new FormData(form);

      fetch(action, {
        method: (form.getAttribute('method') || 'POST').toUpperCase(),
        body: formData,
        headers: { 'X-Requested-With': 'XMLHttpRequest' }
      }).then(function(response){
        return response.text().then(function(text){
          try {
            var parser = new DOMParser();
            var doc = parser.parseFromString(text, 'text/html');
            var success = doc.querySelector('.alert-success');
            var error = doc.querySelector('.alert-error');
            if (success) {
              showMessage('<div class="alert alert-success">' + success.innerHTML + '</div>');
            } else if (error) {
              showMessage('<div class="alert alert-error">' + error.innerHTML + '</div>');
            } else if (response.ok) {
              showMessage('<div class="alert alert-success">Message sent.</div>');
            } else {
              showMessage('<div class="alert alert-error">An unexpected error occurred.</div>');
            }
          } catch (e) {
            if (response.ok) showMessage('<div class="alert alert-success">Message sent.</div>');
            else showMessage('<div class="alert alert-error">An unexpected error occurred.</div>');
          }

          if (spinner) spinner.setAttribute('aria-hidden','true');
          if (submitBtn) { submitBtn.disabled = false; submitBtn.setAttribute('aria-busy','false'); }
        });
      }).catch(function(err){
        showMessage('<div class="alert alert-error">Could not send message. Please try again later.</div>');
        if (spinner) spinner.setAttribute('aria-hidden','true');
        if (submitBtn) { submitBtn.disabled = false; submitBtn.setAttribute('aria-busy','false'); }
      });
    });
  })();
