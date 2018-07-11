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
            <div class="content">
                <form id="movie-form" method="post" action="front?command=EditMovie">
                    <jsp:useBean id="movie" scope="request" class="by.z1max.model.Movie"/>

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
                    <select id="genre-select">
                        <c:forEach items="${genres}" var="genre">
                            <option value="${genre}">${genre.toString()}</option>
                        </c:forEach>
                    </select>
                    <button type="button" onclick="addEnumItem('genre-select', 'genres')">Add genre</button>
                    <div id="genres">
                        Genres

                        <c:if test="${not empty movie.genres}">
                            <c:forEach items="${movie.genres}" var="genre">
                                <div>
                                    <input type="text" readonly="readonly" name="genres" value="${genre}">
                                    <button type="button" onclick="remove(this)">Remove</button>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <select id="country-select">
                        <c:forEach items="${countries}" var="country">
                            <option value="${country}">${country.getName()}</option>
                        </c:forEach>
                    </select>
                    <button type="button" onclick="addEnumItem('country-select', 'countries')">Add Country</button>
                    <div id="countries">
                        Countries
                        <c:if test="${not empty movie.countries}">
                            <c:forEach items="${movie.countries}" var="country">
                                <div>
                                    <input type="text" readonly="readonly" name="countries" value="${country}">
                                    <button type="button" onclick="remove(this)">Remove</button>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <input type="submit">
                </form>
                <textarea name="description" form="movie-form">${movie.description}</textarea>
            </div>
        </section>
        <%@ include file="fragments/footer.jsp" %>
        <script src="${pageContext.request.contextPath}/resources/js/enumHelper.js"></script>
    </body>
</html>

