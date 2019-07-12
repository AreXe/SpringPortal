package com.arexe.bgames.repository;

import com.arexe.bgames.entity.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Integer> {

    @Query(value = "SELECT * FROM board_game b WHERE b.title LIKE %:title%", nativeQuery = true)
    List<BoardGame> findBoardGamesByTitle(String title);
}
