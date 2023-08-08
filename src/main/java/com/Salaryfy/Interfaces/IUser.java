package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.UserDto;
import com.Salaryfy.utils.BaseResponseDTO;


public interface IUser {

    public BaseResponseDTO registerAccount (UserDto userDTO);
}
