package org.example.dto;

import org.example.dashboard.dashboardDto.UserDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class trajetDTO {
    private Long id;
    private UserDto conducteur;
    private String depart;
    private String destination;
    private LocalDate date;
    private LocalTime heure;
    private int nbPlaces;
    private int vu;
    private double prix;

    public void setTypeAnnonce(String typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    private vehiculeDTO vehicule;

    public String getTypeAnnonce() {
        return typeAnnonce;
    }

    private String typeAnnonce;

    public Long getId() {
        return id;
    }


    public String getDepart() {
        return depart;
    }

    public void setVehicule(vehiculeDTO vehicule) {
        this.vehicule = vehicule;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public UserDto getConducteur() {
        return conducteur;
    }

    public void setConducteur(UserDto conducteur) {
        this.conducteur = conducteur;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getVu() {
        return vu;
    }

    public trajetDTO(Long id, UserDto conducteur, String depart, String destination, LocalDate date, LocalTime heure, int nbPlaces, int vu, double prix, vehiculeDTO vehicule, String typeAnnonce) {
        this.id = id;
        this.conducteur = conducteur;
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.heure = heure;
        this.nbPlaces = nbPlaces;
        this.vu = vu;
        this.prix = prix;
        this.vehicule = vehicule;
        this.typeAnnonce = typeAnnonce;
    }

    public double getPrix() {
        return prix;
    }

    public vehiculeDTO getVehicule() {
        return vehicule;
    }




}

