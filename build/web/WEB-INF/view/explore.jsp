 <%-- 
    Document   : explore
    Created on : May 9, 2012, 11:27:14 AM
    Author     : Luis Salazar <bp.lusv@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <style type="text/css">
            .exploreField {
            display: inline;
            margin: 0 12px;
            vertical-align: middle;
        }

        .exploreField > label{
            margin-right: 5px;
        }

        .exploreField > select {
            width: 120px;
        }

        #category {

        }

        #categoryLabel {

        }

        #symbols {
            height: 450px;
            overflow-y: scroll;
        }

        #symbolsTable {
            color: #555;
            font: 13px/24px 'Century Gothic', sans-serif;
            height: auto;
            table-layout: fixed;
            width: 748px;
        }

        #symbolsTable a {
            color: #555;
            display: block;
            font: 13px/24px 'Century Gothic', sans-serif;
            text-decoration: none;
        }

        #symbolsTable th {
            color: #222;
            font: 15px/24px 'Century Gothic', sans-serif;
        }

        #symbolsTable tr {
            height: 30px;
        }

        #symbolsTable td {
            height: 30px;
        }

        .symbolsRow {
            color: #555;
            display: block;
            font: 13px/24px 'Century Gothic', sans-serif;
            height: 30px;
            z-index: 2;
        }

        .symbolsRow:hover {
            background-color: #FFE0C6;
        }

        .symbolsRow:hover span {
            color: #000;
        }

        .symbolsRow span.overflowEllipsis {
            background-color: transparent;
            border-color: #fff;
            border-style: solid;
            border-width: 0 1px;
            display: block;
            float: left;
            height: 30px;
            padding: 0 8px;
            width: 168px;
            z-index: 1;
        }

        #classification {

        }

        #classificationLabel {

        }

        #doSearch {
            background-image: url('../img/searchIcon.png');
            background-repeat: no-repeat;
            background-position: center center;
            height: 30px;
            padding: 0;
            vertical-align: bottom;
            width: 30px;
        }

        #exploreForm {

        }

        #filters {
            display: block;
            height: 30px;
            margin-bottom: 25px;
            text-align: center;
            width: auto;
        }

        #search {
        width: 120px; 
        }
    </style>
            <form action="<c:url value="/explore" />" id="exploreForm" method="get">
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
                                    <a class="symbolsRow" href="<c:url value="/classify"><c:param name="sy" value="${symbol.id}"/></c:url>">
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