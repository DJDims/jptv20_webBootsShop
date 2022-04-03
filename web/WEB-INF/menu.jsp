
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="listProducts">Магазин обуви</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor02">
            <ul class="navbar-nav me-auto">
                <c:choose>
                    <c:when test="${topRole eq 'ADMINISTRATOR'}">
                        <li class="nav-item"><a class="nav-link" href="showChangeRole">Назначить роль</a></li>
                        <li class="nav-item"><a class="nav-link" href="showStatistic">Смотреть статистику</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddProduct">Добавить товар</a></li>
                        <li class="nav-item"><a class="nav-link" href="listProducts">Список товаров</a></li>
                        <li class="nav-item"><a class="nav-link" href="showEditUser">Редактировать данные</a></li>
                        <li class="nav-item"><a class="nav-link" href="showMyPurchases">Посмотреть свои покупки</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddMoney">Добавить денег</a></li>
                        <li class="nav-item"><a class="nav-link" href="showUsersList">Список пользователей</a></li>
                    </c:when>
                    <c:when test="${topRole eq 'SELLER'}">
                        <li class="nav-item"><a class="nav-link" href="showAddProduct">Добавить товар</a></li>
                        <li class="nav-item"><a class="nav-link" href="listProducts">Список товаров</a></li>
                        <li class="nav-item"><a class="nav-link" href="showEditUser">Редактировать данные</a></li>
                        <li class="nav-item"><a class="nav-link" href="showMyPurchases">Посмотреть свои покупки</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddMoney">Добавить денег</a></li>
                    </c:when>
                    <c:when test="${topRole eq 'CUSTOMER'}">
                        <li class="nav-item"><a class="nav-link" href="listProducts">Список товаров</a></li>
                        <li class="nav-item"><a class="nav-link" href="showEditUser">Редактировать данные</a></li>
                        <li class="nav-item"><a class="nav-link" href="showMyPurchases">Посмотреть свои покупки</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddMoney">Добавить денег</a></li>
                    </c:when>
                    <c:when test="${topRole eq NULL}">
                        <li class="nav-item"><a class="nav-link" href="listProducts">Посмотреть каталог товаров</a></li>
                    </c:when>
                </c:choose>
            </ul>
            <ul class="navbar-nav  mb-2 mb-lg-0">
                <c:if test="${authUser eq null}">
                    <li class="nav-item">
                        <a class="nav-link" href="showLogin">Войти</a>
                    </li>
                </c:if>
                <c:if test="${authUser ne null}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Выйти</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>