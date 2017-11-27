<#ftl encoding = 'UTF-8'>
<#import "spring.ftl" as spring/>
<body>
<form action="/login" method="post">
    Login:
    <input type="text" name="login">
    <br>
    Password:
    <input type="password" name="password">
    <br>
    <input type="submit">
</form>
</body>