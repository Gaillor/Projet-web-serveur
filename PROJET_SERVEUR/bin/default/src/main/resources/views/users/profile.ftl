<#ftl encoding="utf-8">

<html>
<body xmlns="http://www.w3.org/1999/html">

<head>
    <title>Profile</title>
</head>
<body>
    <h1>Welcome to the Pokemon World!</h1>
    <h2>Voici votre profil joueur</h2>
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
        </tbody>
    </table>

    <h2>Voici la liste de vos Pokémons</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Nom</th>
                <th>Niveau</th>
                <th>Nombre</th>
            </tr>
        </thead>
        <tbody>
            <#list pokemons as pokemon,number>
                <tr>
                    <td>|${pokemon.id}</td>
                    <td>|${pokemon.name}</td>
                    <td>|${pokemon.level}</td>
                    <td>|${number}</td>
                </tr>
            </#list>
        </tbody>
    </table>


<!-- Formulaire d'échange de Pokémon -->
<form action="/profile/${user.id}/trade" method="POST">
    <input type="hidden" name="userId" value="${user.id}" />
    
    <label for="pokemonId">Sélectionnez le Pokémon à échanger:</label>
    <select id="pokemonId" name="pokemonId">
        <#list pokemons as pokemonL,number>
            <option value="${pokemonL.id}">${pokemonL.name}</option>
        </#list>
    </select>
    
    <button type="submit">Échanger</button>
</form>

<!-- Formulaire d'augmentation de niveau -->
<form action="/profile/${user.id}/levelup" method="POST">
    <input type="hidden" name="userId" value="${user.id}" />
    
    <label for="pokemonIdLevelUp">Sélectionnez le Pokémon à augmenter de niveau:</label>
    <select id="pokemonIdLevelUp" name="pokemonIdLevelUp">
        <#list pokemons as pokemonL,number>
            <option value="${pokemonL.id}">${pokemonL.name}</option>
        </#list>
    </select>
    
    <label for="levelIncrease">Augmentation de niveau (maximum 5 points) :</label>
    <input type="number" id="levelIncrease" name="levelIncrease" min="0" max="5" />
    
    <button type="submit">Augmenter</button>
</form>


</body>
    
</html>