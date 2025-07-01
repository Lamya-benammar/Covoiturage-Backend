package org.example.dto;

import org.example.entity.User;

public class vehiculeDTO {
    private Long id;
    private String marque;
    private String immatriculation;
    private User conducteur;

    public Long getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setConducteur(User conducteur) {
        this.conducteur = conducteur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public User getConducteur() {
        return conducteur;
    }

    public vehiculeDTO(Long id, String marque, String immatriculation, User conducteur) {
        this.id = id;
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.conducteur = conducteur;
    }


}

