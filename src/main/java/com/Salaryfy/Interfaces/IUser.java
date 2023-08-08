package com.Salaryfy.Interfaces;


import com.Salaryfy.Dto.GetAllUserDTO;
import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.utils.BaseResponseDTO;

import java.util.List;


public interface IUser {

    public BaseResponseDTO registerAccount (UserDTO userDTO);

    public void updateDetails (UserDTO userDTO);

    List<GetAllUserDTO> getAllUsers(int pageNo);
}
