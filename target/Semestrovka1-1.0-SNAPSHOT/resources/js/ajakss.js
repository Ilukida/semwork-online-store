function addToBasket(productId) {
    $.ajax('/add-basket', {
        method: 'GET',
        data: {
            'productId': productId
        },
        success: function( result ) {
            alert("The product was added to basket")
        }
    })
}

function addToLiked(productId) {
    $.ajax('/add-liked', {
        method: 'GET',
        data: {
            'productId': productId
        },
        success: function( result ) {
            alert("The product was added to liked")
        }
    })
}

function deleteFromBasket(productId) {
    $.ajax('/delete-basket', {
        method: 'GET',
        data: {
            'productId': productId
        },
        success: function( result ) {
            let deleteForm = $("#"+productId)
            deleteForm.hide()
            alert("The product was deleted from basket")
        }
    })
}

function deleteFromLiked(productId) {
    $.ajax('/delete-liked', {
        method: 'GET',
        data: {
            'productId': productId
        },
        success: function( result ) {
            let deleteForm = $("#"+productId)
            deleteForm.hide()
            alert("The product was deleted from liked")
        }
    })
}