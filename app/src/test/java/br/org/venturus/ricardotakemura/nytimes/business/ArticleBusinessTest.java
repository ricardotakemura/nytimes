package br.org.venturus.ricardotakemura.nytimes.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.business.impl.ArticleJsonBusiness;
import br.org.venturus.ricardotakemura.nytimes.mocks.JsonDataProvideMocks;
import br.org.venturus.ricardotakemura.nytimes.model.Article;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;
import br.org.venturus.ricardotakemura.nytimes.util.DateUtil;
import br.org.venturus.ricardotakemura.nytimes.util.URLUtil;


@RunWith(PowerMockRunner.class)
@PrepareForTest(URLUtil.class)
public class ArticleBusinessTest {

    @Test
    public void getMostViews_isCorrect() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final JsonDataProvideMocks mocks = new JsonDataProvideMocks();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenReturn(mocks.JSON_MOST_POPULAR_OK);
            final ArticleBusiness articleBusiness = new ArticleJsonBusiness();
            final List<Article> articleList = articleBusiness.getMostViews();
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
    public void getMostViews_isFail() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final JsonDataProvideMocks mocks = new JsonDataProvideMocks();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenReturn(mocks.JSON_SEARCH_OK);
            final ArticleBusiness articleBusiness = new ArticleJsonBusiness();
            final List<Article> articleList = articleBusiness.getMostViews();
            Assert.assertTrue(articleList.isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void search_isCorrect() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final JsonDataProvideMocks mocks = new JsonDataProvideMocks();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenReturn(mocks.JSON_SEARCH_OK);
            final ArticleBusiness articleBusiness = new ArticleJsonBusiness();
            final List<Article> articleList = articleBusiness.search("teste", 1);
            Assert.assertFalse(articleList.isEmpty());
            Assert.assertEquals(articleList.size(), 1);
            final Article article = articleList.get(0);
            Assert.assertEquals(article.getTitle(),"Offshore Oil Block Auction Nets Brazil More Than $2 Billion");
            Assert.assertEquals(article.getPublishedDate(), DateUtil.dateFromString("2018-03-29"));
            Assert.assertNotNull(article.getAbstractText());
            Assert.assertNotNull(article.getPicture());
            final Picture picture = article.getPicture();
            Assert.assertNull(picture.getCaption());
            Assert.assertEquals(picture.getFormat(), Picture.PictureFormat.large);
            Assert.assertEquals(picture.getUrl(), new URL("https://www.nytimes.com/images/2018/03/31/us/31census/merlin_136086270_649ff75b-c065-4992-b084-9709831b40fc-articleLarge.jpg"));
            Assert.assertEquals(picture.getHeight().intValue(),481);
            Assert.assertEquals(picture.getWidth().intValue(), 600);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void search_isFail() {
        try {
            PowerMockito.mockStatic(URLUtil.class);
            final JsonDataProvideMocks mocks = new JsonDataProvideMocks();
            PowerMockito.when(URLUtil.readFully(Mockito.any(URL.class))).thenReturn(mocks.JSON_MOST_POPULAR_OK);
            final ArticleBusiness articleBusiness = new ArticleJsonBusiness();
            final List<Article> articleList = articleBusiness.search("teste", 1);
            Assert.assertTrue(articleList.isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
