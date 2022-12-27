<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/resources/css/sign-in.css" rel="stylesheet">
</head>
<body>
<div class="container">
<form action="/sign-up" method="post" class="form-center-content">
    <div class="h3 mb fw-normal">Sign Up</div>
    <div class = "form-floating">

        <label for="firstName">First name</label>
        <input id = "firstName"name="firstName" type="text" class="form-control">
        </div>
    <div class = "form-floating">
        <label for="lastName">Last name</label>
        <input id="lastName" name="lastName" type="text" class="form-control">
    </div>
    <div class = "form-floating">
    <label for="email">Email</label>
        <input id="email" name="email" type="email" class="form-control">
    </div>
    <div class = "form-floating">
    <label for="password">Password</label>
        <input id="password" name="password" type="password" class="form-control">
    </div>
    <div class = "form-floating">
        <label for="phoneNumber">PhoneNumber</label>
        <input id="phoneNumber" name="phoneNumber" type="text" class="form-control">
    </div>
    <input class="login-button" type="submit" value="Sign up">
</form>
    <a href="/sign-in" class="login-button">Sign In</a>
</div>
</body>
</html>