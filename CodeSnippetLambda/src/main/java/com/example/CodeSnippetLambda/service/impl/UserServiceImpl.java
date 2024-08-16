package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.User;
import com.example.CodeSnippetLambda.repository.UserRepository;
import com.example.CodeSnippetLambda.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

    // Create a new User
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by Email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findById(email);
    }

    // Update a User
    public Optional<User> updateUser(String email, User user) {
        return userRepository.findById(email).map(existingUser -> {
            existingUser.setUsername(user.getUsername());
            existingUser.setPfpId(user.getPfpId());
            return userRepository.save(existingUser);
        });
    }

    // Delete a User
    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    // Delete all Users
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}