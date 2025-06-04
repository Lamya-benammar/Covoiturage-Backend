package org.example.controller;

import org.example.entity.Trajet;
import org.example.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public Trajet getTrajetById(@PathVariable Long id) {
        return trajetRepository.findById(id).orElseThrow();
    }



}
