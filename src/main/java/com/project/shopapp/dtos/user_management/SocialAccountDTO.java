package com.project.shopapp.dtos.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class SocialAccountDTO {
    private String provider;

    @JsonProperty("provider_id")
    private String providerId;

    private String email;

    private String name;

    @NotNull(message = "User id cannot be null")
    @JsonProperty("user_profile_id")
    private Long userProfileId;
}
