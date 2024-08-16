package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.UserFollows;
import com.example.CodeSnippetLambda.repository.UserFollowsRepository;
import com.example.CodeSnippetLambda.service.UserFollowsService;

@Service
public class UserFollowsServiceImpl implements UserFollowsService{

    @Autowired
    private UserFollowsRepository userFollowsRepository;

    // Follow a user
    public UserFollows followUser(UserFollows userFollows) {
        return userFollowsRepository.save(userFollows);
    }

    // Unfollow a user
    public void unfollowUser(UserFollows.UserFollowsId id) {
        userFollowsRepository.deleteById(id);
    }

    // Get all followers of a user
    public List<UserFollows> getFollowers(String followingID) {
        return userFollowsRepository.findByFollowingEmail(followingID);
    }

    // Get all users that a user is following
    public List<UserFollows> getFollowing(String followerID) {
        return userFollowsRepository.findByFollowerEmail(followerID);
    }

    // Check if a user is following another user
    public Optional<UserFollows> isFollowing(UserFollows.UserFollowsId id) {
        return userFollowsRepository.findById(id);
    }
}