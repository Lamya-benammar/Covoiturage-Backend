package org.example.service;


import org.example.dto.VehiculeDTO;
import org.example.entity.User;
import org.example.entity.Vehicule;
import org.example.mapper.VehiculeMapper;
import org.example.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private UserRepository userRepository;


    public List<VehiculeDTO> getVehiculesByUserId(Long userId) {
        List<Vehicule> vehicules = vehiculeRepository.findByConducteurId(userId);
        return vehicules.stream()
                .map(VehiculeMapper::mapToVehiculeDTO)
                .collect(Collectors.toList());
    }

    public VehiculeDTO addVehiculeToUser(Long userId, Vehicule vehicule) {
        Optional<Vehicule> vehiculeExistance = vehiculeRepository.findById(vehicule.getId());
        if (vehiculeExistance.isEmpty() || vehiculeExistance == null) {
            throw new RuntimeException("Vehecule avec ID = " + vehicule.getId() + " non trove");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        vehicule.setConducteur(user);
        VehiculeDTO vehiculeDTO = VehiculeMapper.mapToVehiculeDTO(vehicule);
        vehiculeRepository.save(vehicule);
        return vehiculeDTO;
    }


    public VehiculeDTO updateVehicule(Long id, Vehicule updatedVehicule) {
        return VehiculeMapper.mapToVehiculeDTO(vehiculeRepository.findById(updatedVehicule.getId()).map(v -> {
                    v.setMarque(updatedVehicule.getMarque());
                    v.setImmatricule(updatedVehicule.getImmatricule());
                    return vehiculeRepository.save(v);
                }).orElseThrow(() -> new RuntimeException("Vehicule nott found"))
        );
    }


    public VehiculeDTO deleteVehicule(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicule n esxiste pas"));
        vehiculeRepository.delete(vehicule);
        VehiculeDTO deletedVehicule = VehiculeMapper.mapToVehiculeDTO(vehicule);
        return deletedVehicule;
    }
}


