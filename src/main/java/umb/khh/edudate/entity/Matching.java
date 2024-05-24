package umb.khh.edudate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matching")
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "matched_user_id", nullable = false)
    private User matchedUser;

    @Column(name = "seen")
    private boolean seen;

    @Column(name = "liked")
    private boolean liked;

    @Column(name = "matched_at")
    private Time matchedAt;
}
