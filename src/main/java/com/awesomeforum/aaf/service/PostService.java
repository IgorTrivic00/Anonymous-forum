package com.awesomeforum.aaf.service;
import com.awesomeforum.aaf.advice.exception.ContentBadRequestException;
import com.awesomeforum.aaf.advice.exception.NotFoundException;
import com.awesomeforum.aaf.dto.postDto.PostResponse;
import com.awesomeforum.aaf.entity.Post;
import com.awesomeforum.aaf.entity.User;
import com.awesomeforum.aaf.entity.type.PostType;
import com.awesomeforum.aaf.helper.UsernameValidation;
import com.awesomeforum.aaf.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.awesomeforum.aaf.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final WhoaService whoaService;
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final UsernameValidation usernameHelper;

    public PostService(PostRepository postRepository, WhoaService whoaService, UserRepository userRepository,CommentService commentService, UsernameValidation usernameHelper) {

        this.postRepository = postRepository;
        this.whoaService = whoaService;
        this.userRepository=userRepository;
        this.commentService=commentService;
        this.usernameHelper=usernameHelper;
    }
    @Transactional
    public Post createPost(String content,PostType requestedType,User user, String link) {
        if (content == null || content.trim().isEmpty()) {
            throw new ContentBadRequestException("Content mora biti popunjen!");
        }
        user= usernameHelper.getUser(user.getUsername(),user.getProfilePicture());
        String randomWhoa = whoaService.createRandomWhoa();
        // po pravilu frontend developer bi na ui omogucio da je po defaultu cekirano PLAIN ili MARKDOWN, tako da je nemoguce da Post type bude null,
        // ili bilo koja druga vrednost sem te dve vrednosti.
        Post post = Post.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .postType(requestedType)
                .link(link)
                .whoa(randomWhoa)
                .user(user)
                .build();
        return postRepository.save(post);
    }
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
    public List<PostResponse> findAllByUsername(String username) {
        List<Post> posts = postRepository.findAllByUsername(username);

        if (posts == null || posts.isEmpty()) {
            throw new NotFoundException("Ne postoji korisnik sa username-om " + username + " , samim tim ne postoje postovi za nepostojeceg korisnika! ");
        }

        return posts.stream()
                .map(post -> new PostResponse(
                        post.getContent(),
                        post.getPostType(),
                        post.getLink(),
                        post.getCreatedAt(),
                       username

                ))
                .toList();
    }

    @Transactional
    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post sa id-em " + postId + " ne postoji"));
        postRepository.delete(post);
    }




    }


