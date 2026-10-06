package com.tienda.instrumentos.dominio.modelo;

import com.tienda.instrumentos.dominio.enums.TipoInstrumento;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/** Guitarra clásica; ofrece descuento en alquileres semanales. */
@Entity
@DiscriminatorValue("GUITARRA_CLASICA")
public class GuitarraClasica extends Instrumento {

    @Column
    private String tipoNylon; // alto, bajo, extra alto tension

    @Column
    private boolean tieneEstuche;

    public GuitarraClasica() {}

    public GuitarraClasica(String marca, String modelo, String numeroDeSerie,
                            double precioPorDia, String tipoNylon, boolean tieneEstuche) {
        super(marca, modelo, numeroDeSerie, TipoInstrumento.GUITARRA_CLASICA, precioPorDia);
        this.tipoNylon = tipoNylon;
        this.tieneEstuche = tieneEstuche;
    }

    @Override
    public double calcularPrecioAlquiler(int dias) {
        double precioBase = getPrecioPorDia() * dias;
        if (dias >= 7) {
            return precioBase * 0.90;
        }
        return precioBase;
    }

    @Override
    public String obtenerDescripcion() {
        String estuche = tieneEstuche ? "Con estuche incluido" : "Sin estuche";
        return String.format("Guitarra Clasica %s %s | Cuerdas: %s | %s | $%.0f/dia",
                getMarca(), getModelo(), tipoNylon, estuche, getPrecioPorDia());
    }

    public String getTipoNylon() { return tipoNylon; }
    public void setTipoNylon(String tipoNylon) { this.tipoNylon = tipoNylon; }

    public boolean isTieneEstuche() { return tieneEstuche; }
    public void setTieneEstuche(boolean tieneEstuche) { this.tieneEstuche = tieneEstuche; }
}
