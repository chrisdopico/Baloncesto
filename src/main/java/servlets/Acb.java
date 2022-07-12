package servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import modelo.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    private static final Logger log = LogManager.getLogger(Acb.class);

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        String nombreP = req.getParameter("txtNombre");
        String nombre = req.getParameter("R1");
        PrintWriter out = res.getWriter();

        // Cuando se pulse botón Votar
        if (req.getParameter("B1") != null) {
            if (nombre.equals("Otros")) {
                nombre = req.getParameter("txtOtros");
            }
            if (bd.existeJugador(nombre)) {
                bd.actualizarJugador(nombre);
            } else {
                bd.insertarJugador(nombre);
            }
            s.setAttribute("nombreCliente", nombreP);
            // Llamada a la página jsp que nos da las gracias
            res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
        }
        // Cuando se pulse el botón Reset
        else if (req.getParameter("B2") != null) {
            try {
                boolean indicador = bd.votosACero();
                if (indicador) {
                    out.print("Votos a Cero");
                } else {
                    out.print("Error");
                }

                // Llamada a la página jsp que nos avisa que se han puesto los votos a cero
                res.sendRedirect(res.encodeRedirectURL("VotosCero.jsp"));

            } catch (NumberFormatException e) {
                log.info("Number Format Exception" + e);
            } catch (IndexOutOfBoundsException e) {
                log.info("Index out of bounds Exception" + e);
            } finally {
                out.close();
            }
        }
        // Cuando se pulse el botón Ver Tabla
        else if (req.getParameter("B3") != null) {
            try {
                // Llamada a la página jsp que nos avisa que se han puesto los votos a cero
                res.sendRedirect(res.encodeRedirectURL("ListaVotos.jsp"));
            } catch (NumberFormatException e) {
                out.println("Number Format Exception" + e);
            } catch (IndexOutOfBoundsException e) {
                log.info("Index out of bounds Exception" + e);
            } finally {
                out.close();
            }
        } else {
            // Llamada a la página jsp que nos da las gracias
            res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
        }

    }

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
