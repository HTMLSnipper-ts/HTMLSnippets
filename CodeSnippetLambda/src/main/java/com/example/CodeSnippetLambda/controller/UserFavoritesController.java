package com.example.CodeSnippetLambda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CodeSnippetLambda.model.UserFavorites;
import com.example.CodeSnippetLambda.service.UserFavoritesService;

@RestController
@RequestMapping("/api/favorites")
public class UserFavoritesController {

    @Autowired
    private UserFavoritesService userFavoritesService;

    // Add a snippet to user's favorites
    @PostMapping
    public ResponseEntity<UserFavorites> addFavorite(@RequestBody UserFavorites userFavorites) {
        try {
            UserFavorites savedFavorite = userFavoritesService.addFavorite(userFavorites);
            return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Remove a snippet from user's favorites
    @DeleteMapping
    public ResponseEntity<HttpStatus> removeFavorite(@RequestBody UserFavorites.UserFavoritesId id) {
        try {
            userFavoritesService.removeFavorite(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all favorite snippets of a user
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<UserFavorites>> getFavoritesByUser(@PathVariable("userID") String userID) {
        try {
            List<UserFavorites> favorites = userFavoritesService.getFavoritesByUser(userID);
            if (favorites.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all users who favorited a specific snippet
    @GetMapping("/snippet/{snippetID}")
    public ResponseEntity<List<UserFavorites>> getUsersBySnippet(@PathVariable("snippetID") Integer snippetID) {
        try {
            List<UserFavorites> users = userFavoritesService.getUsersBySnippet(snippetID);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if a snippet is favorited by a user
    @GetMapping("/{userID}/{snippetID}")
    public ResponseEntity<UserFavorites> isFavorited(@PathVariable("userID") String userID, @PathVariable("snippetID") Integer snippetID) {
        Optional<UserFavorites> favoriteData = userFavoritesService.isFavorited(new UserFavorites.UserFavoritesId(userID, snippetID));
        return favoriteData.map(favorite -> new ResponseEntity<>(favorite, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}