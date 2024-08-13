package com.mygdx.ergame.object;

import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

public class Coin extends GameObject {

    public Coin(){
        super();

        _sprite.setAnimation(ResourceLoader.getAnimation(ResourceEnum.COIN_GOLD));

        _sprite.setWidth(0.3f);
        _sprite.setOffsetX(-0.15f);
        _sprite.setOffsetY(-0.15f);
    }

    public void update(){
        _sprite.update();
        updatePhysics();
    }
}
