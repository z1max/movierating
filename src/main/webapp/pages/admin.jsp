<%@ taglib prefix="ldt" uri="WEB-INF/localDateTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="title.adminPage"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content">
                <div class="content-full">
                    <a class="button" href="front?command=EditMovie"><fmt:message key="movie.add"/></a>
                </div>
                <table class="users-table">
                    <thead>
                        <tr>
                            <th><fmt:message key="user.username"/></th>
                            <th><fmt:message key="user.email"/></th>
                            <th><fmt:message key="user.registered"/></th>
                            <th><fmt:message key="user.status"/></th>
                            <th><fmt:message key="user.roles"/></th>
                            <th><fmt:message key="user.enabled"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td><ldt:getFormatted localDate="${user.registered}" locale="${language}"/></td>
                                <td><fmt:message key="user.status.${user.status}"/></td>
                                <td>${user.roles}</td>
                                <td>${user.enabled}</td>
                                <td>
                                    <form method="post" action="front?command=AddAdmin">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input class="button ${user.isAdmin() ? 'button-disabled' : ''}" type="submit" value="<fmt:message key="user.makeAdmin"/>" ${user.isAdmin() ? 'disabled' : ''}>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="front?command=EnableUser">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input class="button" type="submit" value="<fmt:message key="user.enable"/>">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </body>
</html>

