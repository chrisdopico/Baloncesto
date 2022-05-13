<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="modelo.ModeloDatos" %>
<%@ page import="modelo.Jugador" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head><title>Ver votos</title></head>
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
