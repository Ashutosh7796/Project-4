package com.Salaryfy.Controller;

import com.Salaryfy.Dto.UserDto;
import com.Salaryfy.Dto.UserDto;
import com.Salaryfy.Exception.BaseException;
import com.Salaryfy.Exception.UserAlreadyExistException;
import com.Salaryfy.Interfaces.IUser;
import com.Salaryfy.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUser userRepository;

    @PostMapping ("/register")
    public ResponseEntity<BaseResponseDTO> registerUser (@RequestBody UserDto userDTO) {
        try {
            BaseResponseDTO response = userRepository.registerAccount(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", response.getMessage()));

        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "User Already Exists"));
        } catch (BaseException c) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "Invalid Role"));
        }
    }

}
