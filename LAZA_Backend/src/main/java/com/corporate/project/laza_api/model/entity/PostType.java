package com.corporate.project.laza_api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "post_type")
@Entity
@Builder
@ToString
public class PostType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    @OneToMany(mappedBy = "postType", cascade = CascadeType.ALL)
    private List<Post> posts;

}
