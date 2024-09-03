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

import com.example.CodeSnippetLambda.model.SnippetDownvotes;
import com.example.CodeSnippetLambda.service.SnippetDownvotesService;

@RestController
@RequestMapping("/api/downvotes")
public class SnippetDownvotesController {

    @Autowired
    private SnippetDownvotesService snippetDownvotesService;

    // Add a downvote to a snippet
    @PostMapping
    public ResponseEntity<SnippetDownvotes> addDownvote(@RequestBody SnippetDownvotes snippetDownvotes) {
        try {
            SnippetDownvotes savedDownvote = snippetDownvotesService.addDownvote(snippetDownvotes);
            return new ResponseEntity<>(savedDownvote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Remove a downvote from a snippet
    @DeleteMapping
    public ResponseEntity<HttpStatus> removeDownvote(@RequestBody SnippetDownvotes.SnippetDownvotesId id) {
        try {
            snippetDownvotesService.removeDownvote(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all downvotes by a user
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<SnippetDownvotes>> getDownvotesByUser(@PathVariable("userID") String userID) {
        try {
            List<SnippetDownvotes> downvotes = snippetDownvotesService.getDownvotesByUser(userID);
            if (downvotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(downvotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all downvotes for a specific snippet
    @GetMapping("/snippet/{snippetID}")
    public ResponseEntity<List<SnippetDownvotes>> getDownvotesBySnippet(@PathVariable("snippetID") Integer snippetID) {
        try {
            List<SnippetDownvotes> downvotes = snippetDownvotesService.getDownvotesBySnippet(snippetID);
            if (downvotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(downvotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if a snippet is downvoted by a user
    @GetMapping("/{userID}/{snippetID}")
    public ResponseEntity<SnippetDownvotes> isDownvoted(@PathVariable("userID") String userID, @PathVariable("snippetID") Integer snippetID) {
        Optional<SnippetDownvotes> downvoteData = snippetDownvotesService.isDownvoted(new SnippetDownvotes.SnippetDownvotesId(userID, snippetID));
        return downvoteData.map(downvote -> new ResponseEntity<>(downvote, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
 // Get the number of downvotes for a specific snippet
    @GetMapping("/count/snippet/{snippetID}")
    public ResponseEntity<Long> getDownvoteCountBySnippet(@PathVariable("snippetID") Integer snippetID) {
        try {
            Long count = snippetDownvotesService.getDownvoteCountBySnippet(snippetID);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}