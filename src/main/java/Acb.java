
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        String nombreP = (String) req.getParameter("txtNombre");
        String nombre = (String) req.getParameter("R1");
        PrintWriter out = res.getWriter();

        // Cuando se pulse botón Votar
        if (req.getParameter("B1") != null) {
            if (nombre.equals("Otros")) {
                nombre = (String) req.getParameter("txtOtros");
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
        // Cuando se pulse el votón Reset
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
                out.println("Number Format Exception" + e);
            } catch (IndexOutOfBoundsException e) {
                out.println("Index out of bounds Exception" + e);
            } finally {
                out.close();
            }
        }

    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
