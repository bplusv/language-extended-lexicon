 <%-- 
    Document   : explore
    Created on : May 9, 2012, 11:27:14 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form id="exForm" action="/get/view/explore" method="GET">
                <div id="exFilters">
                    <div class="exField">
                        <label id="exCategoryLabel" for="exCategory"><fmt:message key="category" />:&nbsp;</label>
                        <select id="exCategory" name="ca" onchange="$('#exForm').submit();">
                            <option value=""><fmt:message key="all" /></option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}" ${param.ca == category.id ? 'selected="selected"' : ''}><fmt:message key="${category.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exField">
                        <label id="exClassificationLabel" for="exClassification"><fmt:message key="classification" />:&nbsp;</label>
                        <select id="exClassification" name="cl" onchange="$('#exForm').submit();">
                            <option value=""><fmt:message key="all" /></option>
                            <c:forEach var="classification" items="${classifications}">
                                <option value="${classification.id}" ${param.cl == classification.id ? 'selected="selected"' : ''}><fmt:message key="${classification.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exField">
                        <input id="exSearch" type="text" name="sy" maxlength="255" value="${param.sy}" />
                        <input id="exDoSearch" type="submit" class="button" value=""/>
                    </div>
                </div>
                <table id="exSymbolsTable" cellspacing="0">
                    <thead>
                        <tr>
                            <th width="182"><fmt:message key="symbol" /></th>
                            <th width="182"><fmt:message key="category" /></th>
                            <th width="182"><fmt:message key="classification" /></th>
                            <th width="182"><fmt:message key="document" /></th>
                            <th width="20" class="syRem" style="width: 20px;"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="symbol" items="${symbolFacade.findByFilters(project.id, param.ca, param.cl, param.sy)}" varStatus="iter">
                            <tr>
                                <td colspan="5" style="background-color:${iter.index % 2 == 0 ? '#fff' : '#f9f9f9'};">
                                    <a class="exSymbolsRow" href="#!/classify?sy=${symbol.id}">
                                        <span class="overflowEllipsis">${symbol.name}</span>
                                        <span class="overflowEllipsis"><fmt:message key="${symbol.definition.category.name}" /></span>
                                        <span class="overflowEllipsis"><fmt:message key="${empty symbol.definition.classification.name ? 'n/a' : symbol.definition.classification.name}" /></span>
                                        <span class="overflowEllipsis">${symbol.document.name}</span>
                                        <span id="exSy${symbol.id}" class="removeSymbol">x</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>