package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardGamesController {

    private final BoardGameService boardGameService;

    @Autowired
    public BoardGamesController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping(value = "/admin/boardgames")
    @Secured(value = {"ROLE_ADMIN"})
    public String showAllBoardGames(Model model){
        List<BoardGame> boardGamesList = boardGameService.findAllBoardGames();
        model.addAttribute("bgList", boardGamesList);
        return "admin/boardgames";
    }

    @GetMapping(value = "/admin/boardgames/search/{title}")
    @Secured(value = {"ROLE_ADMIN"})
    public String searchBoardGames(Model model, @PathVariable("title") String title){
        List<BoardGame> boardGamesList = boardGameService.findBoardGamesByTitle(title);
        model.addAttribute("bgList", boardGamesList);
        return "admin/boardgames";
    }

    @GetMapping(value = "/admin/addboardgame")
    @Secured(value = {"ROLE_ADMIN"})
    public String showBoardGameForm(){
        return "admin/addboardgame";
    }

    @PostMapping(value = "/admin/addnewboardgame")
    @Secured(value = {"ROLE_ADMIN"})
    public String addBoardGame(Model model, BoardGame boardGame){
        boardGameService.saveBoardGame(boardGame);
        return "admin/addboardgame";
    }
}
