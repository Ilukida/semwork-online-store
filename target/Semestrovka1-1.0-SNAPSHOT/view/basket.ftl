<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Basket</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/ajakss.js"></script>

</head>
<body>

<a href="/profile" class="button1">Назад</a>
<main class="text-center">
    <h2 class="mt-5">Корзина</h2>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <#list basket as product>
                    <div id = "${product.id}" class="col">
                        <div class="card shadow-sm">
                            <img width="400" height="400" src="/files/${product.pictureId}"/>

                            <div class="card-body">
                                <p class="card-title">${product.name}</p>
                                <p class="card-text">${product.maker}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-secondary" id="btn-add-to-liked" onclick="deleteFromBasket(${product.id})">Удалить</button>
                                        <button class="btn btn-sm btn-outline-secondary" id="btn-add-to-liked" onclick="addToLiked(${product.id})">Добавить в избранное</button>
                                    </div>
                                    <small class="text-dark">Цена: ${product.price}</small>

                                </div>
                            </div>
                        </div>
                    </div>
                </#list>

            </div>
        </div>
        <a href="/order" class="btn btn-sm btn-outline-secondary">Оформить заказ</a>
    </div>
</main>
</body>
</html>