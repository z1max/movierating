<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <div class="logo-container">
        <a class="logo" href="${pageContext.request.contextPath}">Movie Rating</a>
    </div>
    <div class="menu-container">
        <div class="menu-item">
            <c:choose>
                <c:when test="${not empty activeUser}">
                    <a style="color:white" href="front?command=Profile">${activeUser.username}</a>
                    <form method="post" action="front?command=Logout">
                        <input type="submit" value="Logout">
                    </form>
                </c:when>
                <c:otherwise>
                    <a style="color: white" href="front?command=Signin"><fmt:message key="title.signin"/></a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="menu-item">
        <form method="post" action="front?command=ChangeLanguage">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
        </form>
        </div>
    </div>
</header>
