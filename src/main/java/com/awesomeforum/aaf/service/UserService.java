package com.awesomeforum.aaf.service;

import com.awesomeforum.aaf.advice.exception.AlreadyExistsException;
import com.awesomeforum.aaf.advice.exception.NotFoundException;
import com.awesomeforum.aaf.entity.User;
import com.awesomeforum.aaf.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateProfilePicture(Long id, String newUrl) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Korisnik sa id-em '" + id + "' ne postoji."));

        user.setProfilePicture(newUrl);
        userRepository.save(user);
    }


    public void updateUsername(Long id, String newUsername) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Korisnik sa ID = " + id + " nije pronadjen."));

        if (userRepository.existsByUsername(newUsername)) {
            throw new AlreadyExistsException("Korisnicko ime '" + newUsername + "' je vec zauzeto.");
        }

        user.setUsername(newUsername);
        userRepository.save(user);
    }
}
