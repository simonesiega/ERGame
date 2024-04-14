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
    private static final EnumMap<ResourceEnum, Texture> mapTexture = new EnumMap<ResourceEnum, Texture>(ResourceEnum.class);

    private static EnumMap<ResourceEnum, Texture[]> mapAnimation = new EnumMap<ResourceEnum, Texture[]>(ResourceEnum.class);

    /**
     *
     * @param index
     * @return
     */
    public static Texture getTexture(ResourceEnum index) {
        if (!mapTexture.containsKey(index)){
            /*
            switch (index) {
                case LOGO:
                    mapTexture.put(index, new Texture("badlogic.jpg"));
                    break;
                default: return null;
            }
            */
            if (Objects.requireNonNull(index) == ResourceEnum.LOGO) {
                mapTexture.put(index, new Texture("badlogic.jpg"));
            } else {
                return null;
            }
        }
        return mapTexture.get(index);
    }

    /**
     *
     * @param index
     * @return
     */
    public static Texture[] getAnimation(ResourceEnum index) {
        Texture[] res = null;

        if (!mapAnimation.containsKey(index)){
            switch (index) {
                case KN_IDLE:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__IDLE_00" + i +".png");
                        // mapAnimation.put(index, res);
                    }
                    mapAnimation.put(index, res);
                    break;

                case KN_WALK:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__WALK_00" + i +".png");
                        // mapAnimation.put(index, res);
                    }
                    mapAnimation.put(index, res);
                    break;

                case KN_JUMP:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__JUMP_00" + i +".png");
                        // mapAnimation.put(index, res);
                    }
                    mapAnimation.put(index, res);
                    break;

                case KN_RUN:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__RUN_00" + i +".png");
                        // mapAnimation.put(index, res);
                    }
                    mapAnimation.put(index, res);
                    break;
            }
        } else {
            res = mapAnimation.get(index);
        }

        return res;
    }
}
