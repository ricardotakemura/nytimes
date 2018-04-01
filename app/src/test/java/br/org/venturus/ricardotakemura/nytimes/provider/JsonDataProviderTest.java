package br.org.venturus.ricardotakemura.nytimes.provider;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.ServerException;
import br.org.venturus.ricardotakemura.nytimes.mocks.JsonDataProvideMocks;
import br.org.venturus.ricardotakemura.nytimes.model.Article;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;
import br.org.venturus.ricardotakemura.nytimes.parse.ArticleMostViewJsonParser;
import br.org.venturus.ricardotakemura.nytimes.util.DateUtil;
import br.org.venturus.ricardotakemura.nytimes.util.URLUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(URLUtil.class)
public class JsonDataProviderTest {

    @Test
    public void getList_isCorrect() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final JsonDataProvideMocks mocks = new JsonDataProvideMocks();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenReturn(mocks.JSON_MOST_POPULAR_OK);
            final ArticleMostViewJsonParser articleMostViewJsonParser = new ArticleMostViewJsonParser();
            final List<Article> articleList = JsonDataProvider.getList(articleMostViewJsonParser, "/mostpopular/v2/mostviewed/all-sections/1.json");
            Assert.assertFalse(articleList.isEmpty());
            Assert.assertEquals(articleList.size(), 1);
            final Article article = articleList.get(0);
            Assert.assertEquals(article.getTitle(),"I Tried to Befriend Nikolas Cruz. He Still Killed My Friends.");
            Assert.assertEquals(article.getPublishedDate(), DateUtil.dateFromString("2018-03-27"));
            Assert.assertNotNull(article.getAbstractText());
            Assert.assertNotNull(article.getPicture());
            final Picture picture = article.getPicture();
            Assert.assertEquals(picture.getCaption(),"Nikolas Cruz on his Instagram account.");
            Assert.assertEquals(picture.getFormat(), Picture.PictureFormat.square320);
            Assert.assertEquals(picture.getUrl(), new URL("https://static01.nyt.com/images/2018/03/27/opinion/27Robinson/merlin_133877490_ffcb086d-bc7f-46a9-a088-d15c4a3c1ff8-square320.jpg"));
            Assert.assertEquals(picture.getHeight().intValue(),320);
            Assert.assertEquals(picture.getWidth().intValue(), 320);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getList_parseError() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final JsonDataProvideMocks mocks = new JsonDataProvideMocks();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenReturn(mocks.JSON_INVALID_CREDENTIAL);
            final ArticleMostViewJsonParser articleMostViewJsonParser = new ArticleMostViewJsonParser();
            final List<Article> articleList = JsonDataProvider.getList(articleMostViewJsonParser, "/mostpopular/v2/mostviewed/all-sections/1.json");
            Assert.assertEquals(articleList.size(), 0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getList_isInvalidCredential() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final ArticleMostViewJsonParser articleMostViewJsonParser = new ArticleMostViewJsonParser();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenThrow(new ServerException(HttpURLConnection.HTTP_FORBIDDEN));
            JsonDataProvider.getList(articleMostViewJsonParser, "/mostpopular/v2/mostviewed/all-sections/1.json");
            Assert.fail("Invalid state");
        } catch (ServerException e) {
            Assert.assertEquals(e.getStatusServer(), HttpURLConnection.HTTP_FORBIDDEN);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getList_isInvalidURL() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final ArticleMostViewJsonParser articleMostViewJsonParser = new ArticleMostViewJsonParser();
            PowerMockito.when(URLUtil.createURL(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap())).thenThrow(new MalformedURLException());
            JsonDataProvider.getList(articleMostViewJsonParser, "/mostpopular/v2/mostviewed/all-sections/1.json");
            Assert.fail("Invalid state");
        } catch (MalformedURLException e) {
            Assert.assertNotNull(e);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
