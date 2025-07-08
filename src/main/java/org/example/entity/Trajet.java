package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "trajet")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String depart;
    private String destination;

    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private User conducteur;

    private LocalDate date;
    private LocalTime heure;

    private Integer nbPlaces;
    private Integer reservedPlaces ;

    private String typeAnnonce;

    private Integer vu;

    private Double prix;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    @JsonBackReference
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commentaire> comments;
}
