// ... (within the submit event handler)
if (!emailRegex.test(email)) {
    const emailError = document.createElement('p');
    emailError.textContent = 'Invalid email address';
    emailError.style.color = 'red';
    email.parentNode.appendChild(emailError);
  }