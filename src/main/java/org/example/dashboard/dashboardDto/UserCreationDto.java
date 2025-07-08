package org.example.dashboard.dashboardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreationDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private User.Role role;
}
