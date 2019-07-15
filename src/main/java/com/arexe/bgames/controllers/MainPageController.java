package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainPageController {

    private final BoardGameService boardGameService;

    @Autowired
    public MainPageController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping(value = {"/", "/index"})
    public String showMainPage(Model model) {
        List<BoardGame> boardGameList = boardGameService.findAllBoardGames();
        model.addAttribute("boardGameList", boardGameList);
        return "index";
    }

    @GetMapping(value = "/boardgame/search/{title}")
    public String searchBoardGames(Model model, @PathVariable("title") String title) {
        List<BoardGame> boardGamesList = boardGameService.findBoardGamesByTitle(title);
        model.addAttribute("boardGameList", boardGamesList);
        return "index";
    }
}
