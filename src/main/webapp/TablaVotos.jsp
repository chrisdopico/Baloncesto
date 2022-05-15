<!DOCTYPE html>
<html lang="en">
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Votaci&oacute;n mejor jugador liga ACB</title>
        <link href="estilos.css" rel="stylesheet" type="text/css" />
        
    </head>
    <body class="resultado">
        <font size=10>
        Votaci&oacute;n al mejor jugador de la liga ACB
        <hr>
        <%
            String nombreP = (String) session.getAttribute("nombreCliente");
        %>
        <br>Muchas gracias <%=nombreP%> por su voto
        </font>
        <br>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>
