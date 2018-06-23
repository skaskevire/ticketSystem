<html>
<head><title>Ticket System Register Page</title></head>
<body onload="document.f.name.focus();">
<h3>Insert your Username and Password</h3>
<form name="user" action="/users/add" method="post">
    Name: <input type="text" name="name"/> <br/>
    Email: <input type="text" name="email"/> <br/>
    Password: <input type="text" name="password"/> <br/>
    <input type="submit" value="   Register   "/>
</form>
</body>
</html>