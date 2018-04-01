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
 * JsonParser para Article (json do "buscas")
 * @author ricardotakemura
 */
public class ArticleSearchJsonParser extends JsonParser<Article> {

    private final String RESPONSE_KEY = "response";
    private final String DOCS_KEY = "docs";
    private final String HEADLINE_KEY = "headline";
    private final String MAIN_KEY = "main";
    private final String PUBLISHED_DATE_KEY = "pub_date";
    private final String ABSTRACT_KEY = "abstract";
    private final String SNIPPET_KEY = "snippet";
    private final String MULTIMEDIA_KEY = "multimedia";

    /**
     * @see JsonParser#parseAsList(JSONObject)
     */
    @Override
    protected List<Article> parseAsList(final JSONObject object) throws JsonException {
        final List<Article> ret = new ArrayList<>();
        final JSONObject response = getValue(object, RESPONSE_KEY, JSONObject.class);
        if (response != null) {
            final JSONArray results = getValue(response, DOCS_KEY, JSONArray.class);
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    final Article item = parse(getValue(results, i));
                    ret.add(item);
                }
            }
        }
        return ret;
    }

    /**
     * @see JsonParser#parse(JSONObject)
     */
    @Override
    protected Article parse(final JSONObject object) throws JsonException {
        final Article article = new Article();
        String abstractText = getValue(object, ABSTRACT_KEY, String.class);
        if (abstractText == null) {
            abstractText = getValue(object, SNIPPET_KEY, String.class);
        }
        article.setAbstractText(abstractText);
        final JSONObject headLine = getValue(object, HEADLINE_KEY, JSONObject.class);
        if (headLine != null) {
            article.setTitle(getValue(headLine, MAIN_KEY, String.class));
        }
        article.setPublishedDate(getValue(object, PUBLISHED_DATE_KEY, Date.class));
        final JSONArray medias = getValue(object, MULTIMEDIA_KEY, JSONArray.class);
        if (medias != null) {
            for (int i = 0; i < medias.length(); i++) {
                final JSONObject media = getValue(medias, i);
                final PictureSearchJsonParser pictureSearchJsonParser = new PictureSearchJsonParser();
                final Picture picture = pictureSearchJsonParser.parse(media);
                if (picture != null && (article.getPicture() == null ||
                        picture.getWidth() > article.getPicture().getWidth())) {
                    article.setPicture(picture);
                }
            }
        }
        return article;
    }
}
