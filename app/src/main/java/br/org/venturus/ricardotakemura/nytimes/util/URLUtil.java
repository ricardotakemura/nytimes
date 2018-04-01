package br.org.venturus.ricardotakemura.nytimes.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import br.org.venturus.ricardotakemura.nytimes.exception.ServerException;

public final class URLUtil {

    private static final String UTF_8 = "utf-8";

    public static String readFully(final URL url) throws ServerException {
        try {
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader reader = null;
            StringBuilder data = new StringBuilder();
            if (conn.getDoInput()) {
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        data.append(line);
                    }
                    close(reader);
                } else {
                    throw new ServerException(conn.getResponseCode());
                }
            }
            return data.toString();
        } catch (IOException e) {
            throw new ServerException(ServerException.LOCAL_ERROR, e);
        }
    }

    private static void close(final Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            Log.e("CLOSE_FILE", e.getMessage(), e);
        }
    }

    public static URL createURL(final String domain, final String path, final Map<String, String> params) throws MalformedURLException {
        try {
            StringBuilder builder = new StringBuilder(domain);
            builder.append(path);
            if (params != null && !params.isEmpty()) {
                builder.append("?");
                for (String key : params.keySet()) {
                    builder.append(URLEncoder.encode(key, UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(params.get(key), UTF_8))
                            .append("&");
                }
                return new URL(builder.substring(0, builder.length() - 1));
            }
            return new URL(builder.toString());
        } catch (UnsupportedEncodingException e) {
            MalformedURLException exception = new MalformedURLException(e.getMessage());
            exception.initCause(e);
            throw exception;
        }
    }

}
