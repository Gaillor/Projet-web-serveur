<#ftl encoding="utf-8">

<html>
<body xmlns="http://www.w3.org/1999/html">

<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome to the Pokemon World!</h1>
    <h2>Voici vos informations</h2>
    <ul>
        <#if user.getLastName()?has_content>
            <li>Nom: ${user.getLastName()}</li>
        <#else>
            <li>Nom: non-renseigné</li>
        </#if>
        <#if user.getFirstName()?has_content>
            <li>Prénom(s): ${user.getFirstName()}</li>
        <#else>
            <li>Prénom(s): non-renseigné</li>
        </#if>
        <li>Pseudo: ${user.getUserName()}</li>
        <li>Email: ${user.getEmail()}</li>    
    </ul>
</body>

</html>