package com.mygdx.library;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatedSprite extends GraphicObject{
    private Texture[] frames;
    private int currentFrame;

    public AnimatedSprite() {
        this.frames = null;
        this.currentFrame = -1;
    }

    public AnimatedSprite(Texture[] frames) {
        this.frames = frames;
    }

    /**
     * Imposta l'animazione dello sprite
     * @param frames un'array di immagini di fotogrammi dell'animazione
     */
    public void setAnimation(Texture[] frames) {
        this.frames = frames;
        this.currentFrame = -1;
    }

    /**
     * Imposta la larghezza del fotogramma
     * @param w la larghezza del fotogramma in riferimento alle coordinate della camera
     */
    @Override
    public void setWidth(float w){
        super.setWidth(w);
        if (frames != null) super.setHeight(w * frames[0].getHeight() / frames[0].getWidth());
    }

    /**
     * Imposta l'altezza del fotogramma
     * @param h l'altezza del fotogramma in riferimento alle coordinate della camera
     */
    @Override
    public void setHeight(float h){
        super.setHeight(h);
        if (frames != null) super.setWidth(h * frames[0].getWidth() / frames[0].getWidth());
    }

    /**
     *
     */
    public void update(){
        currentFrame++;
        if (currentFrame == frames.length) currentFrame = 0;
    }

    /**
     * Renderizza l'immagine di riferimento sullo schermo
     * @param sb
     */
    @Override
    public void draw(SpriteBatch sb){
        sb.draw(frames[currentFrame], x + offsetX, y + offsetY, width, height);
    }
}
