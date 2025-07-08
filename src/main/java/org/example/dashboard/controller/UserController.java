package org.example.dashboard.controller;

import lombok.AllArgsConstructor;
import org.example.dashboard.dashboardDto.UserCreationDto;
import org.example.dashboard.dashboardDto.UserDto;
import org.example.dashboard.service.UserService;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dashboard/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreationDto userCreationDto) {
        UserDto savedUser = userService.createUser(userCreationDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("allUsers")
    public ResponseEntity<List<UserDto>> fetchUsers() {
        List<UserDto> users = userService.fetchUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        UserDto deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        UserDto userDto = UserMapper.mapToUserDto(user);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto userDto = UserMapper.mapToUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("users/count")
    public ResponseEntity<Long> getUserCount() {
        Long count = userService.countUsers();
        return ResponseEntity.ok(count);
    }
}