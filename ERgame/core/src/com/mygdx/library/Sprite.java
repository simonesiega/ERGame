package com.mygdx.library;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Rappresenta un oggetto da disegnare sullo schermo con le sue informazioni posizionali e di dimensione.
 * Estende la classe GraphicObject e rappresenta uno sprite statico.
 */
public class Sprite extends GraphicObject {
    private final Texture _frame; // Texture che rappresenta il fotogramma dello sprite

    /**
     * Costruttore che inizializza lo sprite con una texture.
     *
     * @param img la texture da utilizzare per lo sprite
     */
    public Sprite(Texture img) {
        _frame = img;
        _x = _y = 0;
        _width = 1;
        _height = (float) _frame.getHeight() / _frame.getWidth();
    }

    /**
     * Imposta la larghezza del fotogramma e aggiorna l'altezza mantenendo le proporzioni.
     *
     * @param w la larghezza del fotogramma in riferimento alle coordinate della camera
     */
    @Override
    public void setWidth(float w) {
        super.setWidth(w);
        super.setHeight(w * _frame.getHeight() / _frame.getWidth());
    }

    /**
     * Imposta l'altezza del fotogramma e aggiorna la larghezza mantenendo le proporzioni.
     *
     * @param h l'altezza del fotogramma in riferimento alle coordinate della camera
     */
    @Override
    public void setHeight(float h) {
        super.setHeight(h);
        super.setWidth(h * _frame.getWidth() / _frame.getHeight());
    }

    /**
     * Renderizza lo sprite sullo schermo.
     *
     * @param sb il batch utilizzato per disegnare lo sprite
     */
    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(_frame, _x + _offsetX, _y + _offsetY, _width, _height);
    }
}
