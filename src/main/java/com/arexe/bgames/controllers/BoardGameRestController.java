package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.service.BoardGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Board Games RESTful service
 *
 * @author arexe
 */
@RestController
@RequestMapping("/api")
@Slf4j(topic = "bgames.logger")
public class BoardGameRestController {

    private final BoardGameService boardGameService;

    @Autowired
    public BoardGameRestController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    /**
     * List of board games
     * Usage: GET /api/boardgames
     *
     * @return board games list as JSON
     */
    @GetMapping("/boardgames")
    public ResponseEntity<List<BoardGame>> getAllBoardGames() {
        List<BoardGame> boardGameList = boardGameService.findAllBoardGames();
        log.info("[BG_REST] List of returned bgames: " + boardGameList.stream().map(BoardGame::getTitle).collect(Collectors.toList()));
        return new ResponseEntity<>(boardGameList, HttpStatus.OK);
    }

    /**
     * Get board game by ID
     * Usage: GET /api/boardgames/{id}
     *
     * @param id board game ID
     * @return board game as JSON
     */
    @GetMapping("/boardgames/id/{id}")
    public ResponseEntity<BoardGame> getBoardGameById(@PathVariable int id) {
        try {
            BoardGame boardGame = boardGameService.findBoardGameById(id);
            log.info("[BG_REST] Returned bgame: " + boardGame.getTitle());
            return new ResponseEntity<>(boardGame, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get board games list by title
     * Usage: GET /api/boardgames/{title}
     *
     * @param title board game title as String
     * @return board games list as JSON
     */
    @GetMapping("/boardgames/{title}")
    public ResponseEntity<List<BoardGame>> getBoardGameByTitle(@PathVariable String title) {
        List<BoardGame> boardGames = boardGameService.findBoardGamesByTitle(title);
        log.info("[BG_REST] List of returned bgames: " + boardGames.stream().map(BoardGame::getTitle).collect(Collectors.toList()));
        if (boardGames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boardGames, HttpStatus.OK);
    }

    /**
     * Add board game to database (for Admin only)
     *
     * @param boardGame JSON object
     * @return HTTP status 201 and created object
     * @throws URISyntaxException if created URI can't be handled
     */
    @PostMapping("/boardgames")
    public ResponseEntity<BoardGame> addBoardGame(@RequestBody BoardGame boardGame) throws URISyntaxException {
        boardGameService.saveBoardGame(boardGame);
        log.info("[BG_REST] Added new bgame: " + boardGame.getTitle());
        return ResponseEntity.created(new URI("boardgame" + boardGame.getId())).body(boardGame);
    }

    /**
     * Udpdate board game by ID in database (for Admin only)
     *
     * @param id        board game ID
     * @param boardGame JSON object
     * @return HTTP status 200 and updated object
     */
    @PutMapping("/boardgames/{id}")
    public ResponseEntity<BoardGame> updateBoardGame(@PathVariable int id, @RequestBody BoardGame boardGame) {
        if (boardGameService.findBoardGameById(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        boardGameService.updateBoardGame(id, boardGame);
        log.info("[BG_REST] Updated bgame: " + boardGame.toString());
        return ResponseEntity.ok().body(boardGame);
    }

    /**
     * Delete board game by ID from database (for Admin only)
     *
     * @param id board game ID
     * @return HTTP status 200
     */
    @DeleteMapping("/boardgames/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<?> deleteBoardGameById(@PathVariable int id) {
        if (boardGameService.findBoardGameById(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        log.info("[BG_REST] Deleting bgame: " + boardGameService.findBoardGameById(id).toString());
        boardGameService.deleteBoardGameById(id);
        return ResponseEntity.ok().build();
    }
}
