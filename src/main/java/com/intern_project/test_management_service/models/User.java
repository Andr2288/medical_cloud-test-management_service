package com.intern_project.test_management_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters.")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Size(min = 10, message = "Phone number must be at least 10 characters.")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must contain only digits and an optional '+' prefix.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @NotBlank(message = "Status is required.")
    @Column(name = "status")
    private String status;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TestRequest> testRequests;
}
