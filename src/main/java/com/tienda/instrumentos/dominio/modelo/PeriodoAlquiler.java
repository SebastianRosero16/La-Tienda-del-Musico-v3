package com.tienda.instrumentos.dominio.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Agrupa y valida las fechas del alquiler; JPA las guarda en Reserva. */
@Embeddable
public class PeriodoAlquiler {

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaDevolucion;

    // Requerido por JPA.
    protected PeriodoAlquiler() {}

    public PeriodoAlquiler(LocalDate fechaInicio, LocalDate fechaDevolucion) {
        if (fechaInicio == null || fechaDevolucion == null) {
            throw new IllegalArgumentException("Las fechas del periodo no pueden ser nulas");
        }
        if (!fechaDevolucion.isAfter(fechaInicio)) {
            throw new IllegalArgumentException(
                "La fecha de devolucion debe ser posterior a la fecha de inicio");
        }
        this.fechaInicio     = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
    }

    /** Calcula la duración en días. */
    public int calcularDias() {
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaDevolucion);
    }

    /** Indica si ya pasó la fecha de devolución. */
    public boolean estaVencido() {
        return LocalDate.now().isAfter(fechaDevolucion);
    }

    public LocalDate getFechaInicio()     { return fechaInicio; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }

    @Override
    public String toString() {
        return fechaInicio + " -> " + fechaDevolucion + " (" + calcularDias() + " dias)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodoAlquiler that)) return false;
        return fechaInicio.equals(that.fechaInicio) &&
               fechaDevolucion.equals(that.fechaDevolucion);
    }

    @Override
    public int hashCode() {
        return 31 * fechaInicio.hashCode() + fechaDevolucion.hashCode();
    }
}
