package com.mygdx.library;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe che rappresenta un rettangolo grafico.
 * Estende la classe GraphicObject e può essere disegnato sullo schermo.
 */
public class Rectangle extends GraphicObject {
    private Texture _background; // Texture di sfondo del rettangolo

    /**
     * Costruttore che crea un rettangolo con posizione e dimensioni specificate.
     *
     * @param x      la coordinata X del rettangolo
     * @param y      la coordinata Y del rettangolo
     * @param width  la larghezza del rettangolo
     * @param height l'altezza del rettangolo
     */
    public Rectangle(float x, float y, float width, float height) {
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
    }

    /**
     * Costruttore che crea un rettangolo con dimensioni specificate.
     * La posizione del rettangolo verrà impostata a (0, 0).
     *
     * @param width  la larghezza del rettangolo
     * @param height l'altezza del rettangolo
     */
    public Rectangle(float width, float height) {
        this(0, 0, width, height);
    }

    /**
     * Imposta la texture di sfondo del rettangolo.
     *
     * @param _background la texture di sfondo
     */
    public void setBackground(Texture _background) {
        this._background = _background;
    }

    /**
     * Disegna il rettangolo sullo schermo utilizzando la texture di sfondo.
     *
     * @param sb il batch utilizzato per disegnare lo sprite
     */
    @Override
    public void draw(SpriteBatch sb) {
        if (_background != null) {
            sb.draw(_background, _x + _offsetX, _y + _offsetY, _width, _height);
        }
    }
}
