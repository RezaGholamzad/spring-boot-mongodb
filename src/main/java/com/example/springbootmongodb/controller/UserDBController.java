package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.model.User;
import com.example.springbootmongodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserDBController {

    // Spring Data-centric approach and comes with more flexible and complex API operations,
    // based on the well-known access patterns in all Spring Data projects.
    // this works with user-db database
    private final UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers() {
        log.info("Getting all users.");
        return userRepository.findAll();
    }

    @GetMapping( "/{userId}")
    public Optional<User> getUser(@PathVariable String userId) {
        log.info("Getting user with ID: {}.", userId);
        return userRepository.findById(userId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        log.info("Saving user.");
        return userRepository.save(user);
    }

    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get().getUserSettings();
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().getUserSettings().put(key, value);
            userRepository.save(user.get());
            return "Key added";
        } else {
            return "User not found.";
        }
    }
}
