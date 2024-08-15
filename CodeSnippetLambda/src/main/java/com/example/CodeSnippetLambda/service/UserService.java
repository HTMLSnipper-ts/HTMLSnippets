package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.User;

public interface UserService {

	User createUser(User user);

	List<User> getAllUsers();

	Optional<User> getUserByEmail(String email);

	Optional<User> updateUser(String email, User user);

	void deleteUser(String email);

	void deleteAllUsers();


}
