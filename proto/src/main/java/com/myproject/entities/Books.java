package com.myproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "author")
    private String author;

    @NotNull(message = "Published date must not be null")
    @Column(name = "published")
    @Temporal(TemporalType.DATE)
    private Date published;

    public Books() {
    }

    public Books(Integer id, String title, String author, Date published) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }
}
