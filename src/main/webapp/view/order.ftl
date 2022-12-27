<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/css/styleOrder.css">
</head>
<body>
<section class="spec-container">
    <div class="row">
        <div class="col-7">
            <div class="col-md-11">
                <h4 class="mb-3">Платеж</h4>
                <form action="/order" method="post">
                    <div>
                        <label for="address" class="form-label">Город</label>
                        <input type="text" class="form-control" id="address" name="city">
                    </div>
                    <div>
                        <label for="street" class="form-label">Улица</label>
                        <input type="text" class="form-control" id="street" name="street">
                    </div>
                    <div>
                        <label for="house" class="form-label">Дом</label>
                        <input type="text" class="form-control" id="house" name="house">
                    </div>
                    <div>
                        <label for="apartment" class="form-label">Квартира</label>
                        <input type="text" class="form-control" id="apartment" name="apartment" placeholder="если нет квартиры, то выбирите 0">
                    </div>
                    <hr class="my-4">
                    <div class="row gy-3">
                        <div class="col-md-6">
                            <label for="cardOwner" class="form-label">Имя владельца</label>
                            <input type="text" class="form-control" id="cardOwner" name="cardOwner">
                            <small class="text-muted">Полное имя, как указано на карточке</small>
                        </div>
                        <div class="col-md-6">
                            <label for="cardNumber" class="form-label">Номер карты</label>
                            <input type="text" class="form-control" id="cardNumber" name="cardNumber">
                        </div>
                        <div class="col-md-3">
                            <label for="expiration" class="form-label">Срок истечения</label>
                            <input type="text" class="form-control" id="expiration" name="expiration">
                        </div>
                        <div class="col-md-3">
                            <label for="cvv" class="form-label">CVV</label>
                            <input type="text" class="form-control" id="cvv">
                        </div>
                        <hr class="my-4">
                    </div>
                    <hr class="my-4">
                    <div>
                        <button class="btn-primary btn" type="submit">Продолжить</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-5">
            <h4 class="text_color">Продукты</h4>
            <div class="list-group rounded">
                <ul class="list-group mb-3">
                    <#list basket as product>
                        <li class="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                                <h6 class="my-0">${product.name}</h6>
                                <small class="text-muted">${product.maker}</small>
                            </div>
                            <span class="text-muted">${product.price}</span>
                        </li>
                    </#list>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Сумма:</span>
                        <strong>${amount}</strong>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

<footer class="center">
    <div >
        <p class="mb-1">&copy;2022t</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="/support">Support</a></li>
        </ul>
    </div>
</footer>

</body>
</html>