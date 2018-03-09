<!doctype html>
<html lang="fr">
	<head>
		<title>Admin | Boot</title>
	</head>
	<body>
		<p>Veuillez inserer le nom de l implementation de PersistantMediatheque a utiliser</p>
		<form action="boot" method="post">
			<input type="text" name="persistMediaName" placeholder="${persistMediaName}">
			<input type="submit" name="valider">
		</form>
		<c:if ${valide}=false && ${valide}!= null>
		<p>Le nom ${persistMediaName} n est pas valide</p>
		</c:if>
	</body>
</html>