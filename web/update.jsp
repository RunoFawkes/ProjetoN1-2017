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
        <c:if test="${user == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <section id="mainContainer">
            <aside>
                <%@include file="WEB-INF/jspf/menu.jspf"%>
            </aside>
            <section id="mainSection">
            <article>
                <h1>UPDATE</h1>
            <form action="Controller" method="POST">
                <p>
                    <label for="username">Username:</label><br>
                    <input type="text" name="username" id="username" placeholder="username" required value="${user.username}" disabled/>
                </p>
                <p>
                    <label for="password">Password:</label><br>
                    <input type="password" name="password" id="password" placeholder="password" />
                </p>
                <p>
                    <label for="password2">Confirm Password:</label><br>
                    <input type="password" name="password2" id="password2" placeholder="confirm password" />
                </p>
                
                <p>
                    <label for="firstname">First Name:</label><br>
                    <input type="text" name="firstname" id="firstname" placeholder="first name" required value="${user.userprofile.firstname}"/>
                </p>
                
                <p>
                    <label for="lastname">Last Name:</label><br>
                    <input type="text" name="lastname" id="lastname" placeholder="last name" required value="${user.userprofile.lastname}"/>
                </p>
                
                <p>
                    <label for="email">Email:</label><br>
                    <input type="email" name="email" id="email" placeholder="email" required value="${user.userprofile.email}" disabled/>
                </p>
                
                <p>
                    <label for="birthday">Birthday:</label><br>
                    <input type="date" name="birthday" id="birthday" placeholder="birthday" required/>
                </p>
   
                <input type="hidden" name="command" value="Usuario.update"/>
                
                
                <p>
                    <input type="submit" value="UPDATE"/>
                </p>
                
                <p><button onclick="confirmRemove()">Remove Account</button></p>
            </form>
            </article> 
            <script type="text/javascript">
                function confirmRemove() {
                    var resp = confirm("Do you really want to remove tour account?");
                        if(resp) window.location = "Controller?command=Usuario.remove";
                }
            </script>
            </section>
        </section>
    </body>
</html>