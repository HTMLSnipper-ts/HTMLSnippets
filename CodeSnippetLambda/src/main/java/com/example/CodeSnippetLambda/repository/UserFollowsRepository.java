package com.example.CodeSnippetLambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CodeSnippetLambda.model.UserFollows;
import com.example.CodeSnippetLambda.model.UserFollows.UserFollowsId;

@Repository
public interface UserFollowsRepository extends JpaRepository<UserFollows, UserFollowsId> {
    List<UserFollows> findByFollowerEmail(String followerID);
    List<UserFollows> findByFollowingEmail(String followingID);
}