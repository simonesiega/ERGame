package com.mygdx.ergame.object;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

/**
 * La classe Fairy rappresenta una fata nel gioco endless runner.
 * La fata ha diverse animazioni per rappresentare vari stati, come volare, camminare, saltare e morire.
 * Estende {@link Character} e implementa la logica specifica per il comportamento della fata.
 */
public class Fairy extends Character {

    /** Array di texture per l'animazione dello stato "idle" della fata. */
    private final Texture[] _animIdle;
    /** Array di texture per l'animazione dello stato "walk" della fata. */
    private final Texture[] _animWalk;
    /** Array di texture per l'animazione dello stato "fly" della fata. */
    private final Texture[] _animFly;
    /** Array di texture per l'animazione dello stato "die" della fata. */
    private final Texture[] _animDie;
    /** Array di texture per l'animazione dello stato "hurt" della fata. */
    private final Texture[] _animHurt;
    /** Array di texture per l'animazione dello stato "jump" della fata. */
    private final Texture[] _animJump;

    /** Variabile per indicare se la fata sta camminando. */
    private boolean _isWalking = false;
    /** Variabile per indicare se la fata sta correndo. */
    private boolean _isRunning = false;
    /** Variabile per indicare se la fata sta scivolando. */
    private boolean _isSliding = false;

    /** Contatore per gestire la frequenza dei frame dell'animazione. */
    private int _frameSkip = 0;

    /**
     * Costruttore di default della classe {@code Fairy}. Inizializza le animazioni della fata,
     * imposta lo stato iniziale e definisce le proprietà fisiche come raggio di collisione e baricentro.
     */
    public Fairy() {
        super();

        // Carica le animazioni relative alla fata dalle risorse
        _animIdle = ResourceLoader.getAnimation(ResourceEnum.FA_IDLE);
        _animWalk = ResourceLoader.getAnimation(ResourceEnum.FA_WALK);
        _animFly = ResourceLoader.getAnimation(ResourceEnum.FA_FLY);
        _animDie = ResourceLoader.getAnimation(ResourceEnum.FA_DIE);
        _animHurt = ResourceLoader.getAnimation(ResourceEnum.FA_HURT);
        _animJump = ResourceLoader.getAnimation(ResourceEnum.FA_JUMP);

        // Imposta l'animazione iniziale a "fly" (volo)
        _sprite.setAnimation(_animFly);
        _sprite.setWidth(1.0f);  // Imposta la larghezza dello sprite
        _sprite.setOffsetX(-0.50f);  // Centra lo sprite sull'asse X
        _sprite.setOffsetY(-0.17f);  // Centra lo sprite sull'asse Y

        // Imposta il raggio di collisione e il baricentro della fata
        setRadius(0.25f);
        setBarycentre(0.05f, 0.25f);
    }

    /**
     * Aggiorna lo stato della fata, comprese le animazioni e la fisica.
     * Gestisce il cambio dei frame con un contatore per evitare di aggiornare l'animazione troppo frequentemente.
     */
    @Override
    public void update() {
        // Gestisce il ciclo di aggiornamento dell'animazione con un ritardo di 2 frame
        if (_frameSkip == 2) {
            _sprite.update();  // Aggiorna l'animazione corrente
            _frameSkip = 0;
        } else {
            _frameSkip++;  // Incrementa il contatore per il salto dei frame
        }

        // Aggiorna la posizione e il movimento della fata
        updatePhysics();
    }

    /**
     * Gestisce la collisione della fata con un altro oggetto di gioco.
     *
     * @param other L'oggetto di gioco con cui la fata ha colliso.
     */
    @Override
    public void manageCollisionWith(GameObject other) {
        // Logica di gestione della collisione
    }
}
