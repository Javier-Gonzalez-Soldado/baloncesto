import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.Statement;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;


public class ModeloDatosTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    private ModeloDatos modeloDatos;


    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        modeloDatos = new ModeloDatos();
        modeloDatos.abrirConexion();
        modeloDatos.setStatement(mockStatement); // Usamos el nuevo método para configurar el Statement

        // Configurar el mock para que devuelva el mockStatement
        when(mockConnection.createStatement()).thenReturn(mockStatement);
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
    public void testActualizarJugador() throws Exception {
        // Configurar el estado inicial de la base de datos de prueba
        when(mockStatement.executeUpdate(anyString())).thenReturn(1); // Supongamos que una fila fue actualizada

        // Llamar al método que estamos probando
        modeloDatos.actualizarJugador("nombre");

        // Verificar que se llamó al método executeUpdate con la consulta esperada
        verify(mockStatement).executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre LIKE '%nombre%'");

        // Verificar que se cerró el Statement
        verify(mockStatement).close();

    }
}