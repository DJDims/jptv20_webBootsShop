
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="product" items="${products}">
                <div class="col">
                    <div class="card border-primary mb-3" style="max-width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">${product.title}</h4>
                            <p class="card-text">${product.description}</p>
                            <p class="card-text">Размер: ${product.size}</p>
                            <c:if test="${product.quantity != 0}">
                                <p class="card-text">Цена: ${product.price}€</p>
                                <c:if test="${authUser ne null}">
                                    <a href="showBuyProduct?id=${product.id}">Купить</a>
                                </c:if>
                            </c:if>
                            <c:if test="${product.quantity == 0}">
                                <p class="card-text text-warning">Нет в наличии!</p>
                            </c:if>
                        </div>
                    </div>
                </div>   
            </c:forEach>
        </div>
    </div>
</div>