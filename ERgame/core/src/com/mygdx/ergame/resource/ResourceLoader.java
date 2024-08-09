package com.mygdx.ergame.resource;

import com.badlogic.gdx.graphics.Texture;

import java.util.EnumMap;
import java.util.Objects;

/**
 *
 */
public class ResourceLoader {
    /**
     * Mappa per la gestione delle Texture
     */
    private static final EnumMap<ResourceEnum, Texture> _mapTexture = new EnumMap<>(ResourceEnum.class);

    private static final EnumMap<ResourceEnum, Texture[]> _mapAnimation = new EnumMap<>(ResourceEnum.class);


    public static Texture getTexture(ResourceEnum index) {
        if (!_mapTexture.containsKey(index)){
            switch (index){
                case LOGO:
                    _mapTexture.put(index, new Texture("badlogic.jpg"));
                    break;

                case SKY_LEVEL:
                    _mapTexture.put(index, new Texture("level00/Sky.png"));
                    break;

                case BG_LEVEL:
                    _mapTexture.put(index, new Texture("level00/bg.png"));
                    break;

                case MG_LEVEL:
                    _mapTexture.put(index, new Texture("level00/mg.png"));
                    break;

                case FG_LEVEL:
                    _mapTexture.put(index, new Texture("level00/fg.png"));
                    break;

                case G_LEVEL:
                    _mapTexture.put(index, new Texture("level00/g.png"));
                    break;
            }
        }
        return _mapTexture.get(index);
    }


    public static Texture[] getAnimation(ResourceEnum index) {
        Texture[] res = null;

        if (!_mapAnimation.containsKey(index)){
            switch (index) {
                case KN_IDLE:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__IDLE_00" + i +".png");
                    }
                    _mapAnimation.put(index, res);
                    break;

                case KN_WALK:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__WALK_00" + i +".png");
                        // mapAnimation.put(index, res);
                    }
                    _mapAnimation.put(index, res);
                    break;

                case KN_JUMP:
                    res = new Texture[1];
                    for (int i = 4; i < (4 + res.length); i++) {
                        res[i-4] = new Texture("knight/Knight_02__JUMP_00" + i +".png");
                        // _mapAnimation.put(index, res);
                    }
                    _mapAnimation.put(index, res);
                    break;

                case KN_RUN:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__RUN_00" + i +".png");
                    }
                    _mapAnimation.put(index, res);
                    break;
            }
        } else {
            res = _mapAnimation.get(index);
        }

        return res;
    }
}
