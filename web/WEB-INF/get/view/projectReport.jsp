<%-- 
    Document   : projectReport
    Created on : Sep 25, 2012, 11:42:48 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>
<h1><c:out value="${project.name}" /></h1>
<c:forEach var="symbol" items="${projectFacade.getSymbolCollection(project.id)}" varStatus="iter">
    <div><c:out value="${symbol.name}" /></div>
    <div><c:out value="${symbol.document.name}" /></div>
    <div><c:out value="${symbol.definition.classification.name}" /></div>
    <div><c:out value="${symbol.definition.category.name}" /></div>
    <div><c:out value="${symbol.definition.notion}" /></div>
    <div><c:out value="${symbol.definition.actualIntention}" /></div>
    <div><c:out value="${symbol.definition.futureIntention}" /></div>
    <hr />
</c:forEach>