package umb.khh.edudate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

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
    private String passwordHash;
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

    //@Column(name = "interests")
    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    private Set<Interest> interests = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "liked_user_id"),
            inverseJoinColumns = @JoinColumn(name = "liker_user_id")
    )

    private Set<User> likedBy;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Image> images;
}



