<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp"%>
        <title>Movie Rating | <fmt:message key="title.unknown"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp"%>
        <section>
            <div class="content">
                <%@ include file="fragments/error.jsp"%>
            </div>
        </section>
        <%@ include file="fragments/footer.jsp"%>
    </body>
</html>


