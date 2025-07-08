package org.example.controller;

import org.example.dto.VehiculeDTO;
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

    @GetMapping("/vehiculeByUser/{userId}")
    public List<VehiculeDTO> getVehiculesByUser(@PathVariable Long userId) {
        return vehiculeService.getVehiculesByUserId(userId);
    }

    @PostMapping("/addVehiculeToUser/{userId}")
    public VehiculeDTO addVehicule(@PathVariable Long userId, @RequestBody Vehicule vehicule) {
        return vehiculeService.addVehiculeToUser(userId, vehicule);
    }


    @PostMapping("updateVehicule/{id}")
    public VehiculeDTO updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.updateVehicule(id, vehicule);
    }

    @DeleteMapping("deleteVehicule/{id}")
    public ResponseEntity<VehiculeDTO> deleteVehicule(@PathVariable Long id) {
        VehiculeDTO vehiculeDTO = vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}


