package com.mygdx.library;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe di riferimento per tutti gli oggetti grafici che possono essere disegnati sullo schermo.
 * Fornisce attributi di base come posizione, dimensioni e offset.
 */
public abstract class GraphicObject implements Drawable {
    protected float _x;         // Coordinata X dell'oggetto
    protected float _y;         // Coordinata Y dell'oggetto
    protected float _width;     // Larghezza dell'oggetto
    protected float _height;    // Altezza dell'oggetto
    protected float _offsetX;   // Offset X per il rendering
    protected float _offsetY;   // Offset Y per il rendering

    /**
     * Ritorna la coordinata X dell'oggetto in riferimento allo schermo.
     *
     * @return la coordinata X
     */
    public float getX() {
        return _x;
    }

    /**
     * Ritorna la coordinata Y dell'oggetto in riferimento allo schermo.
     *
     * @return la coordinata Y
     */
    public float getY() {
        return _y;
    }

    /**
     * Imposta la coordinata X dell'oggetto.
     *
     * @param _x la coordinata X
     */
    public void setX(float _x) {
        this._x = _x;
    }

    /**
     * Imposta la coordinata Y dell'oggetto.
     *
     * @param y la coordinata Y
     */
    public void setY(float y) {
        this._y = y;
    }

    /**
     * Ritorna la larghezza dell'immagine da renderizzare.
     *
     * @return la larghezza dell'immagine
     */
    public float getWidth() {
        return _width;
    }

    /**
     * Ritorna l'altezza dell'immagine da renderizzare.
     *
     * @return l'altezza dell'immagine
     */
    public float getHeight() {
        return _height;
    }

    /**
     * Imposta la larghezza dell'immagine.
     *
     * @param w la larghezza dell'immagine in riferimento alle coordinate della camera
     */
    public void setWidth(float w) {
        this._width = w;
    }

    /**
     * Imposta l'altezza dell'immagine.
     *
     * @param h l'altezza dell'immagine in riferimento alle coordinate della camera
     */
    public void setHeight(float h) {
        this._height = h;
    }

    /**
     * Ritorna l'offset X per il rendering.
     *
     * @return l'offset X
     */
    public float getOffsetX() {
        return _offsetX;
    }

    /**
     * Ritorna l'offset Y per il rendering.
     *
     * @return l'offset Y
     */
    public float getOffsetY() {
        return _offsetY;
    }

    /**
     * Imposta l'offset X per il rendering.
     *
     * @param x l'offset X
     */
    public void setOffsetX(float x) {
        this._offsetX = x;
    }

    /**
     * Imposta l'offset Y per il rendering.
     *
     * @param y l'offset Y
     */
    public void setOffsetY(float y) {
        this._offsetY = y;
    }

    /**
     * Metodo astratto per il disegno dell'oggetto.
     * Deve essere implementato dalle classi concrete che estendono GraphicObject.
     *
     * @param sb il batch utilizzato per disegnare lo sprite
     */
    public abstract void draw(SpriteBatch sb);
}
