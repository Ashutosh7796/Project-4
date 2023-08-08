package com.Salaryfy.Controller;

import com.Salaryfy.Dto.GetAllUserDTO;
import com.Salaryfy.Dto.ResponseAllUsersDto;
import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.Dto.UserupdateDTO;
import com.Salaryfy.Exception.BaseException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserAlreadyExistException;
import com.Salaryfy.Exception.UserNotFoundException;
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

    @PostMapping ("/register")
    public ResponseEntity<BaseResponseDTO> registerUser (@RequestBody UserDTO userDTO) {
        try {
            BaseResponseDTO response = userService.registerAccount(userDTO);
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
            userService.updateDetails(userDTO);
           UserupdateDTO userupdateDTO= new UserupdateDTO("success");
           userupdateDTO.setMessage("User Details Updated");

           return ResponseEntity.status(HttpStatus.OK).body(userupdateDTO);
        }catch(UserNotFoundException e) {
            UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

         }
     }
    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseAllUsersDto> getAllUser(@RequestParam int pageNo){

        try {
            List<GetAllUserDTO> list= userService.getAllUsers(pageNo);
            ResponseAllUsersDto responseAllUsersDto = new ResponseAllUsersDto("success");
            responseAllUsersDto.setList(list);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllUsersDto);
        } catch (UserNotFoundException e){
            ResponseAllUsersDto responseAllCarDto = new ResponseAllUsersDto("unsuccess");
            responseAllCarDto.setException("car not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllCarDto);
        }
        catch (PageNotFoundException exception){
            ResponseAllUsersDto responseAllCarDto = new ResponseAllUsersDto("unsuccess");
            responseAllCarDto.setException("page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAllCarDto);
        }
    }

}
