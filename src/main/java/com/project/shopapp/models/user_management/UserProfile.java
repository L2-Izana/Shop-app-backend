package com.project.shopapp.models.user_management;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dob;
}
