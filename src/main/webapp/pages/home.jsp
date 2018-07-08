<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n/messages"/>
<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="home.title"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <form method="post" action="front?command=ChangeLanguage">
                <select name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
            <div class="content">
                <c:forEach items="${movies}" var="movie">
                    <jsp:useBean id="movie" scope="page" type="by.z1max.dto.LazyMovie"/>
                    <a class="movie" href="?command=Details&id=${movie.id}">
                        <div>
                            <div class="rating">
                                <span>${movie.rating}</span>
                            </div>
                            <h1>${movie.title}</h1>
                            <div>
                                <table class="table">
                                    <tr>
                                        <th><fmt:message key="movie.releaseDate"/></th>
                                        <td><fmt:formatDate value="${movie.releaseDate}"/></td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="movie.director"/></th>
                                        <td>${movie.director}</td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="movie.runtime"/></th>
                                        <td>${movie.runtime}</td>
                                    </tr>
                                </table><!--
                             --><table class="table">
                                    <tr>
                                        <th><fmt:message key="movie.genres"/></th>
                                        <td>${movie.genres}</td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="movie.country"/></th>
                                        <td>${movie.countries}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </a>
                    <hr/>
                </c:forEach>
            </div>
        </section>
        <%@ include file="fragments/footer.jsp" %>
    </body>
</html>
