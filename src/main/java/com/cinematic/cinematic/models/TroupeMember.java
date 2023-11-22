package com.cinematic.cinematic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "troupe_members")
public class TroupeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "biography")
    private String biography;

    @Column(name = "role")
    private String role;

    @Column(name = "fun_facts")
    private String funFacts;
}
