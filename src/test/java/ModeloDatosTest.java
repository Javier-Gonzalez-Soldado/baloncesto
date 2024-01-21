import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class ModeloDatosTest {

    @BeforeAll
    public static void init() {
        System.out.println("Configurando las variables para la base de datos...");
        //setear variables de entorno para los tests
        System.setProperty("DATABASE_HOST", "jdbc:mysql://localhost");
        System.setProperty("DATABASE_PORT", "3306");
        System.setProperty("DATABASE_NAME", "baloncesto");
        System.setProperty("DATABASE_USER", "usuario");
        System.setProperty("DATABASE_PASS", "clave");
    }


    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "Rudy";
        ModeloDatos instance = new ModeloDatos();
        instance.abrirConexion();
        boolean expResult = true;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");


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