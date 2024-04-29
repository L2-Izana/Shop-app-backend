package com.project.shopapp.models.user_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_avatars")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @Column(name = "image_data")
    private byte[] imageData;
}
