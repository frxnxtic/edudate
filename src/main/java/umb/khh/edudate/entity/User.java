package umb.khh.edudate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umb.khh.edudate.entity.enums.Interest;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    private Date dateOfBirth;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "city")
    private String city;
    @Column(name = "likes")
    private short like;
    @Column(name = "faculta")
    private String faculty;
    @Column(name = "description")
    private String profileDescription;
    @Column(name = "social_links")
    private String social_links;
//ДОБАВИТЬ КОЛОНКУ ДИЗЛАЙКОВ
    @Column(name = "dislikes")
    private int dislikes;


    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "interests")
    private List<Interest> interests = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Image> images;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matching> matchings = new HashSet<>();

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
}




