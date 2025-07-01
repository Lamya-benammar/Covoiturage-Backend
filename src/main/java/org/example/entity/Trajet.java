package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    public List<Commentaire> getComments() {
        return comments;
    }

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    @JsonBackReference
    private Vehicule vehicule;


    public User getUser() {
        return user;
    }

    @ManyToOne
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commentaire> comments; // waiting for Amal's Commentaire push

    private int vu ;
    private double prix;

    public void setVu(int vu) {
        this.vu = vu;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setComments(List<Commentaire> comments) {
        this.comments = comments;
    }

    public double getPrix() {
        return prix;
    }

    public int getVu() {
        return vu;
    }


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
    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

}
