<#ftl encoding = 'UTF-8'>
<#import "spring.ftl" as spring/>
<body>
<form action="/users" method="post">
    Name:
    <input type="text" name="name">
    <br>
    Login:
    <input type="text" name="login">
    <br>
    Password:
    <input type="password" name="password">
    <br>
    Email:
    <input type="email" name="email">
    <br>

    <input type="submit">
</form>
</body>