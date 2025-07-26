package com.awesomeforum.aaf.controller;

import com.awesomeforum.aaf.dto.commentDto.CommentRequest;
import com.awesomeforum.aaf.dto.commentDto.CommentResponse;
import com.awesomeforum.aaf.dto.commentDto.ReplyToCommentRequest;
import com.awesomeforum.aaf.entity.Comment;
import com.awesomeforum.aaf.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request) {


        Comment comment = commentService.createComment(request);

        CommentResponse response = new CommentResponse(
                //comment.getPost().getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getUser().getProfilePicture(),
                comment.getCreatedAt()
        );

        return ResponseEntity
                .created(URI.create("/api/comments/" + comment.getId()))
                .body(response);
    }
    @PostMapping("/reply")
    public ResponseEntity<CommentResponse> replyToComment(@RequestBody ReplyToCommentRequest request) {
        Comment reply = commentService.replyToComment(request);
        CommentResponse response = new CommentResponse(
               // reply.getPost().getId(),
                reply.getContent(),
                reply.getUser().getUsername(),
                reply.getUser().getProfilePicture(),
                reply.getCreatedAt()
        );
        return ResponseEntity
                .created(URI.create("/api/comments/" + reply.getId()))
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.ok("Komentar obrisan");
    }

    @GetMapping("/replies/{parentId}")
    public ResponseEntity<List<CommentResponse>> getReplies(@PathVariable Long parentId) {
        List<CommentResponse> replies = commentService.getRepliesByParentId(parentId);
        return ResponseEntity.ok(replies);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsByPostId(@PathVariable Long postId) {
        List<CommentResponse> comments = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}
