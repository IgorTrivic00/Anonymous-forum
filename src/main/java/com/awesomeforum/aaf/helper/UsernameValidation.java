package com.awesomeforum.aaf.helper;

import com.awesomeforum.aaf.entity.User;
import com.awesomeforum.aaf.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UsernameValidation {
    private final UserRepository userRepository;

    public UsernameValidation(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User getUser(String author, String profilePicture) {
        if (author != null && !author.isBlank()) {
            return userRepository.findByUsername(author)
                    .orElseGet(() -> userRepository.save(
                            User.builder()
                                    .username(author)
                                    .profilePicture(profilePicture)
                                    .build()
                    ));
        } else {
            String randomUsername = "user-" + UUID.randomUUID();
            return userRepository.save(
                    User.builder()
                            .username(randomUsername)
                            .profilePicture(profilePicture)
                            .build()
            );
        }
    }

}
