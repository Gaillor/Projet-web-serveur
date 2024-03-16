<#ftl encoding="utf-8">

<html>
<body xmlns="http://www.w3.org/1999/html">

<head>
    <title>Les pokémons</title>
</head>
<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Niveau</th>
            <th>Id-propriétaire</th>
        </tr>
    </thead>
    <tbody>
        <#list pokemons as pokemon>
            <tr>
                <td>${pokemon.id}</td>
                <td>${pokemon.name}</td>
                <td>${pokemon.level}</td>
                <td>${pokemon.ownerId}</td>  
            </tr>
        </#list>
    </tbody>
</table>
</body>

</html>
