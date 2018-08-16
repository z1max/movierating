<%@ taglib prefix="ldt" uri="WEB-INF/localDateTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="${param.entity == 'genres' ? 'movie.genres' : 'movie.countries'}"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content-small">
                <div>
                    <form id="addEditForm" method="post" action="front?command=AddGenreOrCountry" class="form">
                        <input type="text" name="name">
                        <input type="hidden" name="entity" value="${param.entity}">
                        <input class="button" name="submit" type="submit" value="<fmt:message key="button.add"/>">
                    </form>
                </div>
                <table class="users-table">
                    <thead>
                        <tr>
                            <th><fmt:message key="${param.entity == 'genres' ? 'movie.genre' : 'movie.country'}"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${entities}" var="entity">
                            <tr>
                                <td>${entity.name}</td>
                                <td>
                                    <form onsubmit="return edit(this)">
                                        <input type="hidden" name="id" value="${entity.id}">
                                        <input type="hidden" name="name" value="${entity.name}">
                                        <input class="button edit" type="submit" value="">
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="front?command=DeleteGenreOrCountry">
                                        <input type="hidden" name="id" value="${entity.id}">
                                        <input type="hidden" name="entity" value="${param.entity}">
                                        <input class="button delete" type="submit" value="">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <script src="${pageContext.request.contextPath}/resources/js/editGenreOrCountryHelper.js"></script>
    </body>
</html>