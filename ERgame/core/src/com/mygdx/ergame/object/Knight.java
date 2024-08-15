package com.mygdx.ergame.object;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

/**
 * La classe Knight rappresenta il cavaliere controllato dal giocatore.
 * Gestisce gli stati di animazione e il movimento del cavaliere, inclusi
 * camminata, corsa e salto. Estende {@link GameObject}.
 */
public class Knight extends GameObject {

    /**
     * Enum che definisce i vari stati del cavaliere.
     */
    enum KNIGHT_STATE {
        IDLE,   // Cavaliere fermo
        WALK,   // Cavaliere che cammina
        RUN,    // Cavaliere che corre
        JUMP    // Cavaliere che salta
    }

    // Array di texture per le animazioni del cavaliere
    private final Texture[] _animIdle;
    private final Texture[] _animWalk;
    private final Texture[] _animRun;
    private final Texture[] _animJump;

    // Variabili per gestire i cambiamenti di stato
    private boolean _isWalking = false;
    private boolean _isRunning = false;
    private boolean _isJumping = false;

    // Stato attuale del cavaliere
    private KNIGHT_STATE _state;

    // Contatore per gestire la frequenza dei frame dell'animazione
    private int _frameSkip = 0;

    /**
     * Costruttore di default della classe Knight. Inizializza le animazioni,
     * lo sprite, il raggio di collisione, il baricentro e lo stato iniziale.
     */
    public Knight() {
        super();

        // Carica le animazioni dalle risorse
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

        // Stato iniziale del cavaliere
        _state = KNIGHT_STATE.IDLE;

        // Inizia lo stato di camminata
        entryWalk();
    }

    /**
     * Imposta lo stato di camminata del cavaliere.
     *
     * @param walk true per attivare lo stato di camminata, false altrimenti
     */
    public void setWalk(boolean walk){
        _isWalking = walk;
    }

    /**
     * Imposta lo stato di corsa del cavaliere.
     *
     * @param run true per attivare lo stato di corsa, false altrimenti
     */
    public void setRun(boolean run){
        _isRunning = run;
    }

    /**
     * Imposta lo stato di salto del cavaliere.
     *
     * @param jump true per attivare lo stato di salto, false altrimenti
     */
    public void setJump(boolean jump){
        _isJumping = jump;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di camminata.
     *
     * @return true se il cavaliere sta camminando, false altrimenti
     */
    public boolean isWalking(){
        return _state == KNIGHT_STATE.WALK;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di corsa.
     *
     * @return true se il cavaliere sta correndo, false altrimenti
     */
    public boolean isRunning(){
        return _state == KNIGHT_STATE.RUN;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di salto verso l'alto.
     *
     * @return true se il cavaliere sta saltando verso l'alto, false altrimenti
     */
    public boolean isJumping(){
        return _state == KNIGHT_STATE.WALK && _velocity.y >= 0;
    }

    /**
     * Verifica se il cavaliere è attualmente nello stato di caduta.
     *
     * @return true se il cavaliere sta cadendo, false altrimenti
     */
    public boolean isFalling(){
        return _state == KNIGHT_STATE.JUMP && _velocity.y <= 0;
    }

    /**
     * Aggiorna lo stato del cavaliere in base alle variabili di stato e
     * gestisce la transizione tra camminata, corsa e salto.
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
     * @param other l'altro oggetto di gioco con cui il cavaliere ha colliso.
     */
    @Override
    public void manageCollisionWith(GameObject other) {
        if (other instanceof Coin) System.out.println("Ho trovato una moneta");
    }

    /**
     * Inizializza lo stato di camminata del cavaliere, impostando
     * l'animazione e la velocità.
     */
    private void entryWalk(){
        _state = KNIGHT_STATE.WALK;  // Cambia lo stato a camminata
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
        _state = KNIGHT_STATE.RUN;  // Cambia lo stato a corsa
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
        _state = KNIGHT_STATE.JUMP;  // Cambia lo stato a salto
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
