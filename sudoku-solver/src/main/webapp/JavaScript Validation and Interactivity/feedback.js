const emailInput = document.getElementById('email');
emailInput.addEventListener('input', () => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const emailError = emailInput.parentNode.querySelector('p');

  if (emailError) {
    emailError.remove();
  }

  if (!emailRegex.test(emailInput.value)) {
    const emailError = document.createElement('p');
    emailError.textContent = 'Invalid email address';
    emailError.style.color = 'red';
    emailInput.parentNode.appendChild(emailError);
  }
});