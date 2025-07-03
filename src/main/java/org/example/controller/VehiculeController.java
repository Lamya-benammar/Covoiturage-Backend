package org.example.controller;

import org.example.entity.Vehicule;
import org.example.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

        @Autowired
        private VehiculeService vehiculeService;

        @GetMapping("/user/{userId}")
        public List<Vehicule> getVehiculesByUser(@PathVariable Long userId) {
            return vehiculeService.getVehiculesByUserId(userId);
        }

    @PostMapping("/user/{userId}")
    public Vehicule addVehicule(@PathVariable Long userId, @RequestBody Vehicule vehicule) {
        return vehiculeService.addVehiculeToUser(userId, vehicule);
    }


    @PostMapping("/{id}")
    public Vehicule updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.updateVehicule(id, vehicule);
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
            vehiculeService.deleteVehicule(id);
            return ResponseEntity.noContent().build();
        }
    }


