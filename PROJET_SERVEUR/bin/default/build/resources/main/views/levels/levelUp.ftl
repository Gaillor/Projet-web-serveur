<#ftl encoding="utf-8">

<html>
<body xmlns="http://www.w3.org/1999/html">
<head>
    <title>Confirmation d'augmentation de niveau</title>
</head>
<body>
    <h1>Confirmation d'augmentation de niveau</h1>
    <p>Le niveau du Pokémon a été augmenté avec succès !</p>
    <p>Nouveau niveau du Pokémon :</p>
    <p>Nom : ${pokemon.name}</p>
    <p>Niveau : ${pokemon.level}</p>
    <a href="/profile/${user.id}">Retour au profil</a>
</body>
</html>