package com.arexe.bgames.service;

import com.arexe.bgames.entity.BoardGame;
import com.arexe.bgames.repository.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service("boardGameService")
@Transactional
public class BoardGameServiceImpl implements BoardGameService {

    private final BoardGameRepository boardGameRepository;

    @Autowired
    public BoardGameServiceImpl(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public List<BoardGame> findAllBoardGames() {
        return boardGameRepository.findAll();
    }

    @Override
    public Page<BoardGame> findAllBoardGamesPageable(Pageable pageable) {
        return boardGameRepository.findAll(pageable);
    }

    @Override
    public List<BoardGame> findBoardGamesByTitle(String title) {
        return boardGameRepository.findBoardGamesByTitle(title);
    }

    @Override
    public BoardGame findBoardGameById(Integer id) {
        return boardGameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveBoardGame(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
    }

    @Override
    public void updateBoardGame(int id, BoardGame boardGame) {
        boardGame.setId(id);
        saveBoardGame(boardGame);
    }

    @Override
    public void deleteBoardGameById(int id) {
        boardGameRepository.deleteById(id);
    }
}
