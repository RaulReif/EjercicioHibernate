package com.company.models;

import javax.persistence.*;

@Entity
@Table
public class Palmares {

    @Id
    @Column
    private String id;

    @Column
    private int numero_vueltas;

    @Column
    private int numero_maillots;

    @OneToOne(mappedBy = "palmares")
    @PrimaryKeyJoinColumn
    private Ciclista ciclista;

    public Palmares(String id, int numero_vueltas, int numero_maillots) {
        this.id = id;
        this.numero_vueltas = numero_vueltas;
        this.numero_maillots = numero_maillots;
    }

    public Palmares() {}

    public String getId(){
        return  this.id;
    }

    public int getNumero_vueltas() {
        return numero_vueltas;
    }

    public void setNumero_vueltas(int numero_vueltas) {
        this.numero_vueltas = numero_vueltas;
    }

    public int getNumero_maillots() {
        return numero_maillots;
    }

    public void setNumero_maillots(int numero_maillots) {
        this.numero_maillots = numero_maillots;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ciclista getCiclista() {
        return ciclista;
    }

    public void setCiclista(Ciclista ciclista) {
        this.ciclista = ciclista;
    }
}
