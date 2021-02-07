package com.example.jpacrud.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.*;

@Entity(name = "note_table")
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", insertable = false, columnDefinition = "BIGINT")
    private long id;

    @Column(name = "note_title", nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(name = "created_at", nullable = false, columnDefinition = "BIGINT")
    private long createdAt;

    @Column(name = "note_text", nullable = false, columnDefinition = "TEXT")
    private String text;

    public Note() {
    }

    public Note(String title, long createdAt, String text) {
        this.title = title;
        this.createdAt = createdAt;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
