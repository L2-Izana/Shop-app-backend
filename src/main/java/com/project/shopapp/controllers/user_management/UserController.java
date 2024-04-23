package com.project.shopapp.controllers.user_management;

import com.project.shopapp.dtos.auth.UserLoginDTO;
import com.project.shopapp.dtos.user_management.UserDTO;
import com.project.shopapp.services.user_management.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.badRequest().body("Password does not match");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());
        return ResponseEntity.ok(token);
    }
}
