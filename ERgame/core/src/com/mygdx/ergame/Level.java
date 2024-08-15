package com.mygdx.ergame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.ergame.object.Coin;
import com.mygdx.ergame.object.GameObject;
import com.mygdx.ergame.object.Knight;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.Drawable;
import com.mygdx.library.Parameters;

/**
 * La classe `Level` rappresenta un livello di gioco, contenente un cavaliere, oggetti e texture di sfondo.
 * Implementa l'interfaccia `Drawable` per permettere il disegno del livello e dei suoi componenti.
 */
public class Level implements Drawable {
    // Nome del livello
    private final String _name;

    // Lunghezza del livello e velocità di scorrimento
    private float _length;
    private float _speed;

    // Coordinate per la gestione del movimento degli sfondi
    private float _fx;
    private float _mx;
    private float _bx;

    // Larghezza e altezza del livello
    private final float _width;
    private final float _height;

    // Texture per i vari livelli di sfondo e il cielo
    private final Texture _bgPicture;
    private final Texture _fgPicture;
    private final Texture _gPicture;
    private final Texture _mgPicture;
    private final Texture _skyPicture;

    // Riferimento al cavaliere presente nel livello
    private Knight _knight;

    // Oggetti presenti nel livello, come le monete
    private final GameObject[] _objects;

    /**
     * Costruttore della classe `Level`.
     * Inizializza il livello con il nome, altezza, velocità e carica le texture e gli oggetti.
     *
     * @param name Nome del livello.
     * @param height Altezza del livello.
     */
    public Level(String name, float height) {
        super();

        this._name = name;
        this._length = 0.0f;
        this._speed = -0.03f;

        this._fx = 0.0f;
        this._mx = 0.0f;
        this._bx = 0.0f;

        this._height = height;
        this._width = _height * Parameters.getAspectRatio();

        // Caricamento delle texture di sfondo
        this._bgPicture = ResourceLoader.getTexture(ResourceEnum.BG_LEVEL);
        this._fgPicture = ResourceLoader.getTexture(ResourceEnum.FG_LEVEL);
        this._gPicture = ResourceLoader.getTexture(ResourceEnum.G_LEVEL);
        this._mgPicture = ResourceLoader.getTexture(ResourceEnum.MG_LEVEL);
        this._skyPicture = ResourceLoader.getTexture(ResourceEnum.SKY_LEVEL);

        this._knight = null;

        // Inizializzazione degli oggetti nel livello (ad es. monete)
        this._objects = new GameObject[10];

        for (int i = 0; i < _objects.length; i++) {
            _objects[i] = new Coin();
            _objects[i].setX((float) (4 + Math.random() * 3 * i));
            _objects[i].setY(0.5f);
            _objects[i].setVelocity(this._speed, 0);
        }
    }

    /**
     * Restituisce la velocità corrente del livello.
     *
     * @return Velocità del livello.
     */
    public float getSpeed() {
        return this._speed;
    }

    /**
     * Imposta la velocità del livello.
     *
     * @param speed Nuova velocità del livello.
     */
    public void setSpeed(float speed) {
        this._speed = speed;
    }

    /**
     * Restituisce il nome del livello.
     *
     * @return Nome del livello.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Restituisce il cavaliere associato al livello.
     *
     * @return Cavaliere del livello.
     */
    public Knight getKnight() {
        return this._knight;
    }

    /**
     * Imposta il cavaliere nel livello.
     *
     * @param knight Cavaliere da associare al livello.
     */
    public void setKnight(Knight knight) {
        this._knight = knight;
    }

    /**
     * Metodo per disegnare il livello e tutti i suoi componenti (sfondi, oggetti e cavaliere).
     *
     * @param sb `SpriteBatch` utilizzato per disegnare gli elementi sullo schermo.
     */
    @Override
    public void draw(SpriteBatch sb) {
        // Disegna lo sfondo del cielo
        sb.draw(_skyPicture, 0, 0, _width, _height);

        // Disegna i livelli di sfondo con movimento
        sb.draw(_bgPicture, _bx, 0, _width, _height);
        sb.draw(_bgPicture, _bx + _width, 0, _width, _height);
        sb.draw(_mgPicture, _mx, 0, _width, _height);
        sb.draw(_mgPicture, _mx + _width, 0, _width, _height);
        sb.draw(_fgPicture, _fx, 0, _width, _height);
        sb.draw(_fgPicture, _fx + _width, 0, _width, _height);

        // Disegna gli oggetti nel livello se il cavaliere non sta camminando
        if (!_knight.isWalking()) {
            for (GameObject object : _objects) {
                if (object != null) object.draw(sb);
            }
        }

        // Disegna il cavaliere
        _knight.draw(sb);

        // Disegna il terreno in primo piano
        sb.draw(_gPicture, _fx, 0, _width, _height);
        sb.draw(_gPicture, _fx + _width, 0, _width, _height);
    }

    /**
     * Metodo per aggiornare lo stato del livello, incluso il cavaliere e gli oggetti.
     */
    public void update() {
        _knight.update();

        if (!_knight.isWalking()) {
            // Aggiorna la posizione degli oggetti e gestisce le collisioni
            for (int i = 0; i < _objects.length; i++) {
                GameObject object = _objects[i];

                if (object != null) {
                    object.update();

                    // Gestisce le collisioni tra il cavaliere e gli oggetti
                    if (_knight.collidesWith(object)) {
                        _knight.manageCollisionWith(object);
                        object.manageCollisionWith(_knight);
                        _objects[i] = null;
                    }
                }
            }

            // Aggiorna la posizione degli sfondi con scorrimento
            _fx += _speed;
            if (_fx <= -_width) _fx = _fx + _width;

            _mx += _speed * 0.6f;
            if (_mx <= -_width) _mx = _mx + _width;

            _bx += _speed * 0.25f;
            if (_bx <= -_width) _bx = _bx + _width;
        }
    }
}
