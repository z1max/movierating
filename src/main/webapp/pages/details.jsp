<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="i18n/messages"/>
<!doctype html>
<html>
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | Details</title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content">
                <div class="movie-details">
                    <h1>${movie.title}</h1>
                    <table class="table">
                        <tr>
                            <th><fmt:message key="movie.director"/></th>
                            <td>${movie.director}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.releaseDate"/></th>
                            <td>${movie.releaseDate}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.runtime"/></th>
                            <td>${movie.runtime}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.budget"/></th>
                            <td><fmt:formatNumber value="${movie.budget}"/>$</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.genres"/></th>
                            <td>${movie.genres}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.country"/></th>
                            <td>${movie.countries}</td>
                        </tr>
                    </table>
                    <h2>Description</h2>
                    <div>
                        <p>${movie.description}</p>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="fragments/footer.jsp" %>
    </body>
</html>
