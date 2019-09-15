package com.arexe.bgames.service;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.entity.Favourite;
import com.arexe.bgames.entity.User;

import java.util.List;

public interface FavouriteService {

    void saveFavourite(Favourite favourite);
    List<Favourite> getFavouritesByUser(User user);
    List<Favourite> getFavouritesByBoardGame(BoardGame boardGame);
    List<Favourite> getFavouritesByUserAndBoardGameTitle(User user, String title);
    void deleteFavouriteByUserAndBoardGame(User user, BoardGame boardGame);
}
