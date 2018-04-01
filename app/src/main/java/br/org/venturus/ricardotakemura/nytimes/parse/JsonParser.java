package br.org.venturus.ricardotakemura.nytimes.parse;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;
import br.org.venturus.ricardotakemura.nytimes.util.DateUtil;

/**
 * Classe que transforma JSON em objetos Java
 * @param <T> Tipo da classe
 * @author ricardotakemura
 */
public abstract class JsonParser<T> {

    /**
     * Status OK
     */
    protected final String OK = "OK";
    /**
     * Nome do campo status
     */
    protected final String STATUS_FIELD = "status";

    /**
     * Transforma um JSON em uma lista de objetos
     * @param source JSON (String)
     * @return List
     * @throws JsonException Exceção de JSON
     */
    public List<T> transformAsList(final String source) throws JsonException {
        try {
            final JSONObject root = new JSONObject(source);
            final String status = getValue(root, STATUS_FIELD, String.class);
            if (OK.equals(status)) {
                return parseAsList(root);
            }
            return new ArrayList<>();
        } catch (JSONException e) {
            throw new JsonException(e.getMessage(), e);
        }
    }

    /**
     * Obtem os dados de JSONObject e constroi uma lista de objetos
     * @param object JSONObject
     * @return List
     * @throws JsonException Exceção de JSON
     */
    protected abstract List<T> parseAsList(final JSONObject object) throws JsonException;

    /**
     * Obtem os dados de JSONObject e constroi um objeto
     * @param object JSONObject
     * @return Object
     * @throws JsonException Exceção de JSON
     */
    protected abstract T parse(final JSONObject object) throws JsonException;

    /**
     * Obtem e transforma um dado do JSONObject
     * @param object JSONObject
     * @param key chave do json (String)
     * @param clazz Classe que o dado irá se transformar
     * @param <X> Tipo do objeto
     * @return Object
     * @throws JsonException Exceção de JSON
     */
    @Nullable
    protected <X> X getValue(final JSONObject object, final String key, final Class<X> clazz) throws JsonException {
        try {
            if (!object.isNull(key)) {
                if (String.class.equals(clazz)) {
                    return (X) object.getString(key);
                } else if (Integer.class.equals(clazz)) {
                    return (X) Integer.valueOf(object.getInt(key));
                } else if (Date.class.equals(clazz)) {
                    return (X) DateUtil.dateFromString(object.getString(key));
                } else if (JSONArray.class.equals(clazz)) {
                    return (X) object.getJSONArray(key);
                } else if (JSONObject.class.equals(clazz)) {
                    return (X) object.getJSONObject(key);
                } else if (URL.class.equals(clazz)) {
                    return (X) new URL(object.getString(key));
                } else if (Picture.PictureFormat.class.equals(clazz)) {
                    return (X) Picture.PictureFormat.valueOfString(object.getString(key));
                }
            }
            return null;
        } catch (Exception e) {
            throw new JsonException(e.getMessage(), e);
        }
    }

    /**
     * Obtem um JSONObject de um JSONArray
     * @param array JSONArray
     * @param index indice no array
     * @return JSONObject
     * @throws JsonException Exceção de JSON
     */
    @Nullable
    protected JSONObject getValue(final JSONArray array, final int index) throws JsonException {
        try {
            if (!array.isNull(index)) {
                return array.getJSONObject(index);
            }
            return null;
        } catch (JSONException e) {
            throw new JsonException(e.getMessage(), e);
        }
    }

}
