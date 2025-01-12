const form = document.getElementById('myForm');

form.addEventListener('submit', (event) => {
  event.preventDefault(); // Prevent default form submission

  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;

  // Basic email validation
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    alert('Invalid email address');
    return;
  }

  // Password strength validation (basic example)
  if (password.length < 8) {
    alert('Password must be at least 8 characters long');
    return;
  }

  // Submit the form or perform other actions
  console.log('Form submitted successfully!');
  // You can add more validation rules and actions here
});