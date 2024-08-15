package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.UserFavorites;
import com.example.CodeSnippetLambda.model.UserFavorites.UserFavoritesId;

public interface UserFavoritesService {

	UserFavorites addFavorite(UserFavorites userFavorites);

	void removeFavorite(UserFavoritesId id);

	List<UserFavorites> getFavoritesByUser(String userID);

	List<UserFavorites> getUsersBySnippet(Integer snippetID);

	Optional<UserFavorites> isFavorited(UserFavoritesId userFavoritesId);

}
