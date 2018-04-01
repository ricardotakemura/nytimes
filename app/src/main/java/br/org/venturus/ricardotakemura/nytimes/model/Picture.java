package br.org.venturus.ricardotakemura.nytimes.model;


import android.support.annotation.Nullable;

import java.net.URL;

/**
 * Modelo para imagem
 * @author ricardotakemura
 */
public class Picture {

    /**
     * Tipos de imagem (tamanho)
     * @author ricardotakemura
     */
    public enum PictureFormat {
        square320,
        standardThumbnail,
        normal,
        large,
        jumbo,
        superJumbo,
        square640,
        largeThumbnail,
        mediumThreeByTwo210,
        mediumThreeByTwo440;

        /**
         * Transforma o valor de string para o tipo enumerado
         * @param string String
         * @return PictureFormat
         */
        @Nullable
        public static PictureFormat valueOfString(String string) {
            if (string != null) {
                switch (string.toLowerCase()) {
                    case "square320":
                        return square320;
                    case "thumbnail":
                    case "standard thumbnail":
                        return standardThumbnail;
                    case "normal":
                        return normal;
                    case "large":
                    case "xlarge":
                        return large;
                    case "jumbo":
                        return jumbo;
                    case "superjumbo":
                        return superJumbo;
                    case "square640":
                        return square640;
                    case "large thumbnail":
                    case "thumbLarge":
                        return largeThumbnail;
                    case "mediumthreebytwo210":
                        return mediumThreeByTwo210;
                    case "mediumthreebytwo440":
                        return mediumThreeByTwo440;
                }
            }
            return null;
        }
    }

    private URL url;
    private String caption;
    private Integer height;
    private Integer width;
    private PictureFormat format;

    /**
     * caption
     * @return String
     */
    public String getCaption() {
        return caption;
    }

    /**
     * caption
     * @param caption String
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * URL
     * @return URL
     */
    public URL getUrl() {
        return url;
    }

    /**
     * height
     * @return Integer
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * width
     * @return Integer
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * format
     * @return PictureFormat
     */
    public PictureFormat getFormat() {
        return format;
    }

    /**
     * URL
     * @param url URL
     */
    public void setUrl(final URL url) {
        this.url = url;
    }

    /**
     * height
     * @param height Integer
     */
    public void setHeight(final Integer height) {
        this.height = height;
    }

    /**
     * width
     * @param width Integer
     */
    public void setWidth(final Integer width) {
        this.width = width;
    }

    /**
     * format
     * @param format PictureFormat
     */
    public void setFormat(final PictureFormat format) {
        this.format = format;
    }
}
