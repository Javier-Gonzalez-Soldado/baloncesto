
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

        //Obtenemos el método de la petición
        String metodo = req.getMethod();
        
        if (metodo.equals("POST")) {
            // Si es un POST, procesamos los datos
            procesarPost(req, res);

        }else if(metodo.equals("DELETE")){
            procesarDelete(req, res);
        }
        else {
            // Si es un GET u otro, redirigimos a la página de inicio
            res.sendRedirect(res.encodeRedirectURL("index.html"));
        }
    }

    private void procesarPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
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

    private void procesarDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        bd.ponerVotosCero();
        res.sendRedirect(res.encodeRedirectURL("index.html"));
    }

    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
