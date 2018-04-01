package br.org.venturus.ricardotakemura.nytimes.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DpiUtil {
    public static int toPixels(int dpi) {
        final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, metrics);
    }
}
