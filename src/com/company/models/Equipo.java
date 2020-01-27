package com.company.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Equipo {

    @Id
    @Column
    private int id;

    @Column
    private String nombre;

    @Column
    private String patrocinador;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo")
    private List<Ciclista> ciclistas = new ArrayList<>();

    public Equipo(int id, String nombre, String patrocinador) {
        this.id = id;
        this.nombre = nombre;
        this.patrocinador = patrocinador;
    }

    public Equipo() {}

    public Equipo(int id){ this.id = id; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public List<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void setCiclistas(List<Ciclista> ciclistas) {
        this.ciclistas = ciclistas;
    }
}

