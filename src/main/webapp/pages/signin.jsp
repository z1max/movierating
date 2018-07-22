<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html lang="${language}">
    <head>
        <%@ include file="fragments/meta.jsp"%>
        <title>Movie Rating | <fmt:message key="title.signin"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp"%>
        <section>
            <div class="content">
                <%@ include file="fragments/error.jsp"%>
                <div class="content-small">
                    <form class="form" name="signIn" method="post" action="front?command=Signin" onsubmit="return validate(this, false)">
                        <label for="email"><fmt:message key="user.email"/></label>
                        <input id="email" type="email" name="email">
                        <span id="email-error" class="email-error"><fmt:message key="validation.email"/></span>

                        <label for="password"><fmt:message key="user.password"/></label>
                        <input id="password" type="password" name="password">
                        <span id="password-error" class="password-error"><fmt:message key="validation.password"/></span>

                        <input type="submit" class="button" value="<fmt:message key="title.signin"/>">
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>

