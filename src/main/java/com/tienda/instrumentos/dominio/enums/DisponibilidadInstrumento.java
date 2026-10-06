package com.tienda.instrumentos.dominio.enums;

/** Estados de disponibilidad de un instrumento. */
public enum DisponibilidadInstrumento {
    DISPONIBLE("Disponible para alquiler"),
    RESERVADO("Reservado por un cliente"),
    ALQUILADO("Actualmente alquilado"),
    EN_REPARACION("En reparacion, no disponible");

    private final String descripcion;

    DisponibilidadInstrumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
