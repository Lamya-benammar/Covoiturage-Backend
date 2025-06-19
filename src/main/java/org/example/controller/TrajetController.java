package org.example.controller;

import org.example.entity.Trajet;
import org.example.repository.TrajetRepository;
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

    private final TrajetRepository trajetRepository;

    public TrajetController(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

    @PostMapping
    public Trajet addTrajet(@RequestBody Trajet trajet) {
        return trajetRepository.save(trajet);
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
    @PostMapping("/trajets/{id}/reserver")
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

}
