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
@Table(name = "UserFavorites")
public class UserFavorites implements Serializable {

    @EmbeddedId
    private UserFavoritesId id;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "User_ID")
    private User user;

    @ManyToOne
    @MapsId("snippetID")
    @JoinColumn(name = "Snippet_ID")
    private Snippet snippet;

    // Constructors, Getters, and Setters

    public UserFavorites() {}

    public UserFavorites(User user, Snippet snippet) {
        this.user = user;
        this.snippet = snippet;
        this.id = new UserFavoritesId(user.getEmail(), snippet.getSnippetID());
    }

    public UserFavoritesId getId() {
        return id;
    }

    public void setId(UserFavoritesId id) {
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
    public static class UserFavoritesId implements Serializable {
        private String userID;
        private Integer snippetID;

        public UserFavoritesId() {}

        public UserFavoritesId(String userID, Integer snippetID) {
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
            UserFavoritesId that = (UserFavoritesId) o;
            return Objects.equals(userID, that.userID) &&
                   Objects.equals(snippetID, that.snippetID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userID, snippetID);
        }
    }
}