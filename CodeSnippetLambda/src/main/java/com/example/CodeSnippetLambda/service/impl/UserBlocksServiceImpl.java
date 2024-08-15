package com.example.CodeSnippetLambda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CodeSnippetLambda.model.UserBlocks;
import com.example.CodeSnippetLambda.repository.UserBlocksRepository;
import com.example.CodeSnippetLambda.service.UserBlocksService;

@Service
public class UserBlocksServiceImpl implements UserBlocksService {

    @Autowired
    private UserBlocksRepository userBlocksRepository;

    // Block a user
    public UserBlocks blockUser(UserBlocks userBlocks) {
        return userBlocksRepository.save(userBlocks);
    }

    // Unblock a user
    public void unblockUser(UserBlocks.UserBlocksId id) {
        userBlocksRepository.deleteById(id);
    }

    // Get all users blocked by a user
    public List<UserBlocks> getBlockedUsers(String blockerID) {
        return userBlocksRepository.findByBlockerEmail(blockerID);
    }

    // Get all users blocking a user
    public List<UserBlocks> getBlockers(String blockedID) {
        return userBlocksRepository.findByBlockedEmail(blockedID);
    }

    // Check if a user has blocked another user
    public Optional<UserBlocks> isBlocked(UserBlocks.UserBlocksId id) {
        return userBlocksRepository.findById(id);
    }
}