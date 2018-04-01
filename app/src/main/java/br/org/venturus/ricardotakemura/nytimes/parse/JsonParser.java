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

public abstract class JsonParser<T> {

    protected final String OK = "OK";
    protected final String STATUS_FIELD = "status";

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

    protected abstract List<T> parseAsList(final JSONObject object) throws JsonException;

    protected abstract T parse(final JSONObject object) throws JsonException;

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
