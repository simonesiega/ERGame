package com.mygdx.library;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La classe AnimatedSprite rappresenta uno sprite animato,
 * che utilizza una sequenza di texture per creare un'animazione frame-by-frame.
 */
public class AnimatedSprite extends GraphicObject {
    private Texture[] frames; // Array di texture che rappresentano i fotogrammi dell'animazione.
    private int currentFrame; // Indice del fotogramma corrente.

    /**
     * Costruttore di default che inizializza l'animazione a null e il fotogramma corrente a -1.
     */
    public AnimatedSprite() {
        this.frames = null;
        this.currentFrame = -1;
    }

    /**
     * Costruttore che inizializza l'animazione con un array di texture.
     *
     * @param frames Array di texture che rappresentano i fotogrammi dell'animazione.
     */
    public AnimatedSprite(Texture[] frames) {
        this.frames = frames;
        this.currentFrame = 0;
    }

    /**
     * Imposta l'animazione dello sprite.
     *
     * @param frames Array di texture che rappresentano i fotogrammi dell'animazione.
     */
    public void setAnimation(Texture[] frames) {
        this.frames = frames;
        this.currentFrame = 0;
    }

    /**
     * Imposta la larghezza del fotogramma e mantiene le proporzioni impostando l'altezza in modo corrispondente.
     *
     * @param w La larghezza del fotogramma in riferimento alle coordinate della camera.
     */
    @Override
    public void setWidth(float w) {
        super.setWidth(w);
        if (frames != null) {
            super.setHeight(w * frames[0].getHeight() / frames[0].getWidth());
        }
    }

    /**
     * Imposta l'altezza del fotogramma e mantiene le proporzioni impostando la larghezza in modo corrispondente.
     *
     * @param h L'altezza del fotogramma in riferimento alle coordinate della camera.
     */
    @Override
    public void setHeight(float h) {
        super.setHeight(h);
        if (frames != null) {
            super.setWidth(h * frames[0].getWidth() / frames[0].getHeight());
        }
    }

    /**
     * Aggiorna il fotogramma corrente dell'animazione, passando al fotogramma successivo.
     * Se l'ultimo fotogramma è stato raggiunto, ricomincia dal primo.
     */
    public void update() {
        if (frames != null && frames.length > 0) {
            currentFrame++;
            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }
        }
    }

    /**
     * Renderizza il fotogramma corrente dell'animazione sullo schermo.
     *
     * @param sb Il batch utilizzato per disegnare lo sprite.
     */
    @Override
    public void draw(SpriteBatch sb) {
        if (frames != null && frames.length > 0) {
            sb.draw(frames[currentFrame], _x + _offsetX, _y + _offsetY, _width, _height);
        }
    }
}
