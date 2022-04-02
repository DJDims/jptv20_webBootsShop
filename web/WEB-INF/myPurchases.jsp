
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="history" items="${historys}">
                <div class="col">
                    <div class="card border-primary mb-3" style="max-width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">${history.product.title}</h4>
                            <p class="card-text">${history.product.description}</p>
                            <p class="card-text">Размер: ${history.product.size}</p>
                            <p class="card-text">Цена: ${history.product.price}€</p>
                            <p class="card-text">Дата покупки: ${history.purchaseDate}</p>
                        </div>
                    </div>
                </div>   
            </c:forEach>
        </div>
    </div>
</div>