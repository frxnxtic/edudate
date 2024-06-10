package umb.khh.edudate.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user_likes")
public class UserLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "liker_user_id")
    private Long likerUserId;

    @Column(name = "liked_user_id")
    private Long likedUserId;


}
