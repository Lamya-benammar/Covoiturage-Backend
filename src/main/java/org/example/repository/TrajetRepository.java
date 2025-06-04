package org.example.repository;

import org.example.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    List<Trajet> findAll();

    Trajet save(Trajet trajet);

    Optional<Trajet> findById(Long id);
    @Query("""
    SELECT t FROM Trajet t
    WHERE (:depart IS NULL OR LOWER(t.depart) LIKE LOWER(CONCAT('%', :depart, '%')))
      AND (:destination IS NULL OR LOWER(t.destination) LIKE LOWER(CONCAT('%', :destination, '%')))
      AND (:date IS NULL OR t.date = :date)
      AND (:places IS NULL OR t.nbPlaces >= :places)
""")
    List<Trajet> searchByCriteria(
            @Param("depart") String depart,
            @Param("destination") String destination,
            @Param("date") LocalDate date,
            @Param("places") Integer places
    );
    }


