
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="w-100 d-flex justify-content-center">
    <form action="editProduct?id=${id}" method="POST" >
        <div class="card border-0 mb-3 px-3" style="width: 40em;">
            <h2 class="my-3 w-100 d-flex justify-content-center">Изменить товар</h2>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Название</label>
                <input type="text" class="form-control" name="name" value="${product.title}">
            </div>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Описание</label>
                <textarea class="form-control" name="description" rows="3" value="">${product.description}</textarea>
            </div>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Размер</label>
                <input type="number" min="31" max="48" class="form-control" name="size" value="${product.size}">
            </div>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Цена</label>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-text">€</span>
                        <input type="number" min="1.00" step="0.01" name="price" class="form-control" value="${product.price}">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Количество</label>
                <input type="number" min="1" class="form-control mb-4" name="quantity" value="${product.quantity}">
            </div>
            <input class="btn btn-primary mb-2" type="submit" value="Изменить товар">
          </div>
    </form>
</div>