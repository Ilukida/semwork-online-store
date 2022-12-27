<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Product</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<main class="text-center">
    <div class="container">
        <h2>${saveProduct.name}</h2>

        <div class="row align-items-start">

            <div class="col">

                <#if saveProduct.pictureId??>
                    <img height="500" width="500" src="/files/${saveProduct.pictureId}"/>
                <#else>
                    <img height="500" width="500" src="/no-img.jpg"/>
                </#if>

                <div class="change-avatar-button">
                    <a href = "http://localhost:8080/add-photo">add photo</a>
                </div>
            </div>

            <div class="col">
                <div class="alert alert-dark" role="alert">Информация</div>
                <p><strong>Название:</strong> ${saveProduct.name}</p>
                <p><strong>Производитель:</strong> ${saveProduct.maker}</p>
                <p><strong>Цена:</strong> ${saveProduct.price}</p>
                <p><strong>Описание:</strong> ${saveProduct.description}</p>
                <p><strong>Категория:</strong>${category}</p>
            </div>
        </div>

    </div>
</main>
</body>
</html>
