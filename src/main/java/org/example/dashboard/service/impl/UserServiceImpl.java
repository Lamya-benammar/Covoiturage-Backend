package org.example.dashboard.service.impl;

import org.example.dashboard.dashboardDto.UserCreationDto;
import org.example.dashboard.dashboardDto.UserDto;
import org.example.entity.User;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.dashboard.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserCreationDto userCreationDto) {
        userCreationDto.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        User user = UserMapper.mapToUser(userCreationDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public List<UserDto> fetchUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User  with ID " + id + " does not exist in the DB"));
        userRepository.delete(user);
        UserDto deletedUser = UserMapper.mapToUserDto(user);
        return deletedUser;
    }

}
