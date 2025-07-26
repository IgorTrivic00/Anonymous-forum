package com.awesomeforum.aaf.entity;

import com.awesomeforum.aaf.entity.type.PostType;
import jakarta.persistence.Table;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "post_type")
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Column(name = "link")
    private String link;
    @Column(name = "whoa")
    private String whoa;


}