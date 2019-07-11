package com.arexe.bgames.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "board_game")
@Data
@Builder
@AllArgsConstructor
public class BoardGame implements Serializable {

    private static final long serialVersionUID = -580757404426474546L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_game_id")
    private Integer id;

    @NotNull
    private String title;

    private String designer;

    private Integer releaseYear;

    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    private String players;

    @Column(name = "playing_time")
    private String playingTime;

    @Column(name = "min_age")
    private Integer minAge;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "board_game_category", joinColumns = @JoinColumn(name = "board_game_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public BoardGame() {
        //for JPA
    }
}
