package com.example.CodeSnippetLambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CodeSnippetLambda.model.UserFavorites;
import com.example.CodeSnippetLambda.model.UserFavorites.UserFavoritesId;

@Repository
public interface UserFavoritesRepository extends JpaRepository<UserFavorites, UserFavoritesId> {
    List<UserFavorites> findByUserEmail(String userID);
    List<UserFavorites> findBySnippetSnippetID(Integer snippetID);
}
