package com.awesomeforum.aaf.controller;

import com.awesomeforum.aaf.dto.userDto.UserRequest;
import com.awesomeforum.aaf.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PutMapping("/picture")
    public ResponseEntity<String> updateProfilePicture(@RequestBody UserRequest userDto) {
        userService.updateProfilePicture(userDto.id(), userDto.profilePicture());
        return ResponseEntity.ok("Profilna slika uspesno azurirana.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsername(@PathVariable Long id,
                                                 @RequestParam String username) {
        userService.updateUsername(id, username);
        return ResponseEntity.ok("Korisnicko ime uspesno azurirano.");
    }

}
