package com.arexe.bgames.soap.boardgame;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for boardGame complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="boardGame">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="designer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="releaseYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boardGame", propOrder = {
        "id",
        "title",
        "designer",
        "releaseYear",
        "description"
})
public class BoardGame {

    protected int id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String designer;
    protected int releaseYear;
    @XmlElement(required = true)
    protected String description;

    /**
     * Gets the value of the id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the designer property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDesigner() {
        return designer;
    }

    /**
     * Sets the value of the designer property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDesigner(String value) {
        this.designer = value;
    }

    /**
     * Gets the value of the releaseYear property.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the value of the releaseYear property.
     */
    public void setReleaseYear(int value) {
        this.releaseYear = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
