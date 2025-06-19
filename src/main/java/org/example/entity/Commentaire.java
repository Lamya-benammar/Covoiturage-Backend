package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setTrajet(Trajet trajet) {
    }

    public void setUser(User user) {
    }

    public void setDateCreation(LocalDateTime now) {
    }
}
