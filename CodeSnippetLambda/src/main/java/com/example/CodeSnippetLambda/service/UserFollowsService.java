package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.UserFollows;
import com.example.CodeSnippetLambda.model.UserFollows.UserFollowsId;

public interface UserFollowsService {

	UserFollows followUser(UserFollows userFollows);

	void unfollowUser(UserFollowsId id);

	List<UserFollows> getFollowers(String followingID);

	List<UserFollows> getFollowing(String followerID);

	Optional<UserFollows> isFollowing(UserFollowsId userFollowsId);

}
