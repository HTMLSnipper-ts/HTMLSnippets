package com.example.CodeSnippetLambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CodeSnippetLambda.model.UserBlocks;
import com.example.CodeSnippetLambda.model.UserBlocks.UserBlocksId;

@Repository
public interface UserBlocksRepository extends JpaRepository<UserBlocks, UserBlocksId> {
    List<UserBlocks> findByBlockerEmail(String blockerID);
    List<UserBlocks> findByBlockedEmail(String blockedID);
}