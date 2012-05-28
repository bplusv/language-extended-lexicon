 <%-- 
    Document   : explore
    Created on : May 9, 2012, 11:27:14 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <form action="<c:url value="/explore" />" id="exploreForm" method="get">
                <div id="filters">
                    <div class="exploreField">
                        <label id="classificationLabel" for="classification"><fmt:message key="classification" />:&nbsp;</label>
                        <select id="classification" name="cl" onchange="$('#exploreForm').submit();">
                            <option value=""><fmt:message key="all" /></option>
                            <c:forEach var="classification" items="${classifications}">
                                <option value="${classification.id}" ${param.cl == classification.id ? 'selected="selected"' : ''}><fmt:message key="${classification.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exploreField">
                        <label id="categoryLabel" for="category"><fmt:message key="category" />:&nbsp;</label>
                        <select id="category" name="ca" onchange="$('#exploreForm').submit();">
                            <option value=""><fmt:message key="all" /></option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}" ${param.ca == category.id ? 'selected="selected"' : ''}><fmt:message key="${category.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exploreField">
                        <input id="search" type="text" maxlength="255" name="co" value="${param.co}" />
                        <input id="doSearch" type="submit" class="button" value=""/>
                    </div>
                </div>
                <table id="conceptsTable">
                    <thead>
                        <tr>
                            <th><fmt:message key="concept" /></th>
                            <th><fmt:message key="classification" /></th>
                            <th><fmt:message key="category" /></th>
                            <th><fmt:message key="document" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="concept" items="${conceptManager.getConceptsByFilters(param.cl, param.ca, param.co)}">
                            <tr>
                                <td class="overflowEllipsis"><a href="<c:url value="/classify"><c:param name="co" value="${concept.id}" /></c:url>">${concept.name}</a></td>
                                <td><fmt:message key="${concept.definition.classification.name}" /></td>
                                <td><fmt:message key="${concept.definition.category.name}" /></td>
                                <td class="overflowEllipsis">${concept.document.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>