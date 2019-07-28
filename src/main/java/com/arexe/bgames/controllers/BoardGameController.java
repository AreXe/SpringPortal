package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardGameController {

    private final BoardGameService boardGameService;

    @Autowired
    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping(value = "/admin/boardgames")
    @Secured(value = {"ROLE_ADMIN"})
    public String showAllBoardGames(Model model) {
        List<BoardGame> boardGamesList = boardGameService.findAllBoardGames();
        model.addAttribute("bgList", boardGamesList);
        return "admin/boardgames";
    }

    @GetMapping(value = "/admin/boardgames/search/{title}")
    @Secured(value = {"ROLE_ADMIN"})
    public String searchBoardGames(Model model, @PathVariable("title") String title) {
        List<BoardGame> boardGamesList = boardGameService.findBoardGamesByTitle(title);
        model.addAttribute("bgList", boardGamesList);
        return "admin/boardgames";
    }

    @GetMapping(value = "/admin/addboardgame")
    @Secured(value = {"ROLE_ADMIN"})
    public String showBoardGameForm() {
        return "admin/addboardgame";
    }

    @PostMapping(value = "/admin/addnewboardgame")
    @Secured(value = {"ROLE_ADMIN"})
    public String addBoardGame(Model model, BoardGame boardGame) {
        boardGameService.saveBoardGame(boardGame);
        return "admin/addboardgame";
    }

    @GetMapping(value = "/admin/boardgames/edit/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String showBoardGameEditForm(Model model, @PathVariable("id") int id) {
        BoardGame boardGameById = boardGameService.findBoardGameById(id);
        model.addAttribute("boardGame", boardGameById);
        return "admin/editboardgame";
    }

    @PutMapping(value = "/admin/updateboardgame/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String updateBoardGameFromEditForm(BoardGame boardGame, @PathVariable("id") int id) {
        boardGameService.updateBoardGame(id, boardGame);
        return "redirect:/admin/boardgames";
    }

    @DeleteMapping(value = "/admin/deleteboardgame/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String deleteBoardGame(@PathVariable("id") int id){
        boardGameService.deleteBoardGameById(id);
        return "redirect:/admin/boardgames";
    }
}
