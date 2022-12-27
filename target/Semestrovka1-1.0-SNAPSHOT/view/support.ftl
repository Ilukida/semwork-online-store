<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Support</title>
    <link href="/resources/css/style.css" rel="stylesheet" >
    <link href="/resources/css/sign-in.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">

    <div  class="form-center-content">
        <h2 class="mt-5">Поддержка</h2>

        <form action="/support" method="post">
            <div class = "form-floating">
                <textarea id="problem" class="input_green" required name="problem"></textarea>
            </div>
            <input class="button1" value="Отправить" type="submit">
            <a href="/profile" class="button1">Вернуться к профилю</a>
        </form>
    </div>

</div>
</body>
</html>