<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%--<sql:query var="concepts" dataSource="jdbc/lel">
        SELECT * FROM concept WHERE id = ?;
        <sql:param value="${pageContext.request.queryString}" />
    </sql:query>--%>
    <h1>${selectedConceptClassification.id} - ${selectedConceptClassification.name}</h1>
    <c:forEach var="conceptClassification" items="${conceptClassifications}">
        <p>${conceptClassification.id} - ${conceptClassification.name}</p>
    </c:forEach>