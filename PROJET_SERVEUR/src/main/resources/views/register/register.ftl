<#ftl encoding="utf-8">

<html>
<body xmlns="http://www.w3.org/1999/html">

<head>
    <title>Inscription</title>
</head>
<form method="POST" action="/register">
    <label for="firstname">Firstname:</label>
    <input type="text" id="firstname" name="firstname"><br>
    <label for="lastname">Lastname:</label>
    <input type="text" id="lastname" name="lastname"><br>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="Register">
</form>

</body>

</html>