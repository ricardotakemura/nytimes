package br.org.venturus.ricardotakemura.nytimes.model;

import java.util.Date;

public class Article {

    private String title;
    private Date publishedDate;
    private String abstractText;
    private Picture picture;

    public String getTitle() {
        return title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setPublishedDate(final Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setAbstractText(final String abstractText) {
        this.abstractText = abstractText;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
