package com.Salaryfy.Services;

import com.Salaryfy.Dto.GetAllUserDTO;
import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.Entity.Role;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.BaseException;
import com.Salaryfy.Exception.PageNotFoundException;
import com.Salaryfy.Exception.UserAlreadyExistException;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Interfaces.IUser;
import com.Salaryfy.Repository.RoleRepository;
import com.Salaryfy.Repository.UserRepository;
import com.Salaryfy.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUser {



    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponseDTO registerAccount(UserDTO userDTO) {
        BaseResponseDTO response = new BaseResponseDTO();

        validateAccount(userDTO);

        User user = insertUser(userDTO);

        try { userRepository.save(user);
            response.setCode(String.valueOf(HttpStatus.OK.value()));
            response.setMessage("Account created");
        }catch(UserAlreadyExistException e) {
         response.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
         response.setMessage("User Already Exists");
         } catch(BaseException c) {
        response.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        response.setMessage("invalid Role");
        }

        return response;
    }

    private User insertUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setMobileNo(userDTO.getMobile_no());
        user.setDate(userDTO.getDate());
        user.setUserProfileType(userDTO.getUserProfileType());
        user.setProfilePhoto(userDTO.getProfilePhoto());
        user.setSubType(userDTO.getSubType());

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(userDTO.getRole());
        roles.add(role);
        user.setRoles(roles);

        return user;
    }

    private void validateAccount(UserDTO userDTO) {
        // validate null data
        if (ObjectUtils.isEmpty(userDTO)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Data must not be empty");
        }

        // validate duplicate username
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (!ObjectUtils.isEmpty(user)) {
            throw new UserAlreadyExistException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Username already exists");
        }

        // validate role
        List<String> roles = roleRepository.findAll().stream().map(Role::getName).toList();
        if (!roles.contains(userDTO.getRole())) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid role");
        }
    }

    @Override
    public void updateDetails(UserDTO userDTO) {
        Optional<User> userUpdateOptional = userRepository.findById(userDTO.getUser_id());
        if (userUpdateOptional.isPresent()) {
            User user = userUpdateOptional.get();
            user.setEmail(userDTO.getEmail());
            user.setMobileNo(userDTO.getMobile_no());
            user.setFullName(userDTO.getFullName());
            user.setProfilePhoto(userDTO.getProfilePhoto());
            user.setSubType(userDTO.getSubType());
            user.setUserProfileType(userDTO.getUserProfileType());
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with ID");
        }

    }

    @Override
    public List<GetAllUserDTO> getAllUsers(int pageNo) {
        List<User> listOfUsers = userRepository.findAll();

        if((pageNo*10)>listOfUsers.size()-1){
            throw new PageNotFoundException("page not found");

        }
        if(listOfUsers.size()<=0){throw new UserNotFoundException("user not found",HttpStatus.NOT_FOUND);}

        List<GetAllUserDTO> listOfUsersDto = new ArrayList<>();

        int pageStart=pageNo*10;
        int pageEnd=pageStart+10;
        int diff=(listOfUsers.size()) - pageStart;
        for(int counter=pageStart,i=1;counter<pageEnd;counter++,i++){
            if(pageStart>listOfUsers.size()){break;}


            GetAllUserDTO userDto = new GetAllUserDTO (listOfUsers.get(counter));
            userDto.setUser_id(listOfUsers.get(counter).getUser_id());
            listOfUsersDto.add(userDto);


            if(diff == i){
                break;
            }
        }

        return listOfUsersDto;
    }
}
