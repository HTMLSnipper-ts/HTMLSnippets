package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.SnippetUpvotes;
import com.example.CodeSnippetLambda.repository.SnippetUpvotesRepository;
import com.example.CodeSnippetLambda.service.SnippetUpvotesService;

@Service
public class SnippetUpvotesServiceImpl implements SnippetUpvotesService {

    @Autowired
    private SnippetUpvotesRepository snippetUpvotesRepository;

    // Add an upvote to a snippet
    public SnippetUpvotes addUpvote(SnippetUpvotes snippetUpvotes) {
        return snippetUpvotesRepository.save(snippetUpvotes);
    }

    // Remove an upvote from a snippet
    public void removeUpvote(SnippetUpvotes.SnippetUpvotesId id) {
        snippetUpvotesRepository.deleteById(id);
    }

    // Get all upvotes by a user
    public List<SnippetUpvotes> getUpvotesByUser(String userID) {
        return snippetUpvotesRepository.findByUserEmail(userID);
    }

    // Get all upvotes for a specific snippet
    public List<SnippetUpvotes> getUpvotesBySnippet(Integer snippetID) {
        return snippetUpvotesRepository.findBySnippetSnippetID(snippetID);
    }

    // Check if a snippet is upvoted by a user
    public Optional<SnippetUpvotes> isUpvoted(SnippetUpvotes.SnippetUpvotesId id) {
        return snippetUpvotesRepository.findById(id);
    }
}