package org.example.mapper;

import org.example.dto.CommentaireDTO;
import org.example.entity.Commentaire;

public class CommentaireMapper {

    public static CommentaireDTO mapToCommentaireDTO(Commentaire commentaire) {
        return new CommentaireDTO(
                commentaire.getId(),
                commentaire.getContenu(),
                UserMapper.mapToUserDto(commentaire.getUser()),
                TrajetMapper.mapToTrajetDTO(commentaire.getTrajet())
        );
    }

    public static Commentaire mapToCommentaire(CommentaireDTO commentaireDTO) {
        return new Commentaire(
                        commentaireDTO.getId(),
                        commentaireDTO.getContenu(),
                TrajetMapper.mapToTrajet(commentaireDTO.getTrajet()),
                UserMapper.mapToUserFromDTO(commentaireDTO.getAuteur())
                );
    }
}
