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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CodeSnippetLambda.model.SnippetUpvotes;
import com.example.CodeSnippetLambda.service.SnippetUpvotesService;

@RestController
@RequestMapping("/api/upvotes")
@CrossOrigin
public class SnippetUpvotesController {

    @Autowired
    private SnippetUpvotesService snippetUpvotesService;

    // Add an upvote to a snippet
    @PostMapping
    public ResponseEntity<SnippetUpvotes> addUpvote(@RequestBody SnippetUpvotes snippetUpvotes) {
        try {
            SnippetUpvotes savedUpvote = snippetUpvotesService.addUpvote(snippetUpvotes);
            return new ResponseEntity<>(savedUpvote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Remove an upvote from a snippet
    @DeleteMapping
    public ResponseEntity<HttpStatus> removeUpvote(@RequestBody SnippetUpvotes.SnippetUpvotesId id) {
        try {
            snippetUpvotesService.removeUpvote(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all upvotes by a user
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<SnippetUpvotes>> getUpvotesByUser(@PathVariable("userID") String userID) {
        try {
            List<SnippetUpvotes> upvotes = snippetUpvotesService.getUpvotesByUser(userID);
            if (upvotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(upvotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all upvotes for a specific snippet
    @GetMapping("/snippet/{snippetID}")
    public ResponseEntity<List<SnippetUpvotes>> getUpvotesBySnippet(@PathVariable("snippetID") Integer snippetID) {
        try {
            List<SnippetUpvotes> upvotes = snippetUpvotesService.getUpvotesBySnippet(snippetID);
            if (upvotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(upvotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if a snippet is upvoted by a user
    @GetMapping("/{userID}/{snippetID}")
    public ResponseEntity<SnippetUpvotes> isUpvoted(@PathVariable("userID") String userID, @PathVariable("snippetID") Integer snippetID) {
        Optional<SnippetUpvotes> upvoteData = snippetUpvotesService.isUpvoted(new SnippetUpvotes.SnippetUpvotesId(userID, snippetID));
        return upvoteData.map(upvote -> new ResponseEntity<>(upvote, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
 // Get the number of upvotes for a specific snippet
    @GetMapping("/count/snippet/{snippetID}")
    public ResponseEntity<Long> getUpvoteCountBySnippet(@PathVariable("snippetID") Integer snippetID) {
        try {
            Long count = snippetUpvotesService.getUpvoteCountBySnippet(snippetID);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}