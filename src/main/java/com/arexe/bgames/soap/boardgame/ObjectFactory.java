package com.arexe.bgames.soap.boardgame;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the hcom.arexe.bgames.soap.boardgame package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.arexe.bgames.soap.boardgame
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBoardGameByIdResponse }
     */
    public GetBoardGameByIdResponse createGetBoardGameByIdResponse() {
        return new GetBoardGameByIdResponse();
    }

    /**
     * Create an instance of {@link BoardGame }
     */
    public BoardGame createBoardGame() {
        return new BoardGame();
    }

    /**
     * Create an instance of {@link GetBoardGameByIdRequest }
     */
    public GetBoardGameByIdRequest createGetBoardGameByIdRequest() {
        return new GetBoardGameByIdRequest();
    }

}
