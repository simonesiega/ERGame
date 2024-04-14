package com.mygdx.library;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Rappresenta un oggetto da disegna sullo schermo con le sue informazioni posizionali e di dimensione
 */
public class Sprite extends GraphicObject{
    private Texture frame;

    public Sprite(Texture img) {
        frame = img;

        x = 0;
        y = 0;
        width = 1;
        height = (float) frame.getHeight() / frame.getWidth();
    }

    /**
     * Imposta la larghezza del fotogramma
     * @param w la larghezza del fotogramma in riferimento alle coordinate della camera
     */
    @Override
    public void setWidth(float w){
        super.setWidth(w);
        super.setHeight(w * frame.getHeight() / frame.getWidth());
    }

    /**
     * Imposta l'altezza del fotogramma
     * @param h l'altezza del fotogramma in riferimento alle coordinate della camera
     */
    @Override
    public void setHeight(float h){
        super.setHeight(h);
        super.setWidth(h * frame.getWidth() / frame.getWidth());
    }

    /**
     * Renderizza l'immagine di riferimento sullo schermo
     * @param sb
     */
    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(frame, x, y, width, height);
    }
}
