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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class MainPageController {

    private final UserService userService;
    private final BoardGameService boardGameService;
    private final FavouriteService favouriteService;

    @Autowired
    public MainPageController(UserService userService, BoardGameService boardGameService, FavouriteService favouriteService) {
        this.userService = userService;
        this.boardGameService = boardGameService;
        this.favouriteService = favouriteService;
    }

    @GetMapping(value = {"/", "/index"})
    public String showMainPage(Model model) {
        List<BoardGame> boardGameList = boardGameService.findAllBoardGames();
        User user = getLoggedUser();
        if (user == null) {
            model.addAttribute("boardGameList", boardGameList);
        } else {
            List<BoardGame> boardGameLikedList = favouriteService.getFavouritesByUser(user).stream().map(Favourite::getBoardGame).collect(Collectors.toList());
            List<BoardGame> boardGameOthersList = boardGameList.stream().filter(i -> !boardGameLikedList.contains(i)).collect(Collectors.toList());
            model.addAttribute("boardGameLikedList", boardGameLikedList);
            model.addAttribute("boardGameOthersList", boardGameOthersList);
        }
        return "index";
    }

    @GetMapping(value = "/boardgame/search/{title}")
    public String searchBoardGames(Model model, @PathVariable("title") String title) {
        List<BoardGame> boardGamesList = boardGameService.findBoardGamesByTitle(title);
        User user = getLoggedUser();
        if (user == null) {
            model.addAttribute("boardGameList", boardGamesList);
        } else {
            List<BoardGame> favByUserAndTitle = favouriteService.getFavouritesByUserAndBoardGameTitle(user, title).stream().map(Favourite::getBoardGame).collect(Collectors.toList());
            List<BoardGame> boardGameOthersList = boardGamesList.stream().filter(i -> !favByUserAndTitle.contains(i)).collect(Collectors.toList());
            model.addAttribute("boardGameLikedList", favByUserAndTitle);
            model.addAttribute("boardGameOthersList", boardGameOthersList);
        }
        return "index";
    }

    @GetMapping(value = "/boardgame/{id}")
    public String boardGameDetails(Model model, @PathVariable("id") int id) {
        BoardGame boardGame = boardGameService.findBoardGameById(id);
        boolean isInUserFavList = favouriteService.getFavouritesByUser(getLoggedUser()).stream().map(Favourite::getBoardGame).anyMatch(Predicate.isEqual(boardGame));
        if (isInUserFavList) {
            model.addAttribute("inFavouriteList", true);
        }
        model.addAttribute("boardGame", boardGame);
        return "boardgame";
    }

    private User getLoggedUser() {
        String loggedUser = UserUtils.getLoggedUser();
        return userService.findUserByEmail(loggedUser);
    }
}
