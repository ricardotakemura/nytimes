package br.org.venturus.ricardotakemura.nytimes.parse;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;

/**
 * JsonParser para Picture (json do "buscas")
 * @author ricardotakemura
 */
public class PictureSearchJsonParser extends JsonParser<Picture> {

    private final String TYPE_KEY = "type";
    private final String SUBTYPE_KEY = "subtype";
    private final String CAPTION_KEY = "caption";
    private final String URL_VALUE = "url";
    private final String DOMAIN_URL = "https://www.nytimes.com/";
    private final String IMAGE_VALUE = "image";
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
    protected Picture parse(final JSONObject jsonObject) throws JsonException {
        if (IMAGE_VALUE.equals(getValue(jsonObject, TYPE_KEY, String.class))) {
            final Picture picture = new Picture();
            picture.setCaption(getValue(jsonObject, CAPTION_KEY, String.class));
            picture.setWidth(getValue(jsonObject, WIDTH_KEY, Integer.class));
            picture.setHeight(getValue(jsonObject, HEIGHT_KEY, Integer.class));
            picture.setFormat(getValue(jsonObject, SUBTYPE_KEY, Picture.PictureFormat.class));
            final String path = getValue(jsonObject, URL_VALUE, String.class);
            try {
                if ((path != null) && !path.isEmpty()) {
                    final URL url = new URL(DOMAIN_URL + path);
                    picture.setUrl(url);
                }
            } catch (MalformedURLException e) {
                throw new JsonException(e.getMessage(), e);
            }
            return picture;
        }
        return null;
    }
}
