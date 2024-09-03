package com.example.CodeSnippetLambda.service;

import java.util.List;
import java.util.Optional;

import com.example.CodeSnippetLambda.model.Snippet;

public interface SnippetService {

	Snippet createSnippet(Snippet snippet);

	List<Snippet> getAllSnippets();

	Optional<Snippet> getSnippetByID(int snippetID);

	List<Snippet> getSnippetsByUserID(String userID);

	Optional<Snippet> updateSnippet(int snippetID, Snippet snippet);

	void deleteSnippet(int snippetID);

	List<Snippet> searchSnippets(String query);

}
