<html>
<head>
    <title>Administration</title>
</head>
<body>
    <h1>Customers</h1>
    <ul>
	<#list users as user>
	<li>${user.name}</li>
	</#list>
	</ul>
	<form action="addUser" method="POST">
		<input type="text" name="name"></input>
		<input type="submit" name="Add">Add</input>
	</form>
</body>
</html>