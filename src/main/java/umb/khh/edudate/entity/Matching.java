package umb.khh.edudate.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Time;
@Data
@Entity
@Table(name = "matching")
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "matched_user_id")
    private Long matchedUserId;

    @Column(name = "matched_at")
    private Time matchedAt;

    // Getters and setters
}
