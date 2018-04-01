package br.org.venturus.ricardotakemura.nytimes.model;

import java.util.Date;

/**
 * Modelo para artigo
 * @author ricardotakemura
 */
public class Article {

    private String title;
    private Date publishedDate;
    private String abstractText;
    private Picture picture;

    /**
     * title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * published date
     * @return Date
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

    /**
     * abstract text
     * @return String
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * picture
     * @return Picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * title
     * @param title String
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * published date
     * @param publishedDate Date
     */
    public void setPublishedDate(final Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * abstract text
     * @param abstractText String
     */
    public void setAbstractText(final String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * picture
     * @param picture Picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
