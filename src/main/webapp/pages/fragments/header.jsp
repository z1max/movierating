<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <div class="logo-container">
        <a class="logo" href="${pageContext.request.contextPath}">Movie Rating</a>
    </div>
    <div class="menu-container">
        <c:choose>
            <c:when test="${not empty activeUser}">
                <c:if test="${activeUser.isAdmin()}">
                    <div class="menu-item">
                        <a href="front?command=AdminPage"><fmt:message key="title.adminPage"/></a>
                    </div>
                </c:if>
                <div class="menu-item">
                    <a style="color:white" href="front?command=Profile">${activeUser.username}</a>
                </div>
                <div class="menu-item">
                    <form method="post" action="front?command=Logout">
                        <input type="submit" value="Logout">
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="menu-item">
                    <a style="color: white" href="front?command=Signin"><fmt:message key="title.signin"/></a>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="menu-item">
            <form>
                <select id="language" name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
        </div>
    </div>
</header>
