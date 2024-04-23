package com.project.shopapp.dtos.user_management;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    @NotNull(message = "User's full name cannot be null")
    @JsonProperty("fullname")
    private String fullName;

    @NotNull(message = "User's phone number cannot be null")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    @JsonProperty("date_of_birth")
    private Date dob;

    @NotNull(message = "UserId is required")
    @JsonProperty("user_id")
    private Long userId;
}
