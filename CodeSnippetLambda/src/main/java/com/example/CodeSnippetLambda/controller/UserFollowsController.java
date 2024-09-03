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

import com.example.CodeSnippetLambda.model.UserFollows;
import com.example.CodeSnippetLambda.service.UserFollowsService;

@RestController
@RequestMapping("/api/follows")
@CrossOrigin
public class UserFollowsController {

    @Autowired
    private UserFollowsService userFollowsService;

    // Follow a user
    @PostMapping
    public ResponseEntity<UserFollows> followUser(@RequestBody UserFollows userFollows) {
        try {
            UserFollows savedFollow = userFollowsService.followUser(userFollows);
            return new ResponseEntity<>(savedFollow, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Unfollow a user
    @DeleteMapping
    public ResponseEntity<HttpStatus> unfollowUser(@RequestBody UserFollows.UserFollowsId id) {
        try {
            userFollowsService.unfollowUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all followers of a user
    @GetMapping("/followers/{followingID}")
    public ResponseEntity<List<UserFollows>> getFollowers(@PathVariable("followingID") String followingID) {
        try {
            List<UserFollows> followers = userFollowsService.getFollowers(followingID);
            if (followers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(followers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all users a user is following
    @GetMapping("/following/{followerID}")
    public ResponseEntity<List<UserFollows>> getFollowing(@PathVariable("followerID") String followerID) {
        try {
            List<UserFollows> following = userFollowsService.getFollowing(followerID);
            if (following.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(following, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if a user is following another user
    @GetMapping("/{followerID}/{followingID}")
    public ResponseEntity<UserFollows> isFollowing(@PathVariable("followerID") String followerID, @PathVariable("followingID") String followingID) {
        Optional<UserFollows> followData = userFollowsService.isFollowing(new UserFollows.UserFollowsId(followerID, followingID));
        return followData.map(follow -> new ResponseEntity<>(follow, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}