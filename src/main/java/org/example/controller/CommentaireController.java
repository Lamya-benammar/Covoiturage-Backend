package org.example.controller;


import org.example.entity.Commentaire;
import org.example.entity.Trajet;
import org.example.entity.User;
import org.example.repository.CommentaireRepository;
import org.example.repository.TrajetRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private UserRepository userRepository;

/*
    @GetMapping("/trajet/{trajetId}/commentaire")
    public ResponseEntity<List<Commentaire>> getCommentairesByTrajetId(@PathVariable Long trajetId) {
        List<Commentaire> commentaires = commentaireRepository.findByTrajetId(trajetId);
        return ResponseEntity.ok(commentaires);
    }

    @PostMapping("/trajet/{trajetId}/user/{userId}")
    public ResponseEntity<Commentaire> addCommentaire(
            @PathVariable Long trajetId,
            @PathVariable Long userId,
            @RequestBody Commentaire commentaireRequest) {

        Optional<Trajet> trajetOpt = trajetRepository.findById(trajetId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (trajetOpt.isEmpty() || userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        commentaireRequest.setTrajet(trajetOpt.get());
        commentaireRequest.setUser(userOpt.get());
        commentaireRequest.setDateCreation(LocalDateTime.now());

        Commentaire saved = commentaireRepository.save(commentaireRequest);
        return ResponseEntity.ok(saved);
    }*/
}


