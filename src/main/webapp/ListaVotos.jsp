<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="Jugador"%>
<%@ page import="ModeloDatos"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE> 
<html lang="es">
    <head><title>Ver votos</title></head>
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

    <body class="listado">
        <h1>Tabla de Votos</h1>
        <hr>
        <div class="container">
            <%
                ModeloDatos bd = new ModeloDatos();
                ArrayList<Jugador> jugadores = bd.getJugadores();
                Iterator<Jugador> listado = jugadores.iterator();
            %>
            <table class="table">
                <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Total Votos</th>
                </thead>
                <tbody>
                    <%
                        int contador = 0;
                        Jugador jugador = new Jugador();
                        while(listado.hasNext()){
                            contador++;
                            jugador = listado.next();
                        
                    %>
                    <tr>
                        <td id="contador<%=contador%>"><%=contador%></td>                    
                        <td id="jugador<%=contador%>"><%=jugador.getNombre()%></td>
                        <td id="votes<%=contador%>"><%=jugador.getTotalVotos()%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <br> <a href="index.html" class="btn btn-primary">Volver</a>
    </body>
</html>
