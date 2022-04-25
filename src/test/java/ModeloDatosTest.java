import org.junit.jupiter.api.Test;

import Model.*;
//qw
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

    // Test Actulizar Jugador
    @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Rudy";
        ModeloDatos instance = new ModeloDatos();
        Jugador jugadorOriginal = instance.getJugador(nombre);
        instance.actualizarJugador(nombre);
        Jugador jugadorActualizado = instance.getJugador(nombre);
        assertEquals(jugadorOriginal.getTotalVotos() + 1, jugadorActualizado.getTotalVotos());
    }

}