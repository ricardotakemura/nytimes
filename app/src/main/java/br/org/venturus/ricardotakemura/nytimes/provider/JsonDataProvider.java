package br.org.venturus.ricardotakemura.nytimes.provider;

import android.util.Base64;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.exception.ServerException;
import br.org.venturus.ricardotakemura.nytimes.parse.JsonParser;
import br.org.venturus.ricardotakemura.nytimes.util.URLUtil;

/**
 * Classe que prove dados em formato de JSON
 * @author ricardotakemura
 */
public class JsonDataProvider {

    private static final String DOMAIN_URL = "https://api.nytimes.com/svc";
    private static final String API_KEY = "api-key";
    private static final String API_VALUE = "YWJmOTBlMDliZTUyNDU4NTk0MTZiOTYwZjYxMTMyMTg=";

    /**
     * Obtem uma lista de objetos parseados
     * @param parser adaptador de dados de json para objeto (JsonParser)
     * @param path caminho do serviço (String)
     * @param <T> tipo do objeto
     * @return lista de objetos (List)
     * @throws ServerException Exceção de servidor
     * @throws MalformedURLException Exceção de URL
     * @throws JsonException Exceção de JSON
     */
    public static <T> List<T> getList(JsonParser<T> parser, String path)
            throws ServerException, MalformedURLException, JsonException {
        return JsonDataProvider.getList(parser, path, null);
    }

    /**
     * Obtem uma lista de objetos parseados
     * @param parser adaptador de dados de json para objeto (JsonParser)
     * @param path caminho do serviço (String)
     * @param params parametros (Map)
     * @param <T> tipo do objeto
     * @return lista de objetos (List)
     * @throws ServerException Exceção de servidor
     * @throws MalformedURLException Exceção de URL
     * @throws JsonException Exceção de JSON
     */
    public static <T> List<T> getList(JsonParser<T> parser, String path, Map<String, String> params)
            throws ServerException, MalformedURLException, JsonException {
        final Map<String, String> values = new HashMap<>();
        values.put(API_KEY,new String(Base64.decode(API_VALUE, Base64.DEFAULT)));
        if (params != null) {
            values.putAll(params);
        }
        final URL url = URLUtil.createURL(DOMAIN_URL, path, values);
        return parser.transformAsList(URLUtil.readFully(url));
    }
}
