package org.example.repository;

import org.example.entity.Commentaire;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}
