<%@ taglib prefix="ldt" uri="WEB-INF/localDateTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!doctype html>
<html lang=${language}>
    <head>
        <%@ include file="fragments/meta.jsp" %>
        <title>Movie Rating | <fmt:message key="title.profile"/></title>
    </head>
    <body>
        <%@ include file="fragments/header.jsp" %>
        <section>
            <div class="content">
                <%@ include file="fragments/error.jsp" %>
                <div class="content-small">
                    <table class="table medium-font">
                        <tr>
                            <th><fmt:message key="user.username"/></th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="user.email"/></th>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <th><fmt:message key="user.registered"/></th>
                            <td><ldt:getFormatted localDate="${user.registered}" locale="${language}"/></td>
                        </tr>
                        <tr>
                            <th><fmt:message key="user.status"/></th>
                            <td>${user.status}</td>
                        </tr>
                    </table>
                    <div class="content-full button-container">
                        <button class="button button-block" onclick="showForm('change-password')"><fmt:message
                                key="user.changePassword"/></button>
                        <form id="change-password" class="form hidden" name="changePassword" method="post"
                              action="front?command=ChangePassword">
                            <label for="old-password"><fmt:message key="user.oldPassword"/> </label>
                            <input id="old-password" name="old-password" type="password">

                            <label for="new-password"><fmt:message key="user.newPassword"/></label>
                            <input id="new-password" name="new-password" type="password">

                            <input type="submit" class="button" value="<fmt:message key="submit"/>">
                        </form>
                    </div>
                    <hr/>
                    <div class="content-full">
                        <button class="button button-block" onclick="showForm('delete-account')"><fmt:message
                                key="user.deleteAccount"/></button>
                        <form id="delete-account" class="form hidden"
                              onsubmit="return confirm('<fmt:message key="user.sure"/>')" method="post"
                              action="front?command=DeleteAccount">
                            <label for="password"><fmt:message key="user.password"/> </label>
                            <input id="password" name="password" type="password">
                            <input type="submit" class="button" value="<fmt:message key="submit"/>">

                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>

