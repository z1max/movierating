<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty errorMessageKey}">
    <div class="error">
        <p>
            <fmt:message key="${errorMessageKey}">
                <c:if test="${not empty errorParam}">
                    <fmt:param value="${errorParam}"/>
                </c:if>
            </fmt:message>
        </p>
    </div>
</c:if>
