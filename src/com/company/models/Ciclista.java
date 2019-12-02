package com.company.models;

import javax.persistence.*;

@Entity
@Table
public class Ciclista {

    @Id
    @Column
    private String dorsal;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Palmares palmares;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    public Ciclista(String dorsal, String nombre, String apellidos, Palmares palmares, Equipo equipo) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.palmares = palmares;
        this.equipo = equipo;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Palmares getPalmares() {
        return palmares;
    }

    public void setPalmares(Palmares palmares) {
        this.palmares = palmares;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
