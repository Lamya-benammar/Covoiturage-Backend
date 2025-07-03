package org.example.dto;

import org.example.entity.User;

public class vehiculeDTO {
    private Long id;
    private String marque;
    private String immatricule;
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

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }

    public void setConducteur(User conducteur) {
        this.conducteur = conducteur;
    }

    public String getImmatricule() {
        return immatricule;
    }

    public User getConducteur() {
        return conducteur;
    }

    public vehiculeDTO(Long id, String marque, String immatricule, User conducteur) {
        this.id = id;
        this.marque = marque;
        this.immatricule = immatricule;
        this.conducteur = conducteur;
    }


}

