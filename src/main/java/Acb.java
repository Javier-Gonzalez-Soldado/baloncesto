
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);

        //Si se ha pulsado el botón de ver votos
        if (req.getParameter("verVotos") != null) {
            // Llamada a la página jsp que muestra una tabla con los votos
            List<Jugador> jugadores = bd.obtenerVotos();
            s.setAttribute("jugadores", jugadores);
            res.sendRedirect(res.encodeRedirectURL("VerVotos.jsp"));
        }


        String nombreP = (String) req.getParameter("txtNombre");
        String nombre = (String) req.getParameter("R1");
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

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
