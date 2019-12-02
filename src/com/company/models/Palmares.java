package com.company.models;

import javax.persistence.*;

@Entity
@Table
public class Palmares {

    @Id
    @Column
    private int id;

    @Column
    private int numero_vueltas;

    @Column
    private int numero_maillots;

    @OneToOne(mappedBy = "palmares")
    @PrimaryKeyJoinColumn
    private Ciclista ciclista;

    public Palmares(int id, int numero_vueltas, int numero_maillots, Ciclista ciclista) {
        this.id = id;
        this.numero_vueltas = numero_vueltas;
        this.numero_maillots = numero_maillots;
        this.ciclista = ciclista;
    }

    public int getId(){
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

    public void setId(int id) {
        this.id = id;
    }

    public Ciclista getCiclista() {
        return ciclista;
    }

    public void setCiclista(Ciclista ciclista) {
        this.ciclista = ciclista;
    }
}
