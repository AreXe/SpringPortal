package com.arexe.bgames.soap.boardgame;

import javax.xml.bind.annotation.*;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boardGame" type="{https://senetbg.herokuapp.com/soap/schema}boardGame"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "boardGame"
})
@XmlRootElement(name = "getBoardGameByIdResponse")
public class GetBoardGameByIdResponse {

    @XmlElement(required = true)
    protected BoardGame boardGame;

    /**
     * Gets the value of the boardGame property.
     *
     * @return possible object is
     * {@link BoardGame }
     */
    public BoardGame getBoardGame() {
        return boardGame;
    }

    /**
     * Sets the value of the boardGame property.
     *
     * @param value allowed object is
     *              {@link BoardGame }
     */
    public void setBoardGame(BoardGame value) {
        this.boardGame = value;
    }

}
