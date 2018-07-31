<%@ taglib prefix="ldt" uri="WEB-INF/localDateTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="title.home"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content">
                <%@ include file="fragments/error.jsp"%>
                <c:forEach items="${movies}" var="movie">
                    <jsp:useBean id="movie" scope="page" type="by.epam.dto.LazyMovie"/>
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
                                        <td><ldt:getFormatted localDate="${movie.releaseDate}" locale="${language}"/></td>
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
    </body>
</html>
