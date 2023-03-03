package com.bctech.bookreviewproject.service.impl;

import com.bctech.bookreviewproject.constants.RoleType;
import com.bctech.bookreviewproject.dto.request.SignupDTO;
import com.bctech.bookreviewproject.dto.response.UserDTO;
import com.bctech.bookreviewproject.entity.Role;
import com.bctech.bookreviewproject.entity.User;
import com.bctech.bookreviewproject.exceptions.customexceptions.UserDetailsAlreadyExistException;
import com.bctech.bookreviewproject.repository.RoleRepository;
import com.bctech.bookreviewproject.repository.UserRepository;
import com.bctech.bookreviewproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public UserDTO createUser(SignupDTO signupDTO){
        log.info("Saving user {}", signupDTO.getUsername());
        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        User user = mapper.map(signupDTO, User.class);
        Role role = roleRepository.findByName(String.valueOf(RoleType.ROLE_USER));
        user.setRoles(List.of(role));
        try {
            return mapper.map(userRepository.save(user), UserDTO.class);
        } catch (DataIntegrityViolationException e) {
            log.error("Error while saving user {}", e.getMessage());
            throw new UserDetailsAlreadyExistException("User already exists with username: " + signupDTO.getUsername());
        }

    }

    @Override
    public UserDTO addRoleToUser(String username, String roleName) {
        log.info("Assign role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
            log.debug("User {} found", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

    @Override
    public UserDTO findByUsername(String username) {
        return mapper.map(userRepository.findByUsername(username), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return mapper.map(userRepository.findAll(), List.class);
    }

    @Override
    public Boolean deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
        if (user != null) userRepository.delete(user);

        else {return false;}
        return true;
    }

    @Override
    public UserDTO updateUserByUsername(String username, SignupDTO signupDTO){
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
        user.setUsername(signupDTO.getUsername());
        user.setFullName(signupDTO.getFullName());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        return (mapper.map(userRepository.save(user), UserDTO.class));
    }

}
