package com.project.shopapp.dtos.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAvatarDTO {
    @NotNull(message = "UserId is required")
    @JsonProperty("user_profile_id")
    private Long userProfileId;

    @JsonProperty("image_url")
    private String imageUrl;
}
