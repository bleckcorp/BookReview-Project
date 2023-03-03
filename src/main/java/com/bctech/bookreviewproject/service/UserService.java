package com.bctech.bookreviewproject.service;

import com.bctech.bookreviewproject.dto.request.SignupDTO;
import com.bctech.bookreviewproject.dto.response.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addRoleToUser(String username, String roleName);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();

    Boolean deleteUserByUsername(String userName);

    public UserDTO updateUserByUsername(String username, SignupDTO signupDTO);

    UserDTO createUser(SignupDTO request);
}
