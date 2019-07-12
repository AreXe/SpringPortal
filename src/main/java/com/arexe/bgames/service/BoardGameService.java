package com.arexe.bgames.service;

import com.arexe.bgames.entity.BoardGame;

import java.util.List;

public interface BoardGameService {

    List<BoardGame> findAllBoardGames();
    List<BoardGame> findBoardGamesByTitle(String title);
    BoardGame findBoardGameById(Integer id);
    void saveBoardGame(BoardGame boardGame);
    void deleteBoardGameById(int id);
}
