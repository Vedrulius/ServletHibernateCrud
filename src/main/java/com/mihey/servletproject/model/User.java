package com.mihey.servletproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User() {
    }

    public User(String firstName, String lastName, Region region, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts=" + posts +
                ", region=" + region +
                ", role=" + role +
                '}';
    }
}
