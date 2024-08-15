package com.example.CodeSnippetLambda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "[User]")
public class User {

    @Id
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "Pfp_Id")
    private String pfpId;

    // Constructors
    public User() {}

    public User(String email, String username, String pfpId) {
        this.email = email;
        this.username = username;
        this.pfpId = pfpId;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPfpId() {
        return pfpId;
    }

    public void setPfpId(String pfpId) {
        this.pfpId = pfpId;
    }
}