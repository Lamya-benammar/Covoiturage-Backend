package org.example.dashboard.controller;

import lombok.AllArgsConstructor;
import org.example.dashboard.dashboardDto.UserCreationDto;
import org.example.dashboard.dashboardDto.UserDto;
import org.example.dashboard.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users") // to define the base url for all the rest APIs
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreationDto userCreationDto) {
    UserDto savedUser =userService.createUser(userCreationDto);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> fetchUsers(){
        List<UserDto> users = userService.fetchUsers();
        return ResponseEntity.ok(users);

    }

    @DeleteMapping("dashboard/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id){
        UserDto deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);

    }


}
