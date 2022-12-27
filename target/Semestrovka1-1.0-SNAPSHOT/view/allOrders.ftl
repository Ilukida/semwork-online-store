<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>All Orders</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <style>
        .rounded {
            counter-reset: li;
            list-style: none;
            font: 14px "Trebuchet MS", "Lucida Sans";
            padding: 0;
            text-shadow: 0 1px 0 rgba(255,255,255,.5);
        }
    </style>

</head>
<body>

<a href="/profile" class="button1">Вернуться к профилю</a>

<main class="text-center">
    <h2 class="mt-5">Все заказы</h2>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <div class="col">

                    <ol class="rounded">
                        <#list allOrders as order>
                            <li><a href="/all-products-in-order?orderId=${order.id}"> Заказ на сумму ${order.amount}</a></li>
                        </#list>
                    </ol>

                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
