package com.awesomeforum.aaf.service;
import com.awesomeforum.aaf.advice.exception.NotFoundException;
import com.awesomeforum.aaf.dto.commentDto.CommentRequest;
import com.awesomeforum.aaf.dto.commentDto.CommentResponse;
import com.awesomeforum.aaf.dto.commentDto.ReplyToCommentRequest;
import com.awesomeforum.aaf.entity.Comment;
import com.awesomeforum.aaf.entity.Post;
import com.awesomeforum.aaf.entity.User;
import com.awesomeforum.aaf.helper.UsernameValidation;
import com.awesomeforum.aaf.repository.CommentRepository;
import com.awesomeforum.aaf.repository.PostRepository;
import com.awesomeforum.aaf.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UsernameValidation usernameHelper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, UsernameValidation usernameHelper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.usernameHelper=usernameHelper;
    }
@Transactional
    public Comment createComment(CommentRequest request) {
    User user= usernameHelper.getUser(request.user().getUsername(),request.user().getProfilePicture());
        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new NotFoundException("Post sa id-em = " + request.postId() + " ne postoji."));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }



    public Comment replyToComment(ReplyToCommentRequest request) {
        User user= usernameHelper.getUser(request.user().getUsername(),request.user().getProfilePicture());

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new NotFoundException("Post sa id-em = " + request.postId() + " ne postoji."));

        Comment parent = commentRepository.findById(request.parentCommentId())
                .orElseThrow(() -> new NotFoundException("Komentar  sa  id-em = " + request.parentCommentId() + " na koji zelis da odgovoris ne postoji!."));

        Comment reply = Comment.builder()
                .user(user)
                .post(post)
                .content(request.content())
                .parentComment(parent)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(reply);
    }
    public void deleteCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Komentar sa id-em = " + commentId + " ne postoji."));
        commentRepository.delete(comment);
    }

    public List<CommentResponse> getRepliesByParentId(Long parentCommentId) {
        List<Comment> replies = commentRepository.findAllByParentCommentId(parentCommentId);

        return replies.stream().map(reply -> new CommentResponse(
                //reply.getId(),
                reply.getContent(),
                reply.getUser().getUsername(),
                reply.getUser().getProfilePicture(),
                reply.getCreatedAt()
        )).toList();
    }

    public List<CommentResponse> getAllCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return comments.stream().map(comment -> new CommentResponse(

                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getUser().getProfilePicture(),
                comment.getCreatedAt()
        )).toList();
    }

}
