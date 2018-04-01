package br.org.venturus.ricardotakemura.nytimes.model;


import android.support.annotation.Nullable;

import java.net.URL;

public class Picture {

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public URL getUrl() {
        return url;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public PictureFormat getFormat() {
        return format;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    public void setFormat(final PictureFormat format) {
        this.format = format;
    }
}
