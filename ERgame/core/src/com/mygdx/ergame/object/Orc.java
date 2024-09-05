package com.mygdx.ergame.object;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

public class Orc extends Character {

    // Array di texture per le animazioni del cavaliere
    private final Texture[] _animIdle;
    private final Texture[] _animRun;
    private final Texture[] _animSli;
    private final Texture[] _animWalk;

    // Variabili per gestire i cambiamenti di stato
    private boolean _isWalking = false;
    private boolean _isRunning = false;
    private boolean _isSliding = false;

    // Contatore per gestire la frequenza dei frame dell'animazione
    private int _frameSkip = 0;

    public Orc() {
        super();

        // Carica le animazioni dalle risorse
        _animIdle = ResourceLoader.getAnimation(ResourceEnum.OR_IDLE);
        _animWalk = ResourceLoader.getAnimation(ResourceEnum.OR_WALK);
        _animRun = ResourceLoader.getAnimation(ResourceEnum.OR_RUN);
        _animSli = ResourceLoader.getAnimation(ResourceEnum.OR_SLID);

        // Imposta l'animazione iniziale a "idle"
        _sprite.setAnimation(_animIdle);
        _sprite.setWidth(1.0f);  // Imposta la larghezza dello sprite
        _sprite.setOffsetX(-0.50f);  // Centra lo sprite sull'asse X
        _sprite.setOffsetY(-0.17f);  // Centra lo sprite sull'asse Y

        // Imposta il raggio di collisione e il baricentro del cavaliere
        setRadius(0.25f);
        setBarycentre(0.05f, 0.25f);
    }

    @Override
    public void update() {
        _sprite.update();
        updatePhysics();
    }

    @Override
    public void manageCollisionWith(GameObject other) {

    }
}
