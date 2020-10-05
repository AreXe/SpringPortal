package com.arexe.bgames.soap.boardgame;

import org.springframework.stereotype.Component;

@Component
public class BoardGameTransformer {

    public BoardGame convert(com.arexe.bgames.entity.BoardGame boardGameEntity){
        BoardGame boardGame = new BoardGame();
        boardGame.setId(boardGameEntity.getId());
        boardGame.setTitle(boardGameEntity.getTitle());
        boardGame.setDesigner(boardGameEntity.getDesigner());
        boardGame.setReleaseYear(boardGameEntity.getReleaseYear());
        boardGame.setDescription(boardGameEntity.getDescription());
        return boardGame;
    }

}
