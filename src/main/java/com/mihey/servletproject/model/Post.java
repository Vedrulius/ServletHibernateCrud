package com.mihey.servletproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "created")
    private Timestamp created;
    @Column(name = "updated")
    private Timestamp updated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.created = new Timestamp(System.currentTimeMillis());
        this.updated = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

