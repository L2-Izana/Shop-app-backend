package com.project.shopapp.services.user_management;

import com.project.shopapp.dtos.user_management.UserDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.auth.Role;
import com.project.shopapp.models.user_management.User;
import com.project.shopapp.repositories.auth.RoleRepository;
import com.project.shopapp.repositories.user_management.UserRepository;
import com.project.shopapp.services.user_management.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User createUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        User newUser = User.builder()
                .password(userDTO.getPassword())
                .isActive(true)
                .build();
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        return null;
    }
}
