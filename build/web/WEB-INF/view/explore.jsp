<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <form action="<c:url value="/explore" />" id="exploreForm" method="get">
                <div class="exploreField">
                    <label id="classificationLabel" for="classification">Classification:</label>
                    <select id="classification" name="classification" onchange="$('#exploreForm').submit();">
                        <option value="%">All</option>
                        <c:forEach var="classification" items="${applicationScope.classifications}">
                            <option value="${classification.id}" ${param.classification == classification.id ? 'selected="selected"' : ''}>${classification.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="exploreField">
                    <label id="categoryLabel" for="category">Category:</label>
                    <select id="category" name="category" onchange="$('#exploreForm').submit();">
                        <option value="%">All</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}" ${param.category == category.id ? 'selected="selected"' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="exploreField">
                    <input id="search" type="text" name="searchQuery" />
                    <input id="doSearch" type="submit" class="button" value="Search" />
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
                    <c:forEach var="concept" items="${requestScope.concepts}">
                        <tr>
                            <td>${concept.name}</td>
                            <td>${concept.definition.classification.name}</td>
                            <td>${concept.definition.category.name}</td>
                            <td>${concept.document.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input id="edit" type="submit" name="edit" value="Edit" class="button" />
                <input id="delete" type="submit" name="delete" value="Delete" class="button" />
            </form>
