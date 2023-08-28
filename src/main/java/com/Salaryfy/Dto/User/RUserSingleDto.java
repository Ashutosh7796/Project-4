package com.Salaryfy.Dto.User;

import com.Salaryfy.Dto.UserDTO;
import lombok.Data;

@Data
public class RUserSingleDto {

    private String status;
    private UserDTO Response;

    public RUserSingleDto(String status) {
        this.status = status;
    }


}
