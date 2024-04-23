package com.project.shopapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class FormValidationController {
    public static ResponseEntity<?> handleFormValidationErrors(BindingResult result){
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        else {
            return ResponseEntity.ok().body("Valid form");
        }
    }
}
