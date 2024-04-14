package com.mygdx.library;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rectangle extends GraphicObject{
    private Texture background;

    public Rectangle(float x, float y, float width, float height) {

    }

    public Rectangle(float width, float height) {

    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(background,x,y);
    }
}
