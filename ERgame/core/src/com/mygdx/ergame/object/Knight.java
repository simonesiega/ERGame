package com.mygdx.ergame.object;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

import static com.mygdx.ergame.object.Knight.Constants.*;

/**
 * La classe Knight rappresenta il cavaliere controllato dal giocatore nel gioco endless runner.
 * Il cavaliere può eseguire diverse azioni come camminare, correre e saltare, con animazioni
 * specifiche per ogni stato. Estende {@link Character}.
 */
public class Knight extends Character {

    /**
     * Classe interna Constants che contiene costanti associate al cavaliere,
     * inclusi valori di vita, danno e cura.
     */
    public static class Constants {
        public static final float MAX_HEALTH = 200;  // Vita massima del cavaliere
        public static final float MIN_HEALTH = 0;    // Vita minima del cavaliere

        public static final float DAMAGE_ORC = 60;   // Danno subito dal cavaliere in caso di collisione con un orco
        public static final float HEAL_FAIRY = 40;   // Quantità di vita recuperata dal cavaliere in caso di collisione con una fata
    }

    // Array di texture per le animazioni del cavaliere
    private final Texture[] _animIdle;
    private final Texture[] _animWalk;
    private final Texture[] _animRun;
    private final Texture[] _animJump;

    // Variabili per gestire i cambiamenti di stato del cavaliere
    private boolean _isWalking = false;
    private boolean _isRunning = false;
    private boolean _isJumping = false;

    // Contatore per gestire la frequenza dei frame dell'animazione
    private int _frameSkip = 0;

    /**
     * Costruttore di default della classe Knight. Inizializza le animazioni,
     * imposta la larghezza dello sprite, il raggio di collisione, il baricentro e lo stato iniziale.
     */
    public Knight() {
        super();

        // Carica le animazioni relative al cavaliere dalle risorse
        _animIdle = ResourceLoader.getAnimation(ResourceEnum.KN_IDLE);
        _animWalk = ResourceLoader.getAnimation(ResourceEnum.KN_WALK);
        _animRun = ResourceLoader.getAnimation(ResourceEnum.KN_RUN);
        _animJump = ResourceLoader.getAnimation(ResourceEnum.KN_JUMP);

        // Imposta l'animazione iniziale a "idle"
        _sprite.setAnimation(_animIdle);
        _sprite.setWidth(3);  // Imposta la larghezza dello sprite
        _sprite.setOffsetX(-1.5f);  // Centra lo sprite sull'asse X
        _sprite.setOffsetY(-0.5f);  // Centra lo sprite sull'asse Y

        // Imposta il raggio di collisione e il baricentro del cavaliere
        setRadius(0.4f);
        setBarycentre(0.1f, 0.4f);

        // Imposta la vita del cavaliere
        _health = MAX_HEALTH;

        // Stato iniziale del cavaliere
        _state = CHARACTER_STATE.IDLE;

        // Inizia lo stato di camminata
        entryWalk();
    }

    /**
     * Restituisce la vita attuale del cavaliere.
     *
     * @return la vita del cavaliere.
     */
    public float getHealth(){
        return _health;
    }

    /**
     * Restituisce la vita massima del cavaliere.
     *
     * @return la vita massima del cavaliere.
     */
    public float getMaxHealth(){
        return MAX_HEALTH;
    }

    /**
     * Imposta lo stato di camminata del cavaliere.
     *
     * @param walk {@code true} per attivare lo stato di camminata, {@code false} altrimenti.
     */
    public void setWalk(boolean walk){
        _isWalking = walk;
    }

    /**
     * Imposta lo stato di corsa del cavaliere.
     *
     * @param run {@code true} per attivare lo stato di corsa, {@code false} altrimenti.
     */
    public void setRun(boolean run){
        _isRunning = run;
    }

    /**
     * Imposta lo stato di salto del cavaliere.
     *
     * @param jump {@code true} per attivare lo stato di salto, {@code false} altrimenti.
     */
    public void setJump(boolean jump){
        _isJumping = jump;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di camminata.
     *
     * @return {@code true} se il cavaliere sta camminando, {@code false} altrimenti.
     */
    public boolean isWalking(){
        return _state == CHARACTER_STATE.WALK;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di corsa.
     *
     * @return {@code true} se il cavaliere sta correndo, {@code false} altrimenti.
     */
    public boolean isRunning(){
        return _state == CHARACTER_STATE.RUN;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di salto verso l'alto.
     *
     * @return {@code true} se il cavaliere sta saltando, {@code false} altrimenti.
     */
    public boolean isJumping(){
        return _state == CHARACTER_STATE.WALK && _velocity.y >= 0;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di caduta.
     *
     * @return {@code true} se il cavaliere sta cadendo, {@code false} altrimenti.
     */
    public boolean isFalling(){
        return _state == CHARACTER_STATE.JUMP && _velocity.y <= 0;
    }

    /**
     * Aggiorna lo stato del cavaliere, gestendo la transizione tra camminata, corsa e salto.
     */
    public void update(){
        switch (_state) {
            case WALK:
                if (_isRunning){
                    entryRun();  // Passa allo stato di corsa
                    doRun();     // Esegue la logica di corsa
                }
                else doWalk();    // Continua a camminare
                break;

            case RUN:
                if (_isJumping){
                    entryJump();  // Passa allo stato di salto
                    doJump();     // Esegue la logica di salto
                }
                else doRun();      // Continua a correre
                break;

            case JUMP:
                if (_isRunning){
                    entryRun();   // Passa allo stato di corsa
                    doRun();      // Esegue la logica di corsa
                }
                else doJump();     // Continua a saltare
                break;
        }
    }

    /**
     * Gestisce la collisione del cavaliere con un altro oggetto di gioco.
     *
     * @param other l'oggetto con cui il cavaliere ha colliso.
     */
    @Override
    public void manageCollisionWith(GameObject other) {
        if (other instanceof Coin){
            // Azioni in caso di collisione con una moneta
        }
        else if (other instanceof Orc){
            // Riduce la vita del cavaliere in caso di collisione con un orco
            _health -= DAMAGE_ORC;
        }
        else if (other instanceof Fairy){
            // Cura il cavaliere in caso di collisione con una fata
            float s = _health + HEAL_FAIRY;
            if (s <= MAX_HEALTH){
                _health = s;
            }
        }
    }

    /**
     * Inizializza lo stato di camminata del cavaliere, impostando
     * l'animazione e la velocità.
     */
    private void entryWalk(){
        _state = CHARACTER_STATE.WALK;  // Cambia lo stato a camminata
        _sprite.setAnimation(_animWalk);  // Imposta l'animazione di camminata
        _frameSkip = 0;  // Resetta il contatore dei frame
        _velocity.x = 0.01f;  // Imposta la velocità orizzontale

        _isWalking = false;  // Resetta la variabile di camminata
    }

    /**
     * Esegue la logica dello stato di camminata, aggiornando
     * l'animazione e la fisica del cavaliere.
     */
    private void doWalk(){
        if (_frameSkip % 2 == 0) {
            _sprite.update();  // Aggiorna l'animazione dello sprite
            _frameSkip++;
        }
        else _frameSkip = 0;

        updatePhysics();  // Aggiorna la fisica del cavaliere
    }

    /**
     * Inizializza lo stato di corsa del cavaliere, impostando
     * l'animazione e la velocità.
     */
    private void entryRun(){
        _state = CHARACTER_STATE.RUN;  // Cambia lo stato a corsa
        _sprite.setAnimation(_animRun);  // Imposta l'animazione di corsa


        _frameSkip = 0;  // Resetta il contatore dei frame
        setVelocity(0, 0);  // Resetta la velocità
        setAcceleration(0, 0);  // Resetta l'accelerazione

        _isRunning = false;  // Resetta la variabile di corsa
    }

    /**
     * Esegue la logica dello stato di corsa, aggiornando
     * l'animazione del cavaliere.
     */
    private void doRun(){
        if (_frameSkip % 2 == 0) {
            _sprite.update();  // Aggiorna l'animazione dello sprite
            _frameSkip++;
        }
        else _frameSkip = 0;
    }

    /**
     * Inizializza lo stato di salto del cavaliere, impostando
     * l'animazione, la velocità e l'accelerazione.
     */
    private void entryJump(){
        _state = CHARACTER_STATE.JUMP;  // Cambia lo stato a salto
        _sprite.setAnimation(_animJump);  // Imposta l'animazione di salto
        _frameSkip = 0;  // Resetta il contatore dei frame
        _velocity.y = 0.12f;  // Imposta la velocità verticale
        _acceleration.y = -0.005f;  // Imposta l'accelerazione verticale

        _isJumping = false;  // Resetta la variabile di salto
    }

    /**
     * Esegue la logica dello stato di salto, aggiornando
     * l'animazione e la fisica del cavaliere.
     */
    private void doJump(){
        if (_frameSkip % 2 == 0) {
            _sprite.update();  // Aggiorna l'animazione dello sprite
            _frameSkip++;
        }
        else _frameSkip = 0;

        updatePhysics();  // Aggiorna la fisica del cavaliere
    }
}
