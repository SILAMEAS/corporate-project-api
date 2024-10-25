package com.corporate.project.laza_api.model.entity;

import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "react")
@Entity
@Builder
@ToString
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name")
    private String name;

    // One react type can be used in many reactions
    @OneToMany(mappedBy = "react", cascade = CascadeType.ALL)
    private List<Reaction> reactions;

}
