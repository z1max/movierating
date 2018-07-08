<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="i18n/messages"/>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="pages/fragments/meta.jsp"%>
        <title>Movie Rating | <fmt:message key="welcome.title"/></title>
    </head>
    <body>
        <%@ include file="pages/fragments/header.jsp"%>
        <section class="content">
            <div class="welcome">
                <p><fmt:message key="welcome.welcomeMessage"/></p>
            </div>
            <div class="button-container">
                <a class="button" href="${pageContext.request.contextPath}/front?command=Signin"><fmt:message key="welcome.signin"/></a>
                <a class="button" href="${pageContext.request.contextPath}/front?command=Signup"><fmt:message key="welcome.signup"/></a>
            </div>
            <div class="button-container">
                <a class="button" href="${pageContext.request.contextPath}/front?command=Home"><fmt:message key="welcome.guest"/></a>
            </div>
        </section>
    </body>
</html>
