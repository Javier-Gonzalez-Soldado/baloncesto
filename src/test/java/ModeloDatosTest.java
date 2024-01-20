import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.contrib.java.lang.system.EnvironmentVariables;


public class ModeloDatosTest {

    @Rule
    public final EnvironmentVariables environmentVariables
      = new EnvironmentVariables();

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        instance.abrirConexion();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");

        //setear variables de entorno
        environmentVariables.set("DATABASE_HOST", "jdbc:mysql://localhost");
        environmentVariables.set("DATABASE_PORT", "3306");
        environmentVariables.set("DATABASE_NAME", "baloncesto");
        environmentVariables.set("DATABASE_USER", "usuario");
        environmentVariables.set("DATABASE_PASS", "clave");

        ModeloDatos instance = new ModeloDatos();
        instance.abrirConexion();

        String nombre = "Llull";
        int votosLlullPrev = instance.getVotosJugador(nombre);

        instance.actualizarJugador(nombre);

        int votosLlullPost = instance.getVotosJugador(nombre);

        assertEquals(votosLlullPrev + 1, votosLlullPost);

        //resetear votos
        instance.reiniciarVotos();
    }
}