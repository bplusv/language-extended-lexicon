 <%-- 
    Document   : explore
    Created on : May 9, 2012, 11:27:14 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

            <form action="<c:url value="/explore" />" id="exploreForm" method="get">
                <div id="filters">
                    <div class="exploreField">
                        <label id="classificationLabel" for="classification">Classification:</label>
                        <select id="classification" name="cl" onchange="$('#exploreForm').submit();">
                            <option value="">All</option>
                            <c:forEach var="classification" items="${classifications}">
                                <option value="${classification.id}" ${param.cl == classification.id ? 'selected="selected"' : ''}>${classification.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exploreField">
                        <label id="categoryLabel" for="category">Category:</label>
                        <select id="category" name="ca" onchange="$('#exploreForm').submit();">
                            <option value="">All</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}" ${param.ca == category.id ? 'selected="selected"' : ''}>${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="exploreField">
                        <input id="search" type="text" name="co" value="${param.co}" />
                        <input id="doSearch" type="submit" class="button" value=""/>
                    </div>
                </div>
                <table id="conceptsTable">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Classification</th>
                            <th>Category</th>
                            <th>Document</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="concept" items="${conceptManager.getConcepts(param.cl, param.ca, param.co)}">
                            <tr>
                                <td><a href="<c:url value="/classify?co=${concept.id}" />">${concept.name}</a></td>
                                <td>${concept.definition.classification.name}</td>
                                <td>${concept.definition.category.name}</td>
                                <td>${concept.document.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>