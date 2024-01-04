import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;


public class ModeloDatosTest {

    private ModeloDatos modeloDatos;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;


    @BeforeEach
    public void setUp() throws Exception {
        // Configurar el entorno de prueba
        modeloDatos = new ModeloDatos();

        // Mock de la conexión y el statement
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeUpdate(anyString())).thenReturn(1); // Simular que la actualización fue exitosa

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
    public void testActualizarJugador() {
        try{
            // Configurar el estado inicial de la base de datos de prueba
            when(mockResultSet.next()).thenReturn(true, false); // Simular que hay un jugador en la base de datos
            when(mockResultSet.getString("Nombre")).thenReturn("NombreJugador"); // Simular el nombre del jugador

            // Llamar al método que queremos probar
            modeloDatos.actualizarJugador("NombreJugador");

            // Verificar que el método de actualización se llamó con los parámetros correctos
            verify(mockStatement).executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre LIKE '%NombreJugador%'");

        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}