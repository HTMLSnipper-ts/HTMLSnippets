package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.SnippetUpvotes;
import com.example.CodeSnippetLambda.model.SnippetUpvotes.SnippetUpvotesId;

public interface SnippetUpvotesService {

	SnippetUpvotes addUpvote(SnippetUpvotes snippetUpvotes);

	void removeUpvote(SnippetUpvotesId id);

	List<SnippetUpvotes> getUpvotesByUser(String userID);

	List<SnippetUpvotes> getUpvotesBySnippet(Integer snippetID);

	Optional<SnippetUpvotes> isUpvoted(SnippetUpvotesId snippetUpvotesId);

}
