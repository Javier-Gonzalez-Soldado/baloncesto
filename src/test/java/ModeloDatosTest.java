import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import static org.mockito.Mockito.*;


public class ModeloDatosTest {

    private ModeloDatos modeloDatos;


    @BeforeEach
    public void setUp() throws Exception {
        modeloDatos = mock(ModeloDatos.class);
        modeloDatos.abrirConexion();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Cerrar la conexión después de cada prueba
        modeloDatos.cerrarConexion();
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
    public void testActualizarJugador() throws Exception {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Llull";
        modeloDatos.actualizarJugador(nombre);
        verify(modeloDatos).actualizarJugador(nombre);
    }
}