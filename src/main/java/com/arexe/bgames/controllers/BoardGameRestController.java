package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Board Games RESTful service
 *
 * @author arexe
 */
@RestController
@RequestScope
@RequestMapping("/api")
public class BoardGameRestController {

    private final BoardGameService boardGameService;

    @Autowired
    public BoardGameRestController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    /**
     * List of board games
     * Usage: GET /api/boardgames
     * @return board games list as JSON
     */
    @GetMapping("/boardgames")
    public ResponseEntity<List<BoardGame>> getAllBoardGames() {
        return new ResponseEntity<>(boardGameService.findAllBoardGames(), HttpStatus.OK);
    }

    /**
     * Get board game by ID
     * Usage: GET /api/boardgames/{id}
     * @param id board game ID
     * @return board game as JSON
     */
    @GetMapping("/boardgames/id/{id}")
    public ResponseEntity<BoardGame> getBoardGameById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(boardGameService.findBoardGameById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get board games list by title
     * Usage: GET /api/boardgames/{title}
     * @param title board game title as String
     * @return board games list as JSON
     */
    @GetMapping("/boardgames/{title}")
    public ResponseEntity<List<BoardGame>> getBoardGameByTitle(@PathVariable String title) {
        List<BoardGame> boardGames = boardGameService.findBoardGamesByTitle(title);
        if (boardGames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boardGames, HttpStatus.OK);
    }
}
