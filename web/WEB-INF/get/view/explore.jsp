<%-- 
   Document   : explore
   Created on : May 9, 2012, 11:27:14 AM
   Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<div id="exMain">
        <div id="exCommandPanelWrapper">
            <div id="commandPanel">
                <div id="exCommandPanelTop">
                    <form id="exProjectReportField" action="get/projectReport" method="get">
                        <input id="exProjectReport" class="button" type="submit" value="<fmt:message key="report" />" />
                        <label id="exProjectReportComments"><input id="exProjectReportCommentsCheck" type="checkbox" name="comments" />&nbsp;<fmt:message key="comments" /></label>
                    </form>
                    <a id="exManageSymbol" class="button" href="#!/classify?na=new"><fmt:message key="new symbol" /></a>
                    
                    <a id="exManageProjects" class="button" href="#!/manageProjects"><fmt:message key="projects" /></a>
                </div>
                <form id="exForm" action="/get/data/exploreSymbols" method="get">
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
                            <input id="exSearch" type="text" name="sy" maxlength="255" value="<c:out value="${param.sy}" />" />
                            <span id="exSearchClear">&#215;</span>
                        </div>
                    </div>
                </form>
                            
                <h3 id="exSymbolsListHeader">
                    <span><fmt:message key="symbol" /></span>
                    <span><fmt:message key="category" /></span>
                    <span><fmt:message key="classification" /></span>
                    <span><fmt:message key="document" /></span>
                </h3>
            </div>
        </div>

       <ul id="exSymbolsList">
            <c:set var="symbols" value="${symbolFacade.findByFilters(project.id, param.ca, param.cl, param.sy)}" />
            <c:forEach var="symbol" items="${symbols}" varStatus="iter">
                <li class="${iter.index % 2 == 0 ? 'rowEven' : 'rowOdd'}">
                    <a class="exSymbol" href="#!/classify?sy=${symbol.id}">
                        <span class="overflowEllipsis exSyName"><c:out value="${symbol.name}" /></span>
                        <span class="overflowEllipsis"><fmt:message key="${symbol.definition.category.name}" /></span>
                        <span class="overflowEllipsis"><fmt:message key="${empty symbol.definition.classification.name ? 'n/a' : symbol.definition.classification.name}" /></span>
                        <span class="overflowEllipsis"><c:out value="${empty symbol.document.name ? 'n/a' : symbol.document.name}" /></span> <%-- Cesar : no document symbol --%>
                        <span class="removeSymbol" data-symbol.id="${symbol.id}" data-symbol.name="${symbol.name}">&#215;</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${empty symbols}" >
            <h1 id="exEmptySymbolsListMessage"><fmt:message key="it's lonely here" />...</h1>
        </c:if>
</div>