package com.awesomeforum.aaf.repository;

import com.awesomeforum.aaf.entity.Post;
import com.awesomeforum.aaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
 List<Post> findByUser(User user);

 @Query("SELECT p FROM Post p WHERE p.user.username = :username")
 List<Post> findAllByUsername(@Param("username") String username);

}