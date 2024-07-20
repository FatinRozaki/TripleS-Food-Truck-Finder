document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    
    // Validate credentials
    if (email === 'admin@gmail.com' && password === 'Admin12345') {
        localStorage.setItem('loggedIn', 'true');
        window.location.href = 'dashboard.html'; // Redirect to dashboard
    } else {
        document.getElementById('loginMessage').textContent = 'Invalid email or password. Please try again.';
    }
});

// Check if logged in on load (for dashboard access control)
document.addEventListener('DOMContentLoaded', function() {
    if (localStorage.getItem('loggedIn') === 'true') {
        // User is logged in, allow access to dashboard
        window.location.href = 'dashboard.html';
    }
});


