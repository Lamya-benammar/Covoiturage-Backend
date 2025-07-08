package org.example.mapper;

import org.example.dto.VehiculeDTO;
import org.example.entity.Trajet;
import org.example.entity.Vehicule;

public class VehiculeMapper {
    public static VehiculeDTO mapToVehiculeDTO(Vehicule vehicule) {
        return new VehiculeDTO(
                vehicule.getId(),
                vehicule.getMarque(),
                vehicule.getImmatricule(),
                UserMapper.mapToUserDto(vehicule.getConducteur()),
                vehicule.getTrajets().stream()
                        .map(TrajetMapper::mapToTrajetDTO)
                        .toList()
        );
    }

    public static VehiculeDTO mapToVehiculeDTOWithoutTrajets(Vehicule vehicule) {
        return new VehiculeDTO(
                vehicule.getId(),
                vehicule.getMarque(),
                vehicule.getImmatricule(),
                UserMapper.mapToUserDto(vehicule.getConducteur()),
                null
        );
    }

    public static Vehicule mapToVehicule(VehiculeDTO vehiculeDTO) {
        return new Vehicule(
                vehiculeDTO.getId(),
                vehiculeDTO.getMarque(),
                vehiculeDTO.getImmatricule(),
                UserMapper.mapToUserFromDTO(vehiculeDTO.getConducteur()),
                vehiculeDTO.getTrajets().stream()
                        .map(TrajetMapper::mapToTrajet)
                        .toList()
        );
    }


}
