<#ftl encoding="utf-8">

<html>
<body xmlns="http://www.w3.org/1999/html">

<head>
    <title>Les utilisateurs</title>
</head>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <#if user.lastName?has_content>
                    <td>${user.lastName}</td>    
                <#else>
                    <td>Non renseigné</td>
                </#if>
                <#if user.firstName?has_content>
                    <td>${user.firstName}</td>    
                <#else>
                    <td>Non renseigné</td>
                </#if>
                <td>${user.email}</td>
            </tr>
        </#list>
    </tbody>
</table>
</body>

</html>
