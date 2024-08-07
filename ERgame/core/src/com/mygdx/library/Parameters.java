package com.mygdx.library;

/**
 * Classe di utilità per gestire parametri globali relativi alle proporzioni dello schermo.
 */
public class Parameters {
    private static float _aspectRatio;         // Rapporto di aspetto dello schermo (larghezza/altezza)
    private static float _inverseAspectRatio;  // Rapporto inverso di aspetto dello schermo (altezza/larghezza)

    /**
     * Imposta il rapporto di aspetto basato sulle dimensioni della larghezza e altezza fornite.
     *
     * @param width  la larghezza dello schermo
     * @param height l'altezza dello schermo
     */
    public static void setAspectRatio(float width, float height) {
        _aspectRatio = width / height;
        _inverseAspectRatio = 1.0f / _aspectRatio; // height / width
    }

    /**
     * Restituisce il rapporto di aspetto (larghezza/altezza) dello schermo.
     *
     * @return il rapporto di aspetto
     */
    public static float getAspectRatio() {
        return _aspectRatio;
    }

    /**
     * Restituisce il rapporto inverso di aspetto (altezza/larghezza) dello schermo.
     *
     * @return il rapporto inverso di aspetto
     */
    public static float getInverseAspectRatio() {
        return _inverseAspectRatio;
    }
}
