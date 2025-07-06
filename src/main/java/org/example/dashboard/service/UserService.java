package org.example.dashboard.service;

import org.example.dashboard.dashboardDto.UserCreationDto;
import org.example.dashboard.dashboardDto.UserDto;
import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser (UserCreationDto userCreationDto);
    List<UserDto> fetchUsers ();
    UserDto deleteUser (Long id);

    User updateUser(Long id, User updatedUser);

    User getUserById(Long id);
}
