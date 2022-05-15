<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="modelo.ModeloDatos" %>
<%@ page import="modelo.Jugador" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<html>
    <head><title>Ver votos</title></head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="estilos.css" rel="stylesheet" type="text/css" />

    <body class="listado">
        <h1>Tabla de Votos</h1>
        <hr>
        <div>
            <%
                ModeloDatos bd = new ModeloDatos();
                ArrayList<Jugador> jugadores = bd.getJugadores();
                Iterator<Jugador> listado = jugadores.iterator();
            %>
            <table>
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
                        <td id="contador<%=contador%>">
                            <%=contador%>
                        </td>                    
                        <td id="jugador<%=contador%>">
                            <%=jugador.getNombre()%>
                        </td>
                        <td id="votes<%=contador%>">
                            <%=jugador.getTotalVotos()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>
