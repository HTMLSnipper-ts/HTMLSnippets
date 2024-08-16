package com.example.CodeSnippetLambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CodeSnippetLambda.model.SnippetUpvotes;
import com.example.CodeSnippetLambda.model.SnippetUpvotes.SnippetUpvotesId;

@Repository
public interface SnippetUpvotesRepository extends JpaRepository<SnippetUpvotes, SnippetUpvotesId> {
    List<SnippetUpvotes> findByUserEmail(String userID);
    List<SnippetUpvotes> findBySnippetSnippetID(Integer snippetID);
}