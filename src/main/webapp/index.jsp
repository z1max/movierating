<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="pages/fragments/meta.jsp"%>
        <title>Movie Rating</title>
    </head>
    <body>
        <%@ include file="pages/fragments/header.jsp"%>
        <section class="content">
            <div class="welcome">
                <p><fmt:message key="welcome.welcomeMessage"/></p>
            </div>
            <div class="button-container">
                <a class="button" href="${pageContext.request.contextPath}/front?command=Signin"><fmt:message key="title.signin"/></a>
                <a class="button" href="${pageContext.request.contextPath}/front?command=Signup"><fmt:message key="title.signup"/></a>
            </div>
            <div class="button-container">
                <a class="button" href="${pageContext.request.contextPath}/front?command=Home"><fmt:message key="welcome.guest"/></a>
            </div>
        </section>
    </body>
</html>
