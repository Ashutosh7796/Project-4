package com.Salaryfy.Interfaces;

import com.Salaryfy.Dto.GetAllUserDTO;
import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.Dto.UserResponseDto;
import com.Salaryfy.Entity.User;
import com.Salaryfy.utils.BaseResponseDTO;

import java.util.List;

public interface IUser {

    public BaseResponseDTO registerAccount (UserDTO userDTO);

    public void updateDetails (UserDTO userDTO);


    List<GetAllUserDTO> getAllUsers(int pageNo);

    List<UserDTO> findByStatus(String status, int pageNo);

    public UserDTO getUserById(Integer userId);
}
