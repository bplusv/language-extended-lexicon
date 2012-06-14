<%@page pageEncoding="UTF-8"%>

<style type="text/css">
    #test {
        background-color: #ccc;
        width: 100px;
        height: 20px;
        display: inline-block;
        
    }  
    
    #test:hover {
        background-color: #555;
    }
    
    #test:active {
        background-color: #c00;
    }
    
    
</style>

<!--
<a id="infoBubble">
    <span>${requestScope.foo}</span>
    <div id="infoBubbleArrow"></div>
</a>
-->
<a href="http://www.google.com" id="test">
    <span class="overflowEllipsis" style="background-color: transparent; display:block; height: 20px; width: 100px;">This is a test of a long text.</span>
</a>
