
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="product" items="${products}">
                <div class="col">
                    <div class="card bg-light mb-3" style="max-width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">${product.title}</h4>
                            <p class="card-text" style="text-align: justify">${product.description}</p>
                            <p class="card-text">Размер: ${product.size}</p>
                            <c:if test="${product.quantity != 0}">
                                <p class="card-text">Цена: ${product.price}€</p>
                                <c:if test="${authUser ne null}">
                                    <a href="showBuyProduct?id=${product.id}"><p class="text-info"><strong>Купить</strong></p></a>
                                </c:if>
                            </c:if>
                            <c:if test="${product.quantity == 0}">
                                <p class="card-text text-warning">Нет в наличии!</p>
                            </c:if>
                            <c:if test="${topRole eq 'ADMINISTRATOR'}">
                                <a href="showEditProduct?id=${product.id}"><p class="text-info"><strong>Редактировать</strong></p></a></a>
                            </c:if>
                            <c:if test="${topRole eq 'SELLER'}">
                                <a href="showEditProduct?id=${product.id}"><p class="text-info"><strong>Редактировать</strong></p></a></a>
                            </c:if>
                        </div>
                    </div>
                </div>   
            </c:forEach>
        </div>
    </div>
</div>