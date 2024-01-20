import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ModeloDatosTest {

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
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