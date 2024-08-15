package com.example.CodeSnippetLambda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Snippet")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Snippet_ID")
    private int snippetID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "UserID")
    private String userID;

    @Column(name = "HTMLCode", columnDefinition = "TEXT")
    private String htmlCode;

    @Column(name = "CSSCode", columnDefinition = "TEXT")
    private String cssCode;

    @Column(name = "JSCode", columnDefinition = "TEXT")
    private String jsCode;

    // Constructors, Getters, and Setters

    public Snippet() {}

    public Snippet(String title, String description, String userID, String htmlCode, String cssCode, String jsCode) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.htmlCode = htmlCode;
        this.cssCode = cssCode;
        this.jsCode = jsCode;
    }

    public int getSnippetID() {
        return snippetID;
    }

    public void setSnippetID(int snippetID) {
        this.snippetID = snippetID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public String getCssCode() {
        return cssCode;
    }

    public void setCssCode(String cssCode) {
        this.cssCode = cssCode;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }
}