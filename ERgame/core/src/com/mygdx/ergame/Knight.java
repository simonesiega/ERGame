package com.mygdx.ergame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.AnimatedSprite;
import com.mygdx.library.GraphicObject;

public class Knight extends GraphicObject {

    enum KNIGHT_STATE {
        IDLE,
        WALK
    }

    private final AnimatedSprite _sprite;

    private final Texture[] _animIdle;
    private final Texture[] _animWalk;

    private boolean isWalking;
    private KNIGHT_STATE _state;

    public Knight() {
        super();

        _animIdle = ResourceLoader.getAnimation(ResourceEnum.KN_IDLE);
        _animWalk = ResourceLoader.getAnimation(ResourceEnum.KN_WALK);

        _sprite = new AnimatedSprite(_animIdle);
        _sprite.setWidth(3);
        _sprite.setOffsetX(-1.5f);
        _sprite.setOffsetY(-0.5f);

        _state = KNIGHT_STATE.IDLE;
        isWalking = false;
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        _sprite.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        _sprite.setY(y);
    }

    public void setWalk(boolean walk){
        isWalking = walk;
    }

    @Override
    public void draw(SpriteBatch sb) {
        _sprite.draw(sb);
    }

    public void update(){
        switch(_state){
            case IDLE:
                if (isWalking) {
                    _state = KNIGHT_STATE.WALK;
                    _sprite.setAnimation(_animWalk);
                }
                _sprite.update();
                break;

            case WALK:
                if (!isWalking) {
                    _state = KNIGHT_STATE.IDLE;
                    _sprite.setAnimation(_animIdle);
                }
                _sprite.update();
                break;
        }
    }
}
