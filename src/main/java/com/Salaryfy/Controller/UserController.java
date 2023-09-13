package com.Salaryfy.Controller;

import com.Salaryfy.Dto.*;
import com.Salaryfy.Dto.User.RUserSingleDto;
import com.Salaryfy.Exception.*;
import com.Salaryfy.Interfaces.IUser;
import com.Salaryfy.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUser userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            BaseResponseDTO response = userService.registerAccount(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseDTO("Successful", response.getMessage()));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "User Already Exists"));
        } catch (BaseException c) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "Invalid Role"));
        }catch (EmailNotVerifiedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("unsuccessful", "Email not verified"));
        }
    }


    @PatchMapping("/updateUserDetails")
    public ResponseEntity<?> updateDetails(@RequestBody UserDTO userDTO) {
        try {
            userService.updateDetails(userDTO);

            UserupdateDTO userupdateDTO = new UserupdateDTO("success");
            userupdateDTO.setMessage("User Details Updated");

            return ResponseEntity.status(HttpStatus.OK).body(userupdateDTO);
        } catch (UserNotFoundException e) {
            UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getUserById(@RequestParam Integer userId) {
        try {


            RUserSingleDto responseDto = new RUserSingleDto("Success");

            System.out.println("59");
            responseDto.setResponse(userService.getUserById(userId));
            responseDto.getResponse().setPassword("");
            System.out.println("61");
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (UserNotFoundException e) {
            UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseAllUsersDto> getAllUser(@RequestParam int pageNo) {

        try {
            List<GetAllUserDTO> list = userService.getAllUsers(pageNo);
            ResponseAllUsersDto responseAllUsersDto = new ResponseAllUsersDto("success");
            responseAllUsersDto.setList(list);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllUsersDto);
        } catch (UserNotFoundException e) {
            ResponseAllUsersDto responseAllCarDto = new ResponseAllUsersDto("unsuccess");
            responseAllCarDto.setException("car not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllCarDto);
        } catch (PageNotFoundException exception) {
            ResponseAllUsersDto responseAllCarDto = new ResponseAllUsersDto("unsuccess");
            responseAllCarDto.setException("page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllCarDto);
        }
    }

    @GetMapping("/getAllUsersWithStatus")
    public ResponseEntity<UserResponseDto> findByStatus(@RequestParam String status, @RequestParam int pageNo) {

        try {
            List<UserDTO> user = userService.findByStatus(status, pageNo);
            UserResponseDto userResponseDto = new UserResponseDto("Successful");
            userResponseDto.setUsers(user);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        } catch (UserNotFoundException e) {
            UserResponseDto userResponseDto = new UserResponseDto("Unsuccessful");
            userResponseDto.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponseDto);
        } catch (PageNotFoundException e) {
            UserResponseDto userResponseDto = new UserResponseDto("Unsuccessful");
            userResponseDto.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponseDto);
        }
    }


}
