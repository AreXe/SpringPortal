package com.arexe.bgames.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "favourite")
@Data
@Builder
@AllArgsConstructor
public class Favourite implements Serializable {

    private static final long serialVersionUID = 1534936714451738712L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_game_id")
    private BoardGame boardGame;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Favourite() {
        //for JPA
    }
}