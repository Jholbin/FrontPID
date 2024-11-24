<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background: #fff;
            padding: 20px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #4CAF50;
            font-size: 2.5rem;
        }
        p {
            font-size: 1.2rem;
            margin: 15px 0;
        }
        a, button {
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
        }
        a:hover, button:hover {
            background-color: #45a049;
        }
        button {
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenido, ${sessionScope.username}!</h1> 
        <p>Ingeso Correctamente al Sistema </p>
        <a href="/register">Registrar un Nuevo Usuario</a>
        <form action="/logout" method="get" style="display:inline;">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>
