package com.mygdx.library;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe di riferimento per tutti gli oggetti grafici che possono essere disegnati nello schermo
 */
public abstract class GraphicObject implements Drawable{
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float offsetX;
    protected float offsetY;

    /**
     * Ritorna la coordinata X dell'oggetto in riferimenti dello schermo
     * @return la coordinata x
     */
    public float getX(){
        return x;
    }

    /**
     * Ritorna la coordinata Y dell'oggetto in riferimenti dello schermo
     * @return la coordinata y
     */
    public float getY(){
        return y;
    }

    /**
     * Imposta la coordinata X dell oggetto
     * @param x  la coordinata X
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * Imposta la coordinata Y dell oggetto
     * @param y  la coordinata Y
     */
    public void setY(float y){
        this.y = y;
    }

    /**
     * Ritorna la larghezza dell'immagine da renderizzare
     * @return la larghezza dell'immagine
     */
    public float getWidth(){
        return width;
    }

    /**
     * Ritorna l'altezza dell'immagine da renderizzare
     * @return l'altezza dell'immagine
     */
    public float getHeight(){
        return height;
    }

    /**
     * Imposta la larghezza dell'immagine
     * @param w la larghezza dell'immagine inm riferimento alle coordinate della camera
     */
    public void setWidth(float w) {
        width = w;
    }

    /**
     * Imposta l'altezza del fotogramma
     * @param h l'altezza del fotogramma in riferimento alle coordinate della camera
     */
    public void setHeight(float h) {
        height = h;
    }

    /**
     *
     * @return
     */
    public float getOffsetX(){
        return offsetX;
    }

    /**
     *
     * @return
     */
    public float getOffsetY(){
        return offsetY;
    }

    /**
     *
     * @param x
     */
    public void setOffsetX(float x){
        this.offsetX = x;
    }

    /**
     *
     * @param y
     */
    public void setOffsetY(float y){
        this.offsetY = y;
    }

    public abstract void draw(SpriteBatch sb);
}
