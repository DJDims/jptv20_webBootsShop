
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="my-2 w-100 d-flex justify-content-center">Регистрация нового пользователя</h2>
<div class="w-100 d-flex justify-content-center">
    <form action="registration" method="POST">
        <div class="card border-0 px-3 mb-2" style="width: 70em;">
            <div class="row gx-5">
                <div class="form-group col">
                    <label class="form-label mt-3">Имя</label>
                    <input type="text" class="form-control" name="firstName" placeholder="Введите имя">
                </div>
                <div class="form-group col">
                    <label class="form-label mt-3">Фамилия</label>
                    <input type="text" class="form-control" name="sureName" placeholder="Введите фамилию">
                </div>
                <div class="form-group col">
                    <label class="form-label mt-3">Телефон</label>
                    <input type="text" class="form-control" name="phone" placeholder="Введите телефон">
                </div>
            </div>
            <div class="row gx-5">
                <div class="form-group col">
                    <label class="form-label mt-2">Логин</label>
                    <input type="text" class="form-control mb-3" name="login" placeholder="Введите логин">
                </div>
                <div class="form-group col">
                    <label class="form-label mt-2">Пароль</label>
                    <input type="password" class="form-control mb-3" name="password1" placeholder="Введите пароль">
                </div>
                <div class="form-group col">
                    <label class="form-label mt-2">Подтверждение пароля</label>
                    <input type="password" class="form-control mb-3" name="password2" placeholder="Повторите пароль">
                </div>
            </div>
        </div>
        <div>
            <div class="w-100 d-flex justify-content-center">
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </div>
        </div>
    </form>
</div>