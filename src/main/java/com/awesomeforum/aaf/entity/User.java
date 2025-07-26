package com.awesomeforum.aaf.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name="profile_picture")
    private String profilePicture;



}