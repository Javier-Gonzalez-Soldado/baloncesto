import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

        // Crear un modelo de datos y simular la conexión y el statement
        ModeloDatos instance = new ModeloDatos();
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        // Configurar el modeloDatos para usar el mockStatement
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);

        // Simular la existencia de un jugador
        when(mockResultSet.next()).thenReturn(true);

        // Configurar el modeloDatos para usar la conexión simulada
        instance.abrirConexion();

        // Llamar al método actualizarJugador() con un nombre de jugador existente
        instance.actualizarJugador("Llull");

        // Verificar que se llamó al método executeUpdate con la consulta esperada
        Mockito.verify(mockStatement).executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre LIKE '%Llull%'");

    }
}