package org.example.dashboard.dashboardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Commentaire;
import org.example.entity.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    public UserDto(Long id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }



    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private User.Role role;


    //private List<Commentaire>commentaires;


}
