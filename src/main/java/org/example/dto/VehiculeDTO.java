package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dashboard.dashboardDto.UserDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehiculeDTO {
    private Long id;
    private String marque;
    private String immatricule;
    private UserDto conducteur;
    private List<TrajetDTO> trajets;

}