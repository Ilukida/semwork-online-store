<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Liked</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/ajakss.js"></script>

</head>
<body>

<a href="/profile" class="button1">Назад</a>
<main class="text-center">
    <h2 class="mt-5">Избранное</h2>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <#list liked as product>
                    <div id = "${product.id}" class="col">
                        <div class="card shadow-sm">
                            <img width="400" height="400" src="/files/${product.pictureId}"/>

                            <div class="card-body">
                                <p class="card-title">${product.name}</p>
                                <p class="card-text">${product.maker}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-secondary" id="btn-add-to-liked" onclick="deleteFromLiked(${product.id})">Удалить</button>
                                        <button class="btn btn-sm btn-outline-secondary" id="btn-add-to-basket" onclick="addToBasket(${product.id})">Добавить в корзину</button>
                                    </div>
                                    <small class="text-dark">Цена: ${product.price}</small>

                                </div>
                            </div>
                        </div>
                    </div>
                </#list>

            </div>
        </div>
    </div>
</main>
</body>
</html>