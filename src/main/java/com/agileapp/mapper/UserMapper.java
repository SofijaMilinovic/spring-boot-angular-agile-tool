package com.agileapp.mapper;

import com.agileapp.dto.UserResponseDTO;
import com.agileapp.entity.AppUser;

public class UserMapper {

    public static UserResponseDTO mapToDto(AppUser user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}
