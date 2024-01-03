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
    public void testReiniciarVotos(){
        System.out.println("Prueba de reiniciarVotos");
        ModeloDatos instance = new ModeloDatos();
        instance.reiniciarVotos();
        // fail("Fallo forzado.");
    }

    @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        instance.actualizarJugador(nombre);
        // fail("Fallo forzado.");
    }

    @Test
    public void testInsertarJugador() {
        System.out.println("Prueba de insertarJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        instance.insertarJugador(nombre);
        // fail("Fallo forzado.");
    }

    @Test
    public void testAbrirConexion() {
        System.out.println("Prueba de abrirConexion");
        ModeloDatos instance = new ModeloDatos();
        instance.abrirConexion();
        // fail("Fallo forzado.");
    }

    @Test
    public void testCerrarConexion() {
        System.out.println("Prueba de cerrarConexion");
        ModeloDatos instance = new ModeloDatos();
        instance.cerrarConexion();
        // fail("Fallo forzado.");
    }
}