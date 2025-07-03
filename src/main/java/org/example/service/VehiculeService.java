package org.example.service;


import org.example.entity.User;
import org.example.entity.Vehicule;
import org.example.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.repository.UserRepository;

import java.util.List;

@Service
    public class VehiculeService {

        @Autowired
        private VehiculeRepository vehiculeRepository;
    @Autowired
        private UserRepository userRepository ;


        public List<Vehicule> getVehiculesByUserId(Long userId) {
            return vehiculeRepository.findByConducteurId(userId);
        }

    public Vehicule addVehiculeToUser(Long userId, Vehicule vehicule) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        vehicule.setConducteur(user);
        return vehiculeRepository.save(vehicule);
    }


    public Vehicule updateVehicule(Long id, Vehicule updatedVehicule) {
            return vehiculeRepository.findById(updatedVehicule.getId()).map(v -> {
                v.setMarque(updatedVehicule.getMarque());
                v.setImmatricule(updatedVehicule.getImmatricule());

                return vehiculeRepository.save(v);
            }).orElseThrow(() -> new RuntimeException("Vehicule not found"));
        }

        public void deleteVehicule(Long id) {
            vehiculeRepository.deleteById(id);
        }
    }


