package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dashboard.dashboardDto.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentaireDTO {
    private Long id;
    private String contenu;
    private UserDto auteur;
    private TrajetDTO trajet;

}
