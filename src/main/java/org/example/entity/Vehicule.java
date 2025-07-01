package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String immatriculation;

    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private User conducteur;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Trajet> trajets;


    public Vehicule() {
    }

    public Vehicule(String marque, String immatriculation, User conducteur) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.conducteur = conducteur;
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

    public void setTrajets(List<Trajet> trajets) {
        this.trajets = trajets;
    }

    public List<Trajet> getTrajets() {
        return trajets;
    }

    public User getConducteur() {
        return conducteur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public Long getId() {
        return id;
    }
}
