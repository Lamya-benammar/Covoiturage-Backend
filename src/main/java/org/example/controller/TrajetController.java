package org.example.controller;

import org.example.entity.Trajet;
import org.example.entity.User;
import org.example.repository.TrajetRepository;
import org.example.repository.UserRepository;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trajets")
@CrossOrigin(origins = "http://localhost:4200")
public class TrajetController {

    private final UserRepository userRepository;
    private final TrajetRepository trajetRepository;

    public TrajetController(TrajetRepository trajetRepository , UserRepository userRepository) {

        this.trajetRepository = trajetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

    @GetMapping("/search")
    public List<Trajet> searchTrajets(
            @RequestParam(required = false) String depart,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Integer nbPassagers) {
        return trajetRepository.searchByCriteria(depart, destination, date, nbPassagers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trajet> getTrajet(@PathVariable Long id) {
        Optional<Trajet> trajetOptional = trajetRepository.findById(id);
        if (trajetOptional.isPresent()) {
            Trajet trajet = trajetOptional.get();
            trajet.setVu(trajet.getVu() + 1);
            trajetRepository.save(trajet);
            return ResponseEntity.ok(trajet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{trajetId}/increment-vu")
    public ResponseEntity<Void> incrementerNombreDeVu(@PathVariable Long trajetId) {
        Optional<Trajet> trajetOpt = trajetRepository.findById(trajetId);
        if (!trajetOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Trajet trajet = trajetOpt.get();
        trajet.setVu(trajet.getVu() + 1);
        trajetRepository.save(trajet);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/reserver")
    public ResponseEntity<String> reserverPlace(@PathVariable Long id, @RequestParam String clientName) {
        Optional<Trajet> trajetOpt = trajetRepository.findById(id);
        if (trajetOpt.isPresent()) {
            Trajet trajet = trajetOpt.get();
            if (trajet.getNbPlaces() > 0) {
                trajet.setNbPlaces(trajet.getNbPlaces() - 1);
                String notification = "Le client " + clientName + " a réservé une place avec " + trajet.getConducteur();

                trajetRepository.save(trajet);
                return ResponseEntity.ok("Réservation confirmée.");
            } else {
                return ResponseEntity.badRequest().body("Aucune place disponible.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Trajet> createTrajet(@RequestBody Trajet trajet) {
        Trajet savedTrajet = trajetRepository.save(trajet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrajet);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        if (trajetRepository.existsById(id)) {
            trajetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @RequestBody Trajet updatedTrajet) {
        Optional<Trajet> trajetOptional = trajetRepository.findById(id);
        if (trajetOptional.isPresent()) {
            Trajet existingTrajet = trajetOptional.get();
            existingTrajet.setDepart(updatedTrajet.getDepart());
            existingTrajet.setDestination(updatedTrajet.getDestination());
            existingTrajet.setDate(updatedTrajet.getDate());
            existingTrajet.setHeure(updatedTrajet.getHeure());
            existingTrajet.setNbPlaces(updatedTrajet.getNbPlaces());
            existingTrajet.setConducteur(updatedTrajet.getConducteur());
            existingTrajet.setVu(updatedTrajet.getVu());

            trajetRepository.save(existingTrajet);
            return ResponseEntity.ok(existingTrajet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{userId}")
    public List<Trajet> getTrajetsByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return trajetRepository.findByUser(user);
    }




}
