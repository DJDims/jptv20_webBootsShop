
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
    <form action="addMoney" method="POST">
        <div class="card border-primary mb-3" style="max-width: 20rem;">
            <div class="card-header">Добавление денег</div>
            <div class="card-body">
                <div class="form-group">
                    <label class="form-label mt-1">Добавить денег</label>
                    <div class="form-group">
                        <div class="input-group mb-3">
                            <span class="input-group-text">€</span>
                            <input type="text" min="5" max="500" step="5" class="form-control" name="money">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Внести деньги</button>
            </div>
        </div>
    </form>
</div>