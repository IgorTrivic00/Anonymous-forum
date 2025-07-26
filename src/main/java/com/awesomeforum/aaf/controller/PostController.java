package com.awesomeforum.aaf.controller;

import com.awesomeforum.aaf.dto.postDto.PostRequest;
import com.awesomeforum.aaf.dto.postDto.PostResponse;
import com.awesomeforum.aaf.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.awesomeforum.aaf.service.PostService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;


    public PostController(PostService postService){
        this.postService=postService;

    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        Post saved = postService.createPost(
                request.content(),
                request.postType(),
                request.user(),
                request.link()


        );
        return ResponseEntity
                .created(URI.create("/api/posts/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts=postService.getAllPosts();
        return ResponseEntity
                .ok(posts);
    }

    @GetMapping("{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username) {
        List<PostResponse> posts = postService.findAllByUsername(username);

        return ResponseEntity.ok(posts);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.ok("Obrisan je post");
    }



}
