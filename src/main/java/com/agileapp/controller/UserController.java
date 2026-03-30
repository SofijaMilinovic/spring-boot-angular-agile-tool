package com.agileapp.controller;

import com.agileapp.dto.CreateUserRequestDTO;
import com.agileapp.dto.ProjectResponseDTO;
import com.agileapp.dto.UpdateUserRequestDTO;
import com.agileapp.dto.UserResponseDTO;
import com.agileapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserRequestDTO request){
        return userService.createUser(request);
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserResponseDTO updateUser(@PathVariable Integer userId, @RequestBody UpdateUserRequestDTO request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId){
         userService.deleteUser(userId);
    }


    @GetMapping("/{userId}/projects")
    public List<ProjectResponseDTO> getProjectsOfUser(@PathVariable Integer userId){
        return userService.getProjectsOfUser(userId);
    }

}
