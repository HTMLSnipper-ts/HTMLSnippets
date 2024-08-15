package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.UserFavorites;
import com.example.CodeSnippetLambda.repository.UserFavoritesRepository;
import com.example.CodeSnippetLambda.service.UserFavoritesService;

@Service
public class UserFavoritesServiceImpl implements UserFavoritesService{

    @Autowired
    private UserFavoritesRepository userFavoritesRepository;

    // Add a snippet to user's favorites
    public UserFavorites addFavorite(UserFavorites userFavorites) {
        return userFavoritesRepository.save(userFavorites);
    }

    // Remove a snippet from user's favorites
    public void removeFavorite(UserFavorites.UserFavoritesId id) {
        userFavoritesRepository.deleteById(id);
    }

    // Get all favorite snippets of a user
    public List<UserFavorites> getFavoritesByUser(String userID) {
        return userFavoritesRepository.findByUserEmail(userID);
    }

    // Get all users who favorited a specific snippet
    public List<UserFavorites> getUsersBySnippet(Integer snippetID) {
        return userFavoritesRepository.findBySnippetSnippetID(snippetID);
    }

    // Check if a snippet is favorited by a user
    public Optional<UserFavorites> isFavorited(UserFavorites.UserFavoritesId id) {
        return userFavoritesRepository.findById(id);
    }
}