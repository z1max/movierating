<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="title.details"/> </title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content">
                <%@ include file="fragments/error.jsp" %>
                <div class="movie-details">
                    <h1>${movie.title}</h1>
                    <c:if test="${activeUser.isAdmin()}">
                        <div>
                            <a href="front?command=EditMovie&movieId=${movie.id}"><fmt:message key="movie.edit"/></a>
                            <form method="post" action="front?command=DeleteMovie">
                                <input type="hidden" name="movieId" value="${movie.id}">
                                <input type="submit" value="<fmt:message key="movie.delete"/>">
                            </form>
                        </div>
                    </c:if>
                    <div>
                        <form method="post" action="front?command=Rate" onchange="submit()">
                            <input type="hidden" name="movieId" value="${movie.id}">
                            <label for="user-rating"><fmt:message key="movie.userRating"/></label>
                            <select id="user-rating" name="rating" ${not empty userRating ? 'disabled' : ''}>
                                <c:forEach var="i" begin="1" end="10">
                                    <option value="${i}" ${not empty userRating and userRating == i ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </form>
                    </div>
                    <table class="table">
                        <tr>
                            <th><fmt:message key="movie.rating"/></th>
                            <td>${movie.rating}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.director"/></th>
                            <td>${movie.director}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="movie.releaseDate"/></th>
                            <td><fmt:formatDate value="${movie.releaseDate}"/></td>
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
                <div>
                    <h2>Comments</h2>
                    <c:forEach items="${movie.reviews}" var="review">
                        <div>
                            <div>
                                    ${review.username}
                                    ${review.date}
                            </div>
                            <div>
                                    ${review.comment}
                            </div>
                        </div>
                        <hr/>
                    </c:forEach>
                </div>
                <div>
                    <h2>Write review</h2>
                    <form id="review" method="post" action="front?command=WriteReview">
                        <input type="hidden" name="movieId" value="${movie.id}">
                        <input type="submit">
                    </form>
                    <textarea form="review" name="review"></textarea>
                </div>
            </div>
        </section>
        <%@ include file="fragments/footer.jsp" %>
    </body>
</html>
