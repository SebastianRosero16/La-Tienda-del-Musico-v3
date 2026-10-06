package com.tienda.instrumentos.dominio.modelo;

import com.tienda.instrumentos.dominio.enums.TipoInstrumento;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/** Guitarra acústica con sus características propias. */
@Entity
@DiscriminatorValue("GUITARRA_ACUSTICA")
public class GuitarraAcustica extends Instrumento {

    @Column
    private int numeroCuerdas;

    @Column
    private String materialCuerpo; // madera, carbono, etc.

    // Requerido por JPA.
    public GuitarraAcustica() {}

    public GuitarraAcustica(String marca, String modelo, String numeroDeSerie,
                             double precioPorDia, int numeroCuerdas, String materialCuerpo) {
        super(marca, modelo, numeroDeSerie, TipoInstrumento.GUITARRA_ACUSTICA, precioPorDia);
        this.numeroCuerdas = numeroCuerdas;
        this.materialCuerpo = materialCuerpo;
    }

    @Override
    public double calcularPrecioAlquiler(int dias) {
        return getPrecioPorDia() * dias;
    }

    @Override
    public String obtenerDescripcion() {
        return String.format("Guitarra Acustica %s %s | %d cuerdas | Cuerpo: %s | $%.0f/dia",
                getMarca(), getModelo(), numeroCuerdas, materialCuerpo, getPrecioPorDia());
    }

    public int getNumeroCuerdas() { return numeroCuerdas; }
    public void setNumeroCuerdas(int numeroCuerdas) { this.numeroCuerdas = numeroCuerdas; }

    public String getMaterialCuerpo() { return materialCuerpo; }
    public void setMaterialCuerpo(String materialCuerpo) { this.materialCuerpo = materialCuerpo; }
}
