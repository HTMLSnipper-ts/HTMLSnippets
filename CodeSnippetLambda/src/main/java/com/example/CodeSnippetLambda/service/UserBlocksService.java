package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.UserBlocks;
import com.example.CodeSnippetLambda.model.UserBlocks.UserBlocksId;

public interface UserBlocksService {

	UserBlocks blockUser(UserBlocks userBlocks);

	void unblockUser(UserBlocksId id);

	List<UserBlocks> getBlockedUsers(String blockerID);

	List<UserBlocks> getBlockers(String blockedID);

	Optional<UserBlocks> isBlocked(UserBlocksId userBlocksId);

}
