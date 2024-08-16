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
@Table(name = "UserBlocks")
public class UserBlocks implements Serializable {

    @EmbeddedId
    private UserBlocksId id;

    @ManyToOne
    @MapsId("blockerID")
    @JoinColumn(name = "Blocker_ID")
    private User blocker;

    @ManyToOne
    @MapsId("blockedID")
    @JoinColumn(name = "Blocked_ID")
    private User blocked;

    // Constructors, Getters, and Setters

    public UserBlocks() {}

    public UserBlocks(User blocker, User blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
        this.id = new UserBlocksId(blocker.getEmail(), blocked.getEmail());
    }

    public UserBlocksId getId() {
        return id;
    }

    public void setId(UserBlocksId id) {
        this.id = id;
    }

    public User getBlocker() {
        return blocker;
    }

    public void setBlocker(User blocker) {
        this.blocker = blocker;
    }

    public User getBlocked() {
        return blocked;
    }

    public void setBlocked(User blocked) {
        this.blocked = blocked;
    }

    @Embeddable
    public static class UserBlocksId implements Serializable {
        private String blockerID;
        private String blockedID;

        public UserBlocksId() {}

        public UserBlocksId(String blockerID, String blockedID) {
            this.blockerID = blockerID;
            this.blockedID = blockedID;
        }

        public String getBlockerID() {
            return blockerID;
        }

        public void setBlockerID(String blockerID) {
            this.blockerID = blockerID;
        }

        public String getBlockedID() {
            return blockedID;
        }

        public void setBlockedID(String blockedID) {
            this.blockedID = blockedID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserBlocksId that = (UserBlocksId) o;
            return Objects.equals(blockerID, that.blockerID) &&
                   Objects.equals(blockedID, that.blockedID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(blockerID, blockedID);
        }
    }
}