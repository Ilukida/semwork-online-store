<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Product</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/ajakss.js"></script>

</head>

<body>
<main class="text-center">
    <div class="container">
        <h2>${product.name}</h2>
        <div class="row align-items-start">
            <div class="col">

                <img height="400" width="400" src="/files/${product.pictureId}">

                <div>
                    <button class="btn btn-success" id="btn-add-to-basket" onclick="addToBasket(${product.id})">Добавить в корзину</button>

                    <button class="btn btn-success" id="btn-add-to-liked" onclick="addToLiked(${product.id})">Добавить в избранное</button>
                </div>

            </div>

            <div class="col">
                <div class="alert alert-dark" role="alert">Информация</div>
                <p><strong>Название:</strong> ${product.name}</p>
                <p><strong>Производитель:</strong> ${product.maker}</p>
                <p><strong>Цена:</strong> ${product.price}</p>
                <p><strong>Описание:</strong> ${product.description}</p>
            </div>

        </div>
    </div>
</main>
</body>
</html>
