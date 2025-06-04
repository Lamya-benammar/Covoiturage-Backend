package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conducteur;
    private String depart;
    private String destination;

    private LocalDate date;
    private LocalTime heure;

    private int nbPlaces;

    public Trajet() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConducteur(String conducteur) {
        this.conducteur = conducteur;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Long getId() {
        return id;
    }

    public String getConducteur() {
        return conducteur;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public Trajet(Long id, String conducteur, String depart, String destination, LocalDate date, LocalTime heure, int nbPlaces) {
        this.id = id;
        this.conducteur = conducteur;
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.heure = heure;
        this.nbPlaces = nbPlaces;
    }


}
