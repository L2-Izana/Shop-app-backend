package com.project.shopapp.dtos.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    @NotNull(message = "Notification type cannot be null")
    @JsonProperty("notification_type")
    private String notificationType;

    private String content;

    @NotNull(message = "Sender Id cannot be null")
    @JsonProperty("sender_id")
    private Long senderId;

    @NotNull(message = "Receiver Id cannot be null")
    @JsonProperty("receiver_id")
    private Long receiver_id;

    @NotNull(message = "Is read cannot be null, either 0 or 1")
    @JsonProperty("is_read")
    private int isRead;
}
