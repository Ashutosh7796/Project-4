package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.utils.BaseResponseDTO;


public interface IUser {

    public BaseResponseDTO registerAccount (UserDTO userDTO);

    public void updateDetails (UserDTO userDTO);
}
