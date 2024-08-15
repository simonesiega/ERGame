package com.mygdx.ergame.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.EnumMap;
import java.util.Objects;

/**
 * La classe `ResourceLoader` è responsabile del caricamento e della gestione delle risorse
 * di gioco come texture, animazioni e suoni. Utilizza mappe di tipo `EnumMap` per memorizzare
 * le risorse associate a ogni costante dell'enum `ResourceEnum`.
 */
public class ResourceLoader {

    // Mappa per memorizzare le texture caricate
    private static final EnumMap<ResourceEnum, Texture> _mapTexture = new EnumMap<>(ResourceEnum.class);

    // Mappa per memorizzare le animazioni caricate (array di texture)
    private static final EnumMap<ResourceEnum, Texture[]> _mapAnimation = new EnumMap<>(ResourceEnum.class);

    // Mappa per memorizzare i suoni caricati
    private static final EnumMap<ResourceEnum, Sound> _mapSound = new EnumMap<>(ResourceEnum.class);

    /**
     * Restituisce una texture associata a una costante di `ResourceEnum`.
     * Se la texture non è già stata caricata, la carica e la memorizza nella mappa `_mapTexture`.
     *
     * @param index La costante di `ResourceEnum` associata alla texture desiderata.
     * @return La texture richiesta.
     */
    public static Texture getTexture(ResourceEnum index) {
        if (!_mapTexture.containsKey(index)) {
            switch (index) {
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

                case BUBBLE_COLLISION:
                    _mapTexture.put(index, new Texture("bubble.png"));
                    break;

                case CUORE_CAVALIERE:
                    _mapTexture.put(index, new Texture("knight/heart.png"));
                    break;

                case COIN_GOLD:
                    _mapTexture.put(index, new Texture("coin/gold_00.png"));
                    break;
            }
        }
        return _mapTexture.get(index);
    }

    /**
     * Restituisce un array di texture (animazione) associato a una costante di `ResourceEnum`.
     * Se l'animazione non è già stata caricata, la carica e la memorizza nella mappa `_mapAnimation`.
     *
     * @param index La costante di `ResourceEnum` associata all'animazione desiderata.
     * @return L'array di texture che rappresenta l'animazione richiesta.
     */
    public static Texture[] getAnimation(ResourceEnum index) {
        Texture[] res = null;

        if (!_mapAnimation.containsKey(index)) {
            switch (index) {
                case KN_IDLE:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__IDLE_00" + i + ".png");
                    }
                    _mapAnimation.put(index, res);
                    break;

                case KN_WALK:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__WALK_00" + i + ".png");
                    }
                    _mapAnimation.put(index, res);
                    break;

                case KN_JUMP:
                    res = new Texture[1];
                    for (int i = 4; i < (4 + res.length); i++) {
                        res[i - 4] = new Texture("knight/Knight_02__JUMP_00" + i + ".png");
                    }
                    _mapAnimation.put(index, res);
                    break;

                case KN_RUN:
                    res = new Texture[10];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("knight/Knight_02__RUN_00" + i + ".png");
                    }
                    _mapAnimation.put(index, res);
                    break;

                case COIN_GOLD:
                    res = new Texture[30];
                    for (int i = 0; i < res.length; i++) {
                        res[i] = new Texture("coin/gold_0" + (i / 3) + ".png");
                    }
                    _mapAnimation.put(index, res);
                    break;
            }
        } else {
            res = _mapAnimation.get(index);
        }

        return res;
    }

    /**
     * Restituisce un suono associato a una costante di `ResourceEnum`.
     * Se il suono non è già stato caricato, lo carica e lo memorizza nella mappa `_mapSound`.
     *
     * @param index La costante di `ResourceEnum` associata al suono desiderato.
     * @return Il suono richiesto.
     */
    public static Sound getSound(ResourceEnum index) {
        Sound res = null;

        if (!_mapSound.containsKey(index)) {
            switch (index) {
                case AUDIO_COIN:
                    res = Gdx.audio.newSound(Gdx.files.internal("audio/coin.wav"));
                    _mapSound.put(index, res);
                    break;
            }
        }

        return res;
    }
}
