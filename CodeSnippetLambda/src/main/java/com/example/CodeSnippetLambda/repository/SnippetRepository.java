package com.example.CodeSnippetLambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CodeSnippetLambda.model.Snippet;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Integer> {
    // Custom query to find all snippets by a specific user
    List<Snippet> findByUserID(String userID);
}