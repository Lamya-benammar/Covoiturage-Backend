package org.example.controller;

import org.example.dashboard.dashboardDto.UserDto;
import org.example.dto.TrajetDTO;
import org.example.entity.Commentaire;
import org.example.entity.Trajet;
import org.example.entity.User;
import org.example.entity.Vehicule;
import org.example.mapper.TrajetMapper;

import org.example.repository.*;

import org.example.repository.VehiculeRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trajets")
// @CrossOrigin(origins = "http://localhost:4200")
public class TrajetController {

    private final UserRepository userRepository;
    private final TrajetRepository trajetRepository;
    private final VehiculeRepository vehiculeRepository;

    int totalNumberOfReservedPlaces;

    public TrajetController(TrajetRepository trajetRepository, UserRepository userRepository, VehiculeRepository vehiculeRepository) {

        this.trajetRepository = trajetRepository;
        this.userRepository = userRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @GetMapping
    public ResponseEntity<List<TrajetDTO>> getAllTrajets() {
        List<TrajetDTO> trajetDTOs = trajetRepository.findAll().stream()
                .map(TrajetMapper::mapToTrajetDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(trajetDTOs);
    }


    @GetMapping("/search")
    public List<Trajet> searchTrajets(
            @RequestParam(required = false) String depart,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Integer nbPassagers) {
        return trajetRepository.searchByCriteria(depart, destination, date, nbPassagers);
    }


    @GetMapping("allTrajets")
    public ResponseEntity<List<TrajetDTO>> fetchTrajets() {
        List<Trajet> trajets = trajetRepository.findAll();
        List<TrajetDTO> trajetDTO = trajets.stream().map(trajet ->
                TrajetMapper.mapToTrajetDTO(trajet)).collect(Collectors.toList());
        return ResponseEntity.ok(trajetDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trajet> consulterTrajet(@PathVariable Long id) {
        Optional<Trajet> trajetOptional = trajetRepository.findTrajetById(id);
        if (trajetOptional.isPresent()) {
            Trajet trajet = trajetOptional.get();
            System.out.println("Vehicule: " + trajet.getVehicule());
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


    //to improve/////// instead of using ClientName we should use UserId, for more accuracy...
    // I have added reservedplaces so I can use it in the dashboard (reservation total Value)
    @PostMapping("/{id}/reserver")
    public ResponseEntity<String> reserverPlace(@PathVariable Long id, @RequestParam String clientName) {
        Optional<Trajet> trajetOpt = trajetRepository.findById(id);
        if (trajetOpt.isPresent()) {
            Trajet trajet = trajetOpt.get();
            if (trajet.getNbPlaces() > 0) {
                trajet.setNbPlaces(trajet.getNbPlaces() - 1);
                if (trajet.getReservedPlaces() == null) {
                    trajet.setReservedPlaces(0);
                }
                trajet.setReservedPlaces(trajet.getReservedPlaces() + 1);
                String notification = "Le client " + clientName + " a réservé une place avec " + trajet.getConducteur().getName() + " pour le trajet de " + trajet.getDepart() + " à " + trajet.getDestination() + " le " + trajet.getDate() + " à " + trajet.getHeure() + ".";

                trajetRepository.save(trajet);
                return ResponseEntity.ok("Réservation confirmée.");
            } else {
                return ResponseEntity.badRequest().body("Aucune place disponible.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/vehicules/{vehiculeId}")
    public ResponseEntity<TrajetDTO> createTrajet(
            @PathVariable Long userId,
            @PathVariable Long vehiculeId,
            @RequestBody Trajet trajet) {

        // Vérifier si l'utilisateur existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier si le véhicule existe
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        List<Commentaire> comments = new ArrayList<>();
        trajet.setVu(0);
        trajet.setConducteur(user);
        trajet.setVehicule(vehicule);
        trajet.setComments(comments);

        TrajetDTO savedTrajet = TrajetMapper.mapToTrajetDTO(trajetRepository.save(trajet));
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
    public ResponseEntity<TrajetDTO> updateTrajet(@PathVariable Long id, @RequestBody Trajet updatedTrajet) {
        Optional<Trajet> trajetOptional = trajetRepository.findById(id);
        if (trajetOptional.isPresent()) {
            TrajetDTO existingTrajet = TrajetMapper.mapToTrajetDTO(trajetOptional.get());
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

    @GetMapping("/getAllReservationNumber")
    public Integer getAllReservationNumber() {
        List<Trajet> trajetOpt = trajetRepository.findAll();

        trajetOpt.stream().forEach(trajet -> {
            totalNumberOfReservedPlaces += trajet.getReservedPlaces();
        });
        return totalNumberOfReservedPlaces;
    }

}
