package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.entity.Favourite;
import com.arexe.bgames.entity.User;
import com.arexe.bgames.service.BoardGameService;
import com.arexe.bgames.service.FavouriteService;
import com.arexe.bgames.service.UserService;
import com.arexe.bgames.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class FavouriteController {

    private final UserService userService;
    private final BoardGameService boardGameService;
    private final FavouriteService favouriteService;

    @Autowired
    public FavouriteController(UserService userService, BoardGameService boardGameService, FavouriteService favouriteService) {
        this.userService = userService;
        this.boardGameService = boardGameService;
        this.favouriteService = favouriteService;
    }

    @PostMapping(value = "/addfav/{id}")
    public String addBoardGameToFavourites(@PathVariable("id") int id) {
        User user = getLoggedUser();
        BoardGame boardGame = boardGameService.findBoardGameById(id);

        Favourite favGame = Favourite.builder()
                .user(user)
                .boardGame(boardGame)
                .creationDate(LocalDateTime.now())
                .build();

        boolean checkIfUserFavContainGame = favouriteService.getFavouritesByUser(user).stream().map(Favourite::getBoardGame).anyMatch(boardGame::equals);
        if (checkIfUserFavContainGame) {
            favouriteService.deleteFavouriteByUserAndBoardGame(user, boardGame);
        } else {
            favouriteService.saveFavourite(favGame);
        }
        return "redirect:/index";
    }

    private User getLoggedUser() {
        String loggedUser = UserUtils.getLoggedUser();
        return userService.findUserByEmail(loggedUser);
    }

}
