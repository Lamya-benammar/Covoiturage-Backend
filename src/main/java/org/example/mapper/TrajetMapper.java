package org.example.mapper;

import org.example.dto.TrajetDTO;
import org.example.dto.VehiculeDTO;
import org.example.entity.Trajet;

import java.util.List;

public class TrajetMapper {

    public static TrajetDTO mapToTrajetDTO(Trajet trajet) {
        return new TrajetDTO(
                trajet.getId(),
                trajet.getDepart(),
                trajet.getDestination(),
                trajet.getDate(),
                trajet.getHeure(),
                trajet.getNbPlaces(),
                trajet.getReservedPlaces(),
                trajet.getVu() != null
                ?trajet.getVu() : 0,
                trajet.getPrix() != null
                ?trajet.getPrix() : 0,
                trajet.getTypeAnnonce(),
                VehiculeMapper.mapToVehiculeDTOWithoutTrajets(trajet.getVehicule()),
                UserMapper.mapToUserDto(trajet.getConducteur()),
                trajet.getComments() != null
                        ? trajet.getComments().stream().map(CommentaireMapper::mapToCommentaireDTO).toList()
                        : List.of()
        );
    }


    public static Trajet mapToTrajet(TrajetDTO dto) {
        Trajet trajet = new Trajet();

        trajet.setId(dto.getId());
        trajet.setDepart(dto.getDepart());
        trajet.setDestination(dto.getDestination());
        trajet.setConducteur(UserMapper.mapToUserFromDTO(dto.getConducteur()));
        trajet.setDate(dto.getDate());
        trajet.setHeure(dto.getHeure());
        trajet.setNbPlaces(dto.getNbPlaces());
        trajet.setReservedPlaces(dto.getReservedPlaces());
        trajet.setVu(dto.getVu());
        trajet.setPrix(dto.getPrix());
        trajet.setTypeAnnonce(dto.getTypeAnnonce());
        trajet.setVehicule(VehiculeMapper.mapToVehicule(dto.getVehicule()));
        trajet.setComments(dto.getComments().stream()
                .map(CommentaireMapper::mapToCommentaire)
                .toList());

        return trajet;
    }


}
