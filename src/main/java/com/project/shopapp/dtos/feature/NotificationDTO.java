package com.project.shopapp.dtos.feature;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 100)
    private String notificationType;

    private String content;

    @NotNull(message = "Sender id cannot be null")
    @JsonProperty("sender_id")
    private Long senderId;

    @NotNull(message = "Receiver id cannot be null")
    @JsonProperty("receiver_id")
    private Long receiverId;

    @NotNull(message = "is_read cannot be null, either false or true")
    @JsonProperty("is_read")
    private boolean isRead;
}
