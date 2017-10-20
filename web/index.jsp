<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
        <title>Live Journal</title>
    </head>
    <body>
        
        <section id="loginsection">
            <div id="esquerda">
                <div class="msg">
                    Welcome,
                    <p>let's get signed in!</p>
                </div>
            </div>
            <div id="direita">
                <form action="Controller" method="POST">
			<p>
                            
                            <input type="text" name="username" id="username" placeholder="Username" value="${cookie.username.value}"/>
			</p>
			<p>
                            
                            <input type="password" name="password" id="password" placeholder="Password"/>
			</p>
			<p>
                            <input type="radio" name="remember" id="remember"/>Remember Password</label>
			</p>
                        <input type="hidden" name="command" value="Usuario.login"/>
			<p>
                            <input type="submit" value="SIGN IN"/>
			</p>
			<p>
                            
                            <a href="register.jsp">Sign up</a>
                        </p>
                </form>
            </div>	
	 </section>
         <div id="nome-projeto">
                Live Journal            
         </div>
        
    </body>
</html>
