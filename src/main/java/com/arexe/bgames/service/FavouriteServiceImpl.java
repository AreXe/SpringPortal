package com.arexe.bgames.service;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.entity.Favourite;
import com.arexe.bgames.entity.User;
import com.arexe.bgames.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("favouriteService")
@Transactional
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;

    @Autowired
    public FavouriteServiceImpl(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void saveFavourite(Favourite favourite) {
        favouriteRepository.save(favourite);
    }

    @Override
    public List<Favourite> getFavouritesByUser(User user) {
        return favouriteRepository.findAllByUser(user);
    }

    @Override
    public List<Favourite> getFavouritesByBoardGame(BoardGame boardGame) {
        return favouriteRepository.findAllByBoardGame(boardGame);
    }

    @Override
    public List<Favourite> getFavouritesByUserAndBoardGameTitle(User user, String title) {
        return favouriteRepository.findAllByUserAndBoardGame_TitleContaining(user, title);
    }

    @Override
    public void deleteFavouriteByUserAndBoardGame(User user, BoardGame boardGame) {
        favouriteRepository.deleteFavouriteByUserAndBoardGame(user, boardGame);
    }
}
