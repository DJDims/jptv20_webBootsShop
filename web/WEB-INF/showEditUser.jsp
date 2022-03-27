
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid">
    <form action="editUser" method="POST" >
        <div class="w-100 d-flex justify-content-evenly">
            <div class="card border-primary mb-3" style="max-width: 20rem;">
                <div class="card-header">Личные данные</div>
                <div class="card-body">
                    <div class="form-group">
                        <label class="col-form-label mt-1">Имя</label>
                        <input type="text" class="form-control" name="firstName" value="${firstName}">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label mt-1">Фамилия</label>
                        <input type="text" class="form-control" name="sureName" value="${sureName}">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label mt-1">Телефон</label>
                        <input type="text" class="form-control" name="phone" value="${phone}">
                    </div>
                </div>
            </div>
            <div class="card border-primary mb-3" style="max-width: 20rem;">
                <div class="card-header">Данные аккаунта</div>
                <div class="card-body">
                    <div class="form-group">
                        <label class="col-form-label mt-1">Логин</label>
                        <input type="text" class="form-control" name="login" value="${login}">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label mt-1">Старый пароль</label>
                        <input type="password" class="form-control" name="oldPassword">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label mt-1">Новый пароль</label>
                        <input type="password" class="form-control" name="newPassword1">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label mt-1">Подтвердите новый пароль</label>
                        <input type="password" class="form-control" name="newPassword2">
                    </div>

                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <input class="btn btn-success mt-1" type="submit" value="Подтвердить изменения">
        </div>
    </form>
</div>