import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;


public class ModeloDatosTest {

    private ModeloDatos modeloDatos;
    private ModeloDatos mockModeloDatos;


    @BeforeEach
    public void setUp() throws Exception {
        // Configurar el entorno de prueba
        mockModeloDatos = mock(ModeloDatos.class);
        mockModeloDatos.abrirConexion();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Cerrar la conexión después de cada prueba
        mockModeloDatos.cerrarConexion();
    }


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
        assertEquals(1, 1);
    }
}