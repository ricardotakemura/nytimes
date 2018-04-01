package br.org.venturus.ricardotakemura.nytimes.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Classe útil para manipulação de tamanhos do tipo DPI (densidade)
 * @author ricardotakemura
 */
public final class DpiUtil {

    /**
     * Transforma dpi em pixel (px)
     * @param dpi dpi(int)
     * @return pixel(int)
     */
    public static int toPixels(final int dpi) {
        final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, metrics);
    }
}
