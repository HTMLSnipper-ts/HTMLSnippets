package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.Snippet;
import com.example.CodeSnippetLambda.repository.SnippetRepository;
import com.example.CodeSnippetLambda.service.SnippetService;

@Service
public class SnippetServiceImpl implements SnippetService{

    @Autowired
    private SnippetRepository snippetRepository;

    // Create a new Snippet
    public Snippet createSnippet(Snippet snippet) {
        return snippetRepository.save(snippet);
    }

    // Get all Snippets
    public List<Snippet> getAllSnippets() {
        return snippetRepository.findAll();
    }

    // Get Snippets by UserID
    public List<Snippet> getSnippetsByUserID(String userID) {
        return snippetRepository.findByUserID(userID);
    }

    // Get Snippet by SnippetID
    public Optional<Snippet> getSnippetByID(int snippetID) {
        return snippetRepository.findById(snippetID);
    }

    // Update a Snippet
    public Optional<Snippet> updateSnippet(int snippetID, Snippet snippet) {
        return snippetRepository.findById(snippetID).map(existingSnippet -> {
            existingSnippet.setTitle(snippet.getTitle());
            existingSnippet.setDescription(snippet.getDescription());
            existingSnippet.setHtmlCode(snippet.getHtmlCode());
            existingSnippet.setCssCode(snippet.getCssCode());
            existingSnippet.setJsCode(snippet.getJsCode());
            return snippetRepository.save(existingSnippet);
        });
    }

    // Delete a Snippet
    public void deleteSnippet(int snippetID) {
        snippetRepository.deleteById(snippetID);
    }
}