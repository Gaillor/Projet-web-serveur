<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Pokémons</th>
            <th>Actions</th> <!-- Ajout de la colonne "Actions" -->
        </tr>
    </thead>
    <tbody>
        <#list otherUsers as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>
                    <table>
                        <tbody>
                            <#list user.getPokemonsCollection() as pokemon,number>
                                <tr>
                                    <td>|Id: ${pokemon.id}</td>
                                    <td>|Nom: ${pokemon.name}</td>
                                    <td>|Niveau: ${pokemon.level}</td>
                                    <td>|Nombre: ${number}</td>
                                    <td>
                                        <!-- Ajout d'un formulaire pour sélectionner le Pokémon à échanger -->
                                        <form action="/trades/${user1.id}" method="post">
                                            <input type="hidden" name="selectedUserId" value="${user.id}">
                                            <input type="hidden" name="selectedPokemonId" value="${pokemon.id}">
                                            <input type="submit" value="Échanger">
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </td>
            </tr>
        </#list>
    </tbody>
</table>
