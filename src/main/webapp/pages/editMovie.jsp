<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="title.editMovie"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content-small">
                <form class="form" id="movie-form" method="post" action="front?command=EditMovie">
                    <jsp:useBean id="movie" scope="request" class="by.epam.model.Movie"/>

                    <input type="hidden" name="movieId" value="${movie.id}">

                    <label for="title"><fmt:message key="movie.title"/></label>
                    <input id="title" name="title" type="text" value="${movie.title}">

                    <label for="director"><fmt:message key="movie.director"/></label>
                    <input id="director" name="director" type="text" value="${movie.director}">

                    <label for="releaseDate"><fmt:message key="movie.releaseDate"/></label>
                    <input id="releaseDate" name="releaseDate" type="date" value="${movie.releaseDate}">

                    <label for="budget"><fmt:message key="movie.budget"/></label>
                    <input id="budget" name="budget" type="number" value="${movie.budget}">

                    <label for="runtime"><fmt:message key="movie.runtime"/></label>
                    <input id="runtime" name="runtime" type="number" value="${movie.runtime}">

                    <h1><fmt:message key="movie.genres"/></h1>
                    <div class="custom-select">
                        <select id="genre-select" onchange="addEnumItem('genre-select', 'genres')">
                            <c:forEach items="${genres}" var="genre">
                                <option value="${genre.getName()}">${genre.getName()}</option>
                            </c:forEach>
                        </select>
                        <div class="select-arrow"></div>
                    </div>
                    <div id="genres" class="bordered">
                        <c:if test="${not empty movie.genres}">
                            <c:forEach items="${movie.genres}" var="genre">
                                <div>
                                    <input type="text" readonly="readonly" name="genres" value="${genre.getName()}">
                                    <button type="button" onclick="remove(this)">Remove</button>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <h1><fmt:message key="movie.countries"/></h1>
                    <div class="custom-select">
                        <select id="country-select" onchange="addEnumItem('country-select', 'countries')">
                            <c:forEach items="${countries}" var="country">
                                <option value="${country.getName()}">${country.getName()}</option>
                            </c:forEach>
                        </select>
                        <div class="select-arrow"></div>
                    </div>
                    <div id="countries" class="bordered">
                        <c:if test="${not empty movie.countries}">
                            <c:forEach items="${movie.countries}" var="country">
                                <div>
                                    <input type="text" readonly="readonly" name="countries"
                                           value="${country.getName()}">
                                    <button type="button" onclick="remove(this)">Remove</button>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <label for="description"><fmt:message key="movie.description"/></label>
                    <textarea class="review" name="description" id="description"
                              form="movie-form">${movie.description}</textarea>
                    <input class="button" type="submit" value="<fmt:message key="button.submit"/>">
                </form>
            </div>
        </section>
        <script src="${pageContext.request.contextPath}/resources/js/enumHelper.js"></script>
    </body>
</html>

