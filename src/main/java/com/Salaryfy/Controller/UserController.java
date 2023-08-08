package com.Salaryfy.Controller;

import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.Dto.UserupdateDTO;
import com.Salaryfy.Exception.BaseException;
import com.Salaryfy.Exception.UserAlreadyExistException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IUser;
import com.Salaryfy.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUser userRepository;

    @PostMapping ("/register")
    public ResponseEntity<BaseResponseDTO> registerUser (@RequestBody UserDTO userDTO) {
        try {
            BaseResponseDTO response = userRepository.registerAccount(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", response.getMessage()));

        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "User Already Exists"));
        } catch (BaseException c) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "Invalid Role"));
        }
    }


     @PatchMapping ("/updateUserDetails")
    public ResponseEntity<?> updateDetails (@RequestBody UserDTO userDTO){
        try {
            userRepository.updateDetails(userDTO);
           UserupdateDTO userupdateDTO= new UserupdateDTO("success");
           userupdateDTO.setMessage("User Details Updated");

           return ResponseEntity.status(HttpStatus.OK).body(userupdateDTO);
        }catch(UserNotFoundException e) {
            UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

         }
     }

}
