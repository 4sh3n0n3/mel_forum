<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="wrapper ps13-forum-block ps-13-box-shadow">
    <h2 class="head ps13-text-shadow">
            Вход на форум
    </h2>
    <form action="<c:url value="/login"/>" method="POST" id="login_form">
    <c:if test="${ not empty requestScope.error }">
        <p class="lead text-danger">
            <c:out value="${requestScope.error}"/>
        </p>
    </c:if>
        <br>
            <input class="form-control mr-sm-2" type="text" id="log_username" name="log_username" placeholder="Введите ваш логин" />
        </br>
        <br>
            <input class="form-control mr-sm-2" type="text" id="log_password" name="log_password" placeholder="Введите пароль" />
        </br>
        <input style="margin: auto; display: inherit;" type="submit" class="btn btn-primary ps13-text-shadow ps13-button navbar-btn my-2 my-sm-0" name="submit_reg_form" value="Войти" />
        <input type="hidden" name="url" value="
        <c:choose>
            <c:when test="${ not empty param['url'] }">
                <c:out value="${param['url']}"/>
            </c:when>
            <c:when test="${ empty param['url'] }">
                <c:out value="${requestScope['javax.servlet.forward.request_uri']}"/>
            </c:when>
        </c:choose>
        ">
        <div class="checkbox">
          <label>
            <input name="remember_me" type="checkbox" value="on"> Remember me
          </label>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/login_form_validation.js"></script>