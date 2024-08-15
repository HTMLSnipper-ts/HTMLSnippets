package com.example.CodeSnippetLambda.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "SnippetDownvotes")
public class SnippetDownvotes implements Serializable {

    @EmbeddedId
    private SnippetDownvotesId id;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "User_ID")
    private User user;

    @ManyToOne
    @MapsId("snippetID")
    @JoinColumn(name = "Snippet_ID")
    private Snippet snippet;

    // Constructors, Getters, and Setters

    public SnippetDownvotes() {}

    public SnippetDownvotes(User user, Snippet snippet) {
        this.user = user;
        this.snippet = snippet;
        this.id = new SnippetDownvotesId(user.getEmail(), snippet.getSnippetID());
    }

    public SnippetDownvotesId getId() {
        return id;
    }

    public void setId(SnippetDownvotesId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Embeddable
    public static class SnippetDownvotesId implements Serializable {
        private String userID;
        private Integer snippetID;

        public SnippetDownvotesId() {}

        public SnippetDownvotesId(String userID, Integer snippetID) {
            this.userID = userID;
            this.snippetID = snippetID;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public Integer getSnippetID() {
            return snippetID;
        }

        public void setSnippetID(Integer snippetID) {
            this.snippetID = snippetID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SnippetDownvotesId that = (SnippetDownvotesId) o;
            return Objects.equals(userID, that.userID) &&
                   Objects.equals(snippetID, that.snippetID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userID, snippetID);
        }
    }
}