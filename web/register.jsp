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
                    Thanks!
                    <p>You are just one step away from your Journal.</p>
                </div>
            </div>
            <div id="direita">
              
            <form action="Controller" method="POST">
                <p>
                    <label for="username">Username:</label><br>
                    <input type="text" name="username" id="username" placeholder="username" required/>
                </p>
                <p>
                    <label for="password">Password:</label><br>
                    <input type="password" name="password" id="password" placeholder="password" required/>
                </p>
                <p>
                    <label for="password2">Confirm Password:</label><br>
                    <input type="password" name="password2" id="password2" placeholder="confirm password" required/>
                </p>
                
                <p>
                    <label for="firstname">First Name:</label><br>
                    <input type="text" name="firstname" id="firstname" 
                           placeholder="first name" required/>
                </p>
                
                <p>
                    <label for="lastname">Last Name:</label><br>
                    <input type="text" name="lastname" id="lastname" 
                           placeholder="last name" required/>
                </p>
                
                <p>
                    <label for="email">Email:</label><br>
                    <input type="email" name="email" id="email" 
                           placeholder="email" required/>
                </p>
                
                <p>
                    <label for="birthday">Birthday:</label><br>
                    <input type="date" name="birthday" id="birthday" placeholder="birthday" required/>
                </p>
   
                <input type="hidden" name="command" value="Usuario.register"/>
                <p>
                    <input type="submit" value="SIGN UP"/>
                </p>
            </form>
            </div>	
	 </section>
         <div id="nome-projeto">
                Live Journal            
         </div> 
         <div id="about-link">
             <a href="index.jsp">Back</a>               
         </div>
                        
        
        
    </body>
</html>
