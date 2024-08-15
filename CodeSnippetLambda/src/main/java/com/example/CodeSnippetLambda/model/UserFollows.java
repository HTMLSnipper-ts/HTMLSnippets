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
@Table(name = "UserFollows")
public class UserFollows implements Serializable {

    @EmbeddedId
    private UserFollowsId id;

    @ManyToOne
    @MapsId("followerID")
    @JoinColumn(name = "Follower_ID")
    private User follower;

    @ManyToOne
    @MapsId("followingID")
    @JoinColumn(name = "Following_ID")
    private User following;

    // Constructors, Getters, and Setters

    public UserFollows() {}

    public UserFollows(User follower, User following) {
        this.follower = follower;
        this.following = following;
        this.id = new UserFollowsId(follower.getEmail(), following.getEmail());
    }

    public UserFollowsId getId() {
        return id;
    }

    public void setId(UserFollowsId id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    @Embeddable
    public static class UserFollowsId implements Serializable {
        private String followerID;
        private String followingID;

        public UserFollowsId() {}

        public UserFollowsId(String followerID, String followingID) {
            this.followerID = followerID;
            this.followingID = followingID;
        }

        public String getFollowerID() {
            return followerID;
        }

        public void setFollowerID(String followerID) {
            this.followerID = followerID;
        }

        public String getFollowingID() {
            return followingID;
        }

        public void setFollowingID(String followingID) {
            this.followingID = followingID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserFollowsId that = (UserFollowsId) o;
            return Objects.equals(followerID, that.followerID) &&
                   Objects.equals(followingID, that.followingID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(followerID, followingID);
        }
    }
}