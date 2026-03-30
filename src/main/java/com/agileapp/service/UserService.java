package com.agileapp.service;

import com.agileapp.dto.CreateUserRequestDTO;
import com.agileapp.dto.ProjectResponseDTO;
import com.agileapp.dto.UpdateUserRequestDTO;
import com.agileapp.dto.UserResponseDTO;
import com.agileapp.entity.AppUser;
import com.agileapp.entity.ProjectMember;
import com.agileapp.exception.BadRequestException;
import com.agileapp.exception.ResourceNotFoundException;
import com.agileapp.mapper.ProjectMapper;
import com.agileapp.mapper.UserMapper;
import com.agileapp.repository.AppUserRepository;
import com.agileapp.repository.ProjectMemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final ProjectMemberRepository projectMemberRepository;

    public UserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, ProjectMemberRepository projectMemberRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.projectMemberRepository = projectMemberRepository;
    }

    public UserResponseDTO createUser(CreateUserRequestDTO request){
       if(appUserRepository.findByUsername(request.getUsername()).isPresent()){
           throw new BadRequestException("User already exists");
       }

        AppUser newUser = new AppUser();
       newUser.setUsername(request.getUsername());
       newUser.setPasswordHash(passwordEncoder.encode(request.getPassword()));
       newUser.setFullName(request.getFullName());

       AppUser savedUser = appUserRepository.save(newUser);

       return UserMapper.mapToDto(savedUser);

   }

   public List<UserResponseDTO> getAllUsers(){

        List<AppUser> users = appUserRepository.findAll();

        return users.stream().map(UserMapper::mapToDto).toList();

   }

   public UserResponseDTO getUserById(Integer userId){
        AppUser user = appUserRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        return UserMapper.mapToDto(user);

   }

   public UserResponseDTO updateUser(Integer userId, UpdateUserRequestDTO request){

        AppUser user = appUserRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(request.getUsername()!=null){
            AppUser existingUser = appUserRepository.findByUsername(request.getUsername()).orElse(null);

            if(existingUser!=null && !existingUser.getUserId().equals(userId)){
                throw  new BadRequestException("Username already exists");
            }

            user.setUsername(request.getUsername());
        }

        if(request.getPassword()!=null){
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if(request.getFullName()!=null){
            user.setFullName(request.getFullName());
        }

        AppUser updatedUser = appUserRepository.save(user);

        return UserMapper.mapToDto(updatedUser);

   }

   public void deleteUser(Integer userId){
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        appUserRepository.delete(user);
   }

   public List<ProjectResponseDTO> getProjectsOfUser(Integer userId){

        appUserRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        List<ProjectMember> memberships = projectMemberRepository.findByUser_UserId(userId);

        if(memberships.isEmpty()){
            throw new ResourceNotFoundException("User is not assigned to any project");
        }

        return memberships.stream().map(pm->ProjectMapper.mapToDto(pm.getProject())).toList();

   }



}
