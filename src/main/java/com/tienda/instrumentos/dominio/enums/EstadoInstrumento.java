package com.tienda.instrumentos.dominio.enums;

/** Estados físicos posibles de un instrumento. */
public enum EstadoInstrumento {
    NUEVO("Nuevo - Sin uso previo"),
    USADO("Usado - Con desgaste normal"),
    RAYADO("Rayado - Tiene marcas visibles"),
    DANADO("Dañado - Requiere revision");

    private final String descripcion;

    EstadoInstrumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
