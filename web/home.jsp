<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Home - Live Journal</title>
    </head>
    <body>
        <c:if test="${user == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <section id="mainContainer">
            <aside>
                
               <%@include file="WEB-INF/jspf/menu.jspf"%>
                
                <form id="formPost" action="Controller" method="POST">
                    <p>
                    <p>I'm your journal, tell me about your day!</p>
                        <textarea name="posttext"></textarea>
                    </p>
                    
                    <p>How are you feeling?</p>
                    
                    <select name="humor">
                        <c:forEach items="${humores}" var="humor">
                            <option value="${humor.id_humor}">${humor.humor_nome}</option>
                        </c:forEach>
                    </select>
         
                    <input type="hidden" name="command" value="Post.post"/>
                    <p>
                        <input type="submit" value="POST"/>
                    </p>
                    <article id="msgSection">
                        ${msg}
                        <c:set var="msg" value="" scope="session"></c:set>
                    </article>
                    
                     <c:if test="${user.username == 'ADMIN'}">
                        <p id="admin"><a href="Controller?command=Post.removeall">Erase all posts</a></p>
                     </c:if>
                </form>
            </aside>
            <section id="mainSection">
                <c:forEach var="post" items="${user.posts}">
                    <article class="post-container">
                        <span class="feeling" style="background-color: ${post.cor}">Feeling ${post.feels}</span>
                        <p class="post" style="background-color: ${post.cor}">${post.posttext}</p>
                        
                        <p class="update-link"><a href="updatePost.jsp?postId=${post.id_post}&postText=${post.posttext}">Update</a></p>
                    </article>
                </c:forEach>
            </section>
        </section>
    </body>
</html>
