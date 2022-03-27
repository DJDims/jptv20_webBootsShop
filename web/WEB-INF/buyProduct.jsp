
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
    <div class="card border-primary mb-3" style="max-width: 20rem;">
        <div class="card-header">Ваш счет</div>
        <div class="card-body">
            <p class="card-text">У вас на счету ${wallet}</p>
            <a href="showAddMoney"><p class="text-muted">Поплнить счет</p></a>
        </div>
    </div>
    <div class="card border-primary mb-3" style="max-width: 20rem;">
        <div class="card-header">Информация о товаре</div>
        <div class="card-body">
            <h4 class="card-title">${product.title}</h4>
            <p class="card-text">${product.description}</p>
            <p class="card-text">Размер: ${product.size}</p>
            <p class="card-text">Цена: ${product.price}€</p>
        </div>
    </div>
    <form action="buyProduct?id=${product.id}" method="POST">
        <button type="sumbit" class="btn btn-success">Купить</button>
    </form>
</div>