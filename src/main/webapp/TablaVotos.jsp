<!DOCTYPE> 
<html lang="es">
    <head><title>Votaci&oacute;n mejor jugador liga ACB</title></head>
    <link href="estilos.css" rel="stylesheet" type="text/css" />
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
    rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous"
    />
    <link
    rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
    integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
    crossorigin="anonymous"
    />

    <body class="resultado">
        <p style="font-size: 40;">
            Votaci&oacute;n al mejor jugador de la liga ACB
        </p>
        <hr>
        <p style="font-size: 40;">
        <%
            String nombreP = (String) session.getAttribute("nombreCliente");
        %>
        <br>Muchas gracias <%=nombreP%> por su voto
        </p>
        <br>
        <br> <a id="goBack" href="index.html"> Ir al comienzo</a>
    </body>
</html>
