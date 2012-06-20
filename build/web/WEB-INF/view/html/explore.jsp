 <%-- 
    Document   : explore
    Created on : May 9, 2012, 11:27:14 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form action="<c:url value="/explore" />" id="exploreForm" method="GET" onsubmit="return controller('explore', $(this).serialize());">
                <div id="filters">
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
                        <label id="classificationLabel" for="classification"><fmt:message key="classification" />:&nbsp;</label>
                        <select id="classification" name="cl" onchange="$('#exploreForm').submit();">
                            <option value=""><fmt:message key="all" /></option>
                            <c:forEach var="classification" items="${classifications}">
                                <option value="${classification.id}" ${param.cl == classification.id ? 'selected="selected"' : ''}><fmt:message key="${classification.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exploreField">
                        <input id="search" type="text" maxlength="255" name="sy" value="${param.sy}" />
                        <input id="doSearch" type="submit" class="button" value=""/>
                    </div>
                </div>
                <table id="symbolsTable">
                    <thead>
                        <tr>
                            <th><fmt:message key="symbol" /></th>
                            <th><fmt:message key="category" /></th>
                            <th><fmt:message key="classification" /></th>
                            <th><fmt:message key="document" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="symbol" items="${symbolManager.getSymbolsByFilters(project.id, param.cl, param.ca, param.sy)}" varStatus="iter">
                            <tr>
                                <td colspan="4" style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
                                    <a class="symbolsRow" href="#!nav=classify&sy=${symbol.id}">
                                        <span class="overflowEllipsis">${symbol.name}</span>
                                        <span class="overflowEllipsis"><fmt:message key="${symbol.definition.category.name}" /></span>
                                        <span class="overflowEllipsis"><fmt:message key="${empty symbol.definition.classification.name ? 'n/a' : symbol.definition.classification.name}" /></span>
                                        <span class="overflowEllipsis">${symbol.document.name}</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>