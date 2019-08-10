package com.arexe.bgames.service;

import com.arexe.bgames.entity.BoardGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardGameService {

    List<BoardGame> findAllBoardGames();
    Page<BoardGame> findAllBoardGamesPageable(Pageable pageable);
    List<BoardGame> findBoardGamesByTitle(String title);
    BoardGame findBoardGameById(Integer id);
    void saveBoardGame(BoardGame boardGame);
    void updateBoardGame(int id, BoardGame boardGame);
    void deleteBoardGameById(int id);
}
