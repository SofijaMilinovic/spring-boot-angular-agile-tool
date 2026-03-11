package com.agileapp.service;


import com.agileapp.dto.LoginRequestDTO;
import com.agileapp.dto.LoginResponseDTO;
import com.agileapp.entity.AppUser;
import com.agileapp.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository appUserRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO  login(LoginRequestDTO request){

        AppUser user = appUserRepository.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPasswordHash())){
            throw new RuntimeException("Invalid password");
        }


        String token = jwtService.generateToken(user.getUsername());

        return new LoginResponseDTO(token,user.getUserId(),user.getUsername());
    }
}
