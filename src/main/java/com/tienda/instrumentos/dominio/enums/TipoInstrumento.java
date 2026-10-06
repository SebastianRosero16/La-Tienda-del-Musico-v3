package com.tienda.instrumentos.dominio.enums;

/** Tipos de instrumento disponibles en la tienda. */
public enum TipoInstrumento {
    GUITARRA_ACUSTICA("Guitarra Acustica"),
    GUITARRA_ELECTRICA("Guitarra Electrica"),
    GUITARRA_CLASICA("Guitarra Clasica"),
    BAJO("Bajo Electrico"),
    UKULELE("Ukulele");

    private final String nombre;

    TipoInstrumento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
