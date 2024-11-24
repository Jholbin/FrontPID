<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Register</title>

    <!-- Incluir BootstrapValidator CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
        }
        h2 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-size: 1rem;
            color: #333;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group input:focus {
            border-color: #4CAF50;
            outline: none;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            text-align: center;
            font-size: 0.9rem;
        }
        .login-link {
            text-align: center;
            margin-top: 15px;
        }
        .login-link a {
            color: #4CAF50;
            text-decoration: none;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Register</h2>
        <form id="id_form" action="/register" method="post" onsubmit="showSuccessMessage(event)">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required
                minlength="6" maxlength="30" pattern="^[a-zA-Z0-9]+$"
                    title="El nombre de usuario debe tener entre 6 y 30 caracteres y solo puede contener letras y números">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required
                minlength="8" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
                    title="El password debe tener al menos 8 caracteres, incluir una letra, un numero y un simbolo especial">
                    <button type="button" onclick="togglePasswordVisibility()">Mostrar</button>
            </div>
            <div class="form-group">
                <label for="firstname">Nombre:</label>
                <input type="text" id="firstname" name="firstname" required>
            </div>
            <div class="form-group">
                <label for="lastname">Apellido:</label>
                <input type="text" id="lastname" name="lastname" required>
            </div>
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" required>
            </div>

            <input type="hidden" name="role" value="ADMIN">

            <div class="form-group">
                <button type="submit">Register</button>
            </div>
            <div>
                <c:if test="${not empty error}">
                    <p class="error-message" style="color: red; font-weight: bold;">${error}</p>
                </c:if>
            </div>
            
        </form>
         <!-- Enlace para redirigir al login y hacer logout -->
        <div class="login-link">
        <p>Ya tienes una cuenta? <a href="#" onclick="logoutAndRedirect()">Ingresa Aqui</a></p>
        </div>

        <!-- Script para hacer logout antes de redirigir al login -->
        <script>
        function logoutAndRedirect() {
            // Crear un formulario de logout y enviarlo
            var form = document.createElement("form");
            form.method = "get";
            form.action = "/logout";
            document.body.appendChild(form);
            form.submit();

            // Redirigir al login después de hacer logout
            setTimeout(function() {
                window.location.href = "/login";
            }, 1000); // Espera un segundo para asegurar que el logout se haya procesado
        }

        function togglePasswordVisibility() {
    var passwordField = document.getElementById('password');
    var button = event.target;

    if (passwordField.type === "password") {
        passwordField.type = "text";
        button.textContent = "Ocultar";  // Cambia el texto del botón
    } else {
        passwordField.type = "password";
        button.textContent = "Mostrar";  // Cambia el texto del botón
    }
}
                    
        </script>
        
    </div>
</body>
</html>
