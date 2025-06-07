package org.example.dashboard.service;

import org.example.dashboard.dashboardDto.UserCreationDto;
import org.example.dashboard.dashboardDto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser (UserCreationDto userCreationDto);
    List<UserDto> fetchUsers ();
    UserDto deleteUser (Long id);
}
