
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="user" items="${users}">
                <div class="card border-dark mb-3" style="max-width: 20rem;">
                    <div class="card-header">${user.login}</div>
                    <div class="card-body">
                        <h4 class="card-title">${user.firstName} ${user.sureName}</h4>
                        <p class="card-text">${user.phone}</p>
                        <p class="card-text">${user.wallet}€</p>
                    </div>
                </div>  
            </c:forEach>
        </div>
    </div>
</div>