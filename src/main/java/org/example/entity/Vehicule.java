package org.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String immatriculation;

    private String conducteur;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Trajet> trajets;

    public Vehicule() {
    }

    public Vehicule(String marque, String immatriculation, String conducteur) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.conducteur = conducteur;
    }


}
