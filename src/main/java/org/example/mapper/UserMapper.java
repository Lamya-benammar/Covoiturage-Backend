package org.example.mapper;

import org.example.dashboard.dashboardDto.UserCreationDto;
import org.example.dashboard.dashboardDto.UserDto;
import org.example.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
                //user.getCommentaires()
        );
    }


    public static User mapToUserFromUserCreationDto(UserCreationDto creationDto) {
        return new User(
                creationDto.getId(),
                creationDto.getFirstName(),
                creationDto.getLastName(),
                creationDto.getEmail(),
                creationDto.getPhone(),
                creationDto.getPassword(),
                creationDto.getRole()
        );

    }


    public static User mapToUserFromDTO(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getFirstname());
        user.setLastName(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());
        return user;
    }
}
