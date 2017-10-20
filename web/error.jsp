<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <h1>ERROR</h1>
        <p>${errormsg}</p>
        <c:set var="errormsg" value="" scope="session"></c:set>
    </body>
</html>
