package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.SnippetDownvotes;
import com.example.CodeSnippetLambda.repository.SnippetDownvotesRepository;
import com.example.CodeSnippetLambda.service.SnippetDownvotesService;

@Service
public class SnippetDownvotesServiceImpl implements SnippetDownvotesService {

    @Autowired
    private SnippetDownvotesRepository snippetDownvotesRepository;

    // Add a downvote to a snippet
    public SnippetDownvotes addDownvote(SnippetDownvotes snippetDownvotes) {
        return snippetDownvotesRepository.save(snippetDownvotes);
    }

    // Remove a downvote from a snippet
    public void removeDownvote(SnippetDownvotes.SnippetDownvotesId id) {
        snippetDownvotesRepository.deleteById(id);
    }

    // Get all downvotes by a user
    public List<SnippetDownvotes> getDownvotesByUser(String userID) {
        return snippetDownvotesRepository.findByUserEmail(userID);
    }

    // Get all downvotes for a specific snippet
    public List<SnippetDownvotes> getDownvotesBySnippet(Integer snippetID) {
        return snippetDownvotesRepository.findBySnippetSnippetID(snippetID);
    }

    // Check if a snippet is downvoted by a user
    public Optional<SnippetDownvotes> isDownvoted(SnippetDownvotes.SnippetDownvotesId id) {
        return snippetDownvotesRepository.findById(id);
    }
    
 // Get the number of downvotes for a specific snippet
    @Override
    public Long getDownvoteCountBySnippet(Integer snippetID) {
        return snippetDownvotesRepository.countBySnippetSnippetID(snippetID);
    }
}