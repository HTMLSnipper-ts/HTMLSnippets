package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.SnippetDownvotes;
import com.example.CodeSnippetLambda.model.SnippetDownvotes.SnippetDownvotesId;

public interface SnippetDownvotesService {

	SnippetDownvotes addDownvote(SnippetDownvotes snippetDownvotes);

	void removeDownvote(SnippetDownvotesId id);

	List<SnippetDownvotes> getDownvotesByUser(String userID);

	List<SnippetDownvotes> getDownvotesBySnippet(Integer snippetID);

	Optional<SnippetDownvotes> isDownvoted(SnippetDownvotesId snippetDownvotesId);

	Long getDownvoteCountBySnippet(Integer snippetID);

}
