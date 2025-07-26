package com.awesomeforum.aaf.repository;


import com.awesomeforum.aaf.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByParentCommentId(Long parentCommentId);
    List<Comment> findAllByPostId(Long postId);
}