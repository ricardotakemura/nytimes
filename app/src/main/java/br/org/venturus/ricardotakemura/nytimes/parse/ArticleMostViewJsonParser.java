package br.org.venturus.ricardotakemura.nytimes.parse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;

/**
 * JsonParser para Article (json do "mais vistos")
 * @author ricardotakemura
 */
public class ArticleMostViewJsonParser extends JsonParser<Article> {

    private final String TITLE_KEY = "title";
    private final String PUBLISHED_DATE_KEY = "published_date";
    private final String ABSTRACT_KEY = "abstract";
    private final String MEDIA_KEY = "media";
    private final String TYPE_KEY = "type";
    private final String MEDIA_METADATA_KEY = "media-metadata";
    private final String CAPTION_KEY = "caption";
    private final String IMAGE_VALUE = "image";
    private final String RESULTS_FIELD = "results";

    /**
     * @see JsonParser#parse(JSONObject)
     */
    @Override
    protected Article parse(final JSONObject object) throws JsonException {
        final Article article = new Article();
        article.setTitle(getValue(object, TITLE_KEY, String.class));
        article.setPublishedDate(getValue(object, PUBLISHED_DATE_KEY, Date.class));
        article.setAbstractText(getValue(object, ABSTRACT_KEY, String.class));
        final JSONArray medias = getValue(object, MEDIA_KEY, JSONArray.class);
        if (medias != null) {
            for (int i = 0; i < medias.length(); i++) {
                final JSONObject media = getValue(medias, i);
                if (!media.isNull(MEDIA_METADATA_KEY)
                        && IMAGE_VALUE.equals(getValue(media, TYPE_KEY, String.class))) {
                    final String caption = getValue(media, CAPTION_KEY, String.class);
                    final JSONArray metaDatas = getValue(media, MEDIA_METADATA_KEY, JSONArray.class);
                    if (metaDatas != null) {
                        for (int j = 0; j < metaDatas.length(); j++) {
                            final JSONObject metaData = getValue(metaDatas, j);
                            final PictureMostViewJsonParser pictureMostViewJsonParser = new PictureMostViewJsonParser();
                            Picture picture = pictureMostViewJsonParser.parse(metaData);
                            if (picture != null && (article.getPicture() == null ||
                                    picture.getWidth() > article.getPicture().getWidth())) {
                                picture.setCaption(caption);
                                article.setPicture(picture);
                            }
                        }
                    }
                }
            }
        }
        return article;
    }

    /**
     * @see JsonParser#parseAsList(JSONObject)
     */
    @Override
    protected List<Article> parseAsList(final JSONObject object) throws JsonException {
        List<Article> ret = new ArrayList<>();
        final JSONArray results = getValue(object, RESULTS_FIELD, JSONArray.class);
        if (results != null) {
            for (int i = 0; i < results.length(); i++) {
                final Article item = parse(getValue(results, i));
                ret.add(item);
            }
        }
        return ret;
    }
}
