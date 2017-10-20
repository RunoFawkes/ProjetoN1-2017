<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Live Journal</title>
        <meta charset="utf-8"/>
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>
        <section id="mainContainer">
            <aside>
                <%@include file="WEB-INF/jspf/menu.jspf"%>
            </aside>
            <section id="mainSection">
            <article>
                <h1>UPDATE POST</h1>
            <form action="Controller" method="POST">
                <p>
                    <label for="posttext">Post:</label><br>
                    <textarea id="update-text" name="posttext"><%= request.getParameter("postText") %></textarea>
                </p>
                <select name="humor">
                     <c:forEach items="${humores}" var="humor">
                         <option value="${humor.id_humor}">${humor.humor_nome}</option>
                     </c:forEach>
                </select>
                <input type="hidden" name="postId" value="<%= request.getParameter("postId") %>"/>
                <input type="hidden" name="command" value="Post.update"/>

                <p><input type="submit" value="UPDATE"/></p>
               
            </form>
                
            <form action="Controller" method="POST">
                <input type="hidden" name="postId" value="<%= request.getParameter("postId") %>"/>
                <input type="hidden" name="command" value="Post.remove"/>
                <p><input type="submit" value="DELETE"/></p>
            </form>
                
            </article>
            </section>
        </section>
    </body>
</html>
