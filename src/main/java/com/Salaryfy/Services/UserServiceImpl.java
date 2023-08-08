package com.Salaryfy.Services;

import com.Salaryfy.Dto.UserDTO;
import com.Salaryfy.Entity.Role;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.BaseException;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
}
