package com.arexe.bgames.soap;

import com.arexe.bgames.service.BoardGameService;
import com.arexe.bgames.soap.boardgame.BoardGame;
import com.arexe.bgames.soap.boardgame.BoardGameTransformer;
import com.arexe.bgames.soap.boardgame.GetBoardGameByIdRequest;
import com.arexe.bgames.soap.boardgame.GetBoardGameByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BoardGameEndpoint {

    private static final String NAMESPACE_URI = "https://senetbg.herokuapp.com/soap/schema";
    private final BoardGameService boardGameService;
    private final BoardGameTransformer boardGameTransformer;

    @Autowired
    public BoardGameEndpoint(BoardGameService boardGameService, BoardGameTransformer boardGameTransformer) {
        this.boardGameService = boardGameService;
        this.boardGameTransformer = boardGameTransformer;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBoardGameByIdRequest")
    @ResponsePayload
    public GetBoardGameByIdResponse getBoardGamesByTitleResponse(@RequestPayload GetBoardGameByIdRequest request) {
        final com.arexe.bgames.entity.BoardGame boardGameById = boardGameService.findBoardGameById(request.getId());
        final BoardGame boardGame = boardGameTransformer.convert(boardGameById);
        GetBoardGameByIdResponse response = new GetBoardGameByIdResponse();
        response.setBoardGame(boardGame);
        return response;
    }

}
