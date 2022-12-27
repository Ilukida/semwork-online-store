<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Catalog</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

</head>
<body>


<a href="/profile" class="button1">Вернуться к профилю</a>
<main class="text-center">

    <h2 class="mt-5">Каталог</h2>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <#list categories as category>

                    <div class="col">
                        <div class="card shadow-sm">
                            <img width="400" height="400" src="/files/${category.pictureId}"/>

                            <div class="card-body">
                                <p class="card-title">${category.name}</p>
                                <p class="card-text"></p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="/products?categoryId=${category.id}" class="btn btn-sm btn-outline-secondary">Смотреть</a>
                                    </div>

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