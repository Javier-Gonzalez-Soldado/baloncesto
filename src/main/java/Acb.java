
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);

        // Si se ha pulsado el botón de reiniciar votos, se reinician y se redirige a la página principal
        if (req.getParameter("resetVotos") != null && req.getParameter("resetVotos").equals("true")) {
            bd.reiniciarVotos();
            res.sendRedirect(res.encodeRedirectURL("index.html"));
            return;
        }

        String nombreP =  req.getParameter("txtNombre");
        String nombre =  req.getParameter("R1");
        if (nombre.equals("Otros")) {
            nombre =  req.getParameter("txtOtros");
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

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
