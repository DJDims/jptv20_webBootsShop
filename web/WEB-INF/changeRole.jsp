
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">

    <div class="card text-white border-primary mb-3" style="max-width: 20rem;">
        <div class="card-header">Назначение роли</div>
        <form action="changeRole">
            <div class="card-body">
                <div class="form-group">
                    <label class="form-label mt-1">Выбор пользователя</label>
                    <select class="form-select" name="selectUser">
                        <c:forEach var="item" items="${mapUsers}">
                            <option value="${item.key.id}">${item.key.firstName} ${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="form-label mt-1">Выбор роли</label>
                    <select class="form-select" name="selectRole">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-success mt-3">Success</button>
                </div>
            </div>
        </form>
    </div>
    
</div>