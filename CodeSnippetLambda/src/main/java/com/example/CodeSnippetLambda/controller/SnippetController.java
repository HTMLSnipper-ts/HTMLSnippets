package com.example.CodeSnippetLambda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CodeSnippetLambda.model.Snippet;
import com.example.CodeSnippetLambda.service.SnippetService;

@RestController
@RequestMapping("/api/snippets")
@CrossOrigin
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    // Create a new Snippet
    @PostMapping
    public ResponseEntity<Snippet> createSnippet(@RequestBody Snippet snippet) {
        try {
            Snippet savedSnippet = snippetService.createSnippet(snippet);
            return new ResponseEntity<>(savedSnippet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all Snippets
    @GetMapping
    public ResponseEntity<List<Snippet>> getAllSnippets() {
        try {
            List<Snippet> snippets = snippetService.getAllSnippets();
            if (snippets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(snippets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Snippet by SnippetID
    @GetMapping("/{snippetID}")
    public ResponseEntity<Snippet> getSnippetByID(@PathVariable("snippetID") int snippetID) {
        Optional<Snippet> snippetData = snippetService.getSnippetByID(snippetID);
        return snippetData.map(snippet -> new ResponseEntity<>(snippet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get Snippets by UserID
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Snippet>> getSnippetsByUserID(@PathVariable("userID") String userID) {
        try {
            List<Snippet> snippets = snippetService.getSnippetsByUserID(userID);
            if (snippets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(snippets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Update a Snippet
    @PutMapping("/{snippetID}")
    public ResponseEntity<Snippet> updateSnippet(@PathVariable("snippetID") int snippetID, @RequestBody Snippet snippet) {
        Optional<Snippet> updatedSnippet = snippetService.updateSnippet(snippetID, snippet);
        return updatedSnippet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a Snippet
    @DeleteMapping("/{snippetID}")
    public ResponseEntity<HttpStatus> deleteSnippet(@PathVariable("snippetID") int snippetID) {
        try {
            snippetService.deleteSnippet(snippetID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<List<Snippet>> searchSnippets(@PathVariable("query") String query) {
        try {
            List<Snippet> snippets = snippetService.searchSnippets(query);
            if (snippets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(snippets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
