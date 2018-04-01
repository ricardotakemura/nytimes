package br.org.venturus.ricardotakemura.nytimes.provider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.exception.ServerException;
import br.org.venturus.ricardotakemura.nytimes.parse.JsonParser;
import br.org.venturus.ricardotakemura.nytimes.util.URLUtil;

public class JsonDataProvider {

    private static final String DOMAIN_URL = "https://api.nytimes.com/svc";
    private static final String API_KEY = "api-key";
    private static final String API_VALUE = "abf90e09be5245859416b960f6113218";

    public static <T> List<T> getList(JsonParser<T> parser, String path)
            throws ServerException, MalformedURLException, JsonException {
        return JsonDataProvider.getList(parser, path, null);
    }

    public static <T> List<T> getList(JsonParser<T> parser, String path, Map<String, String> params)
            throws ServerException, MalformedURLException, JsonException {
        final Map<String, String> values = new HashMap<>();
        values.put(API_KEY, API_VALUE);
        if (params != null) {
            values.putAll(params);
        }
        final URL url = URLUtil.createURL(DOMAIN_URL, path, values);
        return parser.transformAsList(URLUtil.readFully(url));
    }
}
