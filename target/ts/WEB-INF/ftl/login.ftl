<html>
<head><title>Ticket System Login Page</title></head>
<body onload="document.f.username.focus();">
<h3>Login with Username and Password</h3>
<form name="f" action="/login" method="POST">
    <table>
        <tbody>
        <tr>
            <td>User:</td>
            <td><input type="text" name="username" value=""></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><input name="submit" type="submit" value="Login"></td>
        </tr>
        <tr>
            <td><input type='checkbox' name='remember-me'/></td>
            <td>Remember me on this computer.</td>
        </tr>
        <tr>
            <td></td>
            <td><a href="/register">Register</a></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>