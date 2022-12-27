<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Save Product</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/resources/css/sign-in.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <form action="/saveProduct" method="post" class="form-center-content">
        <div class="h3 mb fw-normal">Добавление продукта</div>

        <div class = "form-floating">
            <label for="name">Название продукта:</label>
            <input id = "name" name="name" type="text" class="form-control">
        </div>

        <div class = "form-floating">
            <label for="maker">Производитель:</label>
            <input id="maker" name="maker" type="text" class="form-control">
        </div>

        <div class = "form-floating">
            <label for="price">Цена:</label>
            <input id="price" name="price" type="number" class="form-control">
        </div>

        <div class = "form-floating">
            <label for="description">Описание:</label>
            <input id="description" name="description" type="text" class="form-control">
        </div>

        <div>
            <p><strong>Выбери категорию</strong></p>
            <p></p>
            <p><select name="category">
                    <option value="1">Молочная продукция</option>
                    <option value="2" selected>Колбасные изделия</option>
                    <option value="3">Крупы</option>
                    <option value="4">Морепродукты</option>
                    <option value="5">Напитки</option>
                    <option value="6">Овощи и зелень</option>
                    <option value="7">Сладкое</option>
                    <option value="8">Фрукты и ягоды</option>
                    <option value="9">Хлебобулочные изделия</option>
                </select>
        </div>

        <input class="login-button" type="submit" value="Сохранить">
    </form>
</div>
</body>
</html>