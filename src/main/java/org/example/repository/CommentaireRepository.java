package org.example.repository;

import org.example.entity.Commentaire;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByTrajetId(Long trajetId);
}
