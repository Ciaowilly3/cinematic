package com.cinematic.cinematic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Table(name = "troupe_reviews")
public class ReviewTroupe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewTroupeId;

    @Column(name = "review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "troupe_member_id")
    private TroupeMember troupeMember;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
