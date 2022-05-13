package Modelo;

public class Jugador {
    private int jugadorId;
    private String nombre;
    private int totalVotos;

    public Jugador() {
    }

    public Jugador(int jugadorId, String nombre, int totalVotos) {
        this.jugadorId = jugadorId;
        this.nombre = nombre;
        this.totalVotos = totalVotos;
    }

    public int getId() {
        return jugadorId;
    }

    public void setId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

}
