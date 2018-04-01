package br.org.venturus.ricardotakemura.nytimes.parse;

import org.json.JSONObject;

import java.net.URL;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;

/**
 * JsonParser para Picture (json do "mais vistos")
 * @author ricardotakemura
 */
public class PictureMostViewJsonParser extends JsonParser<Picture> {

    private final String URL_KEY = "url";
    private final String FORMAT_KEY = "format";
    private final String HEIGHT_KEY = "height";
    private final String WIDTH_KEY = "width";

    /**
     * @see JsonParser#parseAsList(JSONObject)
     */
    @Override
    protected List<Picture> parseAsList(JSONObject object) throws JsonException {
        return null;
    }

    /**
     * @see JsonParser#parse(JSONObject)
     */
    @Override
    protected Picture parse(final JSONObject object) throws JsonException {
        final Picture picture = new Picture();
        picture.setUrl(getValue(object, URL_KEY, URL.class));
        picture.setFormat(getValue(object, FORMAT_KEY, Picture.PictureFormat.class));
        picture.setHeight(getValue(object, HEIGHT_KEY, Integer.class));
        picture.setWidth(getValue(object, WIDTH_KEY, Integer.class));
        return picture;
    }
}
