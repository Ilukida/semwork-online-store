<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link href="/resources/css/style.css" rel="stylesheet" >
    <link href="/resources/css/sign-in.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <form action="/sign-in" class="form-center-content" method="post">
        <div class="h3 mb-3 fw-normal">Sign in</div>
        <div class = "form-floating">
            <input id="email" class="form-control" name="email" type="email" placeholder="Email">
            <label for="email">Email</label>
        </div>
        <div class = "form-floating">
            <input id = "password" class="form-control" name="password" type="password" placeholder="Password">

            <label for="password">Password</label>
            </div>
        <input class="login-button" type="submit" value="Sign in">
    </form>
    <a href="/sign-up" class="login-button">Sign Up</a>
</div>
</body>
</html>