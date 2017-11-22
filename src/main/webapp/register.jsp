<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="wrapper ps13-forum-block ps-13-box-shadow">
    <h2 class="head ps13-text-shadow">
            Регистрация на форуме
    </h2>
    <form action="<c:url value="/send_register_form"/>" method="POST" id="registration_form">
        <br>
            <input class="form-control mr-sm-2" type="text" id="reg_username" name="reg_username" placeholder="Введите желаемое имя пользователя" />
        </br>
        <br>
            <input class="form-control mr-sm-2" type="text" id="reg_password" name="reg_password" placeholder="Введите пароль" />
        </br>
        <br>
            <input class="form-control mr-sm-2" type="text" id="reg_password_check" name="reg_password_check" placeholder="Повторите пароль" />
        </br>
        <br>
            <input class="form-control mr-sm-2" type="text" id="reg_email" name="reg_email" placeholder="Введите email" />
        </br>
        <input style="margin: auto; display: inherit;" type="submit" class="btn btn-primary ps13-text-shadow ps13-button navbar-btn my-2 my-sm-0" name="submit_reg_form" value="Зарегестрироваться" />
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/registration_form_validation.js"></script>