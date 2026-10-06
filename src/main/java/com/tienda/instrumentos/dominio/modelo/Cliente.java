package com.tienda.instrumentos.dominio.modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/** Cliente y sus reservas activas. */
@Entity
@Table(name = "clientes")
public class Cliente {

    // UUID técnico; la identificación pertenece al negocio.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String numeroIdentificacion; // cedula o NIT

    @Column(nullable = false)
    private String celular;

    @Column
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    // Requerido por JPA.
    public Cliente() {}

    public Cliente(String nombre, String numeroIdentificacion, String celular) {
        setNombre(nombre);
        setNumeroIdentificacion(numeroIdentificacion);
        setCelular(celular);
    }

    /** El cliente puede tener hasta tres reservas activas. */
    public boolean puedeHacerNuevaReserva() {
        long reservasActivas = reservas.stream()
                .filter(r -> r.estaActiva())
                .count();
        return reservasActivas < 3;
    }

    // Evita que otros modifiquen la lista directamente.
    public List<Reserva> getReservas() {
        return Collections.unmodifiableList(reservas);
    }

    public void agregarReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public UUID getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        if (numeroIdentificacion == null || numeroIdentificacion.isBlank()) {
            throw new IllegalArgumentException("El numero de identificacion es obligatorio");
        }
        this.numeroIdentificacion = numeroIdentificacion.trim();
    }

    public String getCelular() { return celular; }

    public void setCelular(String celular) {
        if (celular == null || celular.isBlank()) {
            throw new IllegalArgumentException("El celular del cliente es obligatorio");
        }
        this.celular = celular.trim();
    }

    public String getCorreoElectronico() { return correoElectronico; }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s | ID: %s | Cel: %s", nombre, numeroIdentificacion, celular);
    }
}
