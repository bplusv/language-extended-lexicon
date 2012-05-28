<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style type="text/css">
    
</style>

<p>${pageContext.request.locale.language}</p>

<a id="infoBubble">
    <span>${requestScope.foo}</span>
    <div id="infoBubbleArrow"></div>
</a>