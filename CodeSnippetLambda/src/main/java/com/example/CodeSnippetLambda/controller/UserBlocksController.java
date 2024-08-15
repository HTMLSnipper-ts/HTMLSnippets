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

import com.example.CodeSnippetLambda.model.UserBlocks;
import com.example.CodeSnippetLambda.service.UserBlocksService;

@RestController
@RequestMapping("/api/blocks")
public class UserBlocksController {

    @Autowired
    private UserBlocksService userBlocksService;

    // Block a user
    @PostMapping
    public ResponseEntity<UserBlocks> blockUser(@RequestBody UserBlocks userBlocks) {
        try {
            UserBlocks savedBlock = userBlocksService.blockUser(userBlocks);
            return new ResponseEntity<>(savedBlock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Unblock a user
    @DeleteMapping
    public ResponseEntity<HttpStatus> unblockUser(@RequestBody UserBlocks.UserBlocksId id) {
        try {
            userBlocksService.unblockUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all users blocked by a user
    @GetMapping("/blocked/{blockerID}")
    public ResponseEntity<List<UserBlocks>> getBlockedUsers(@PathVariable("blockerID") String blockerID) {
        try {
            List<UserBlocks> blockedUsers = userBlocksService.getBlockedUsers(blockerID);
            if (blockedUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(blockedUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all users blocking a user
    @GetMapping("/blockers/{blockedID}")
    public ResponseEntity<List<UserBlocks>> getBlockers(@PathVariable("blockedID") String blockedID) {
        try {
            List<UserBlocks> blockers = userBlocksService.getBlockers(blockedID);
            if (blockers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(blockers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if a user has blocked another user
    @GetMapping("/{blockerID}/{blockedID}")
    public ResponseEntity<UserBlocks> isBlocked(@PathVariable("blockerID") String blockerID, @PathVariable("blockedID") String blockedID) {
        Optional<UserBlocks> blockData = userBlocksService.isBlocked(new UserBlocks.UserBlocksId(blockerID, blockedID));
        return blockData.map(block -> new ResponseEntity<>(block, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}