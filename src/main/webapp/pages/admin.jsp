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
                    <a class="button" href="front?command=GenresOrCountries&entity=genres"><fmt:message key="movie.genres"/></a>
                    <a class="button" href="front?command=GenresOrCountries&entity=countries"><fmt:message key="movie.countries"/></a>
                </div>
                <table class="users-table">
                    <thead>
                        <tr>
                            <th><fmt:message key="user.username"/></th>
                            <th><fmt:message key="user.email"/></th>
                            <th><fmt:message key="user.registered"/></th>
                            <th><fmt:message key="user.status"/></th>
                            <th><fmt:message key="user.enabled"/></th>
                            <th><fmt:message key="user.admin"/></th>
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
                                <td>
                                    <form method="post" action="front?command=EnableOrDisableUser">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input class="button ${user.enabled ? 'check' : 'cross'}" type="submit" value="">
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="front?command=GrantOrDenyAdmin">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input class="button ${user.isAdmin() ? 'check' : 'cross'}" type="submit" value="" ${user.getId() == activeUser.getId() ? 'disabled' : ''}>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="front?command=DeleteUser">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input class="button delete" type="submit" value="">
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

