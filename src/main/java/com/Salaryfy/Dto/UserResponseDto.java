package com.Salaryfy.Dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {

    String status;

    List <UserDTO> users;

    String exception;

    public UserResponseDto(String status){
        this.status=status;
    }


}
