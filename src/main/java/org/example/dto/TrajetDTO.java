package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dashboard.dashboardDto.UserDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrajetDTO {
    private Long id;
    private String depart;
    private String destination;
    private LocalDate date;
    private LocalTime heure;
    private int nbPlaces;
    private int reservedPlaces;
    private int vu;
    private double prix;
    private String typeAnnonce;
    private VehiculeDTO vehicule;
    private UserDto conducteur;

    private List<CommentaireDTO> comments;

}

