package com.corporate.project.laza_api.model.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "reaction")
@Entity
@Builder
@ToString
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "react_id",nullable = false)
    private React react;

    @ManyToOne
    @JoinColumn(name = "user_owner_id",nullable = false)
    private AppUser userOwner;

    @ManyToOne
    @JoinColumn(name = "user_react_id",nullable = false)
    private AppUser userReact;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

}
