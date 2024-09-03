package com.example.CodeSnippetLambda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CodeSnippetLambda.model.SnippetDownvotes;
import com.example.CodeSnippetLambda.model.SnippetDownvotes.SnippetDownvotesId;

@Repository
public interface SnippetDownvotesRepository extends JpaRepository<SnippetDownvotes, SnippetDownvotesId> {
    List<SnippetDownvotes> findByUserEmail(String userID);
    List<SnippetDownvotes> findBySnippetSnippetID(Integer snippetID);
	Long countBySnippetSnippetID(Integer snippetID);
}