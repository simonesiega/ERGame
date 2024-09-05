package com.mygdx.ergame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.ergame.object.Coin;
import com.mygdx.ergame.object.Knight;
import com.mygdx.ergame.object.Orc;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.Drawable;
import com.mygdx.library.Parameters;

import java.util.ArrayList;

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

    private final Texture _leftHeartKnightPicture;
    private final Texture _rightHeartKnightPicture;
    private final Texture _coinPicture;
    private final BitmapFont _fontNumberCoin;

    // Misure for the bar
    private final float _dimensionXHeartBar = 0.075f;
    private final float _dimensionYHeartBar = 0.15f;
    private final float _healthBarXOffset = 0.02f;
    private final float _healthBarYOffset = 2.78f;
    float _dimensionCoinBar = 0.15f;
    float _coinNumberXOffset = 0.03f;
    float _coinNumberYOffset = 2.78f - _dimensionYHeartBar - 0.02f;

    // Riferimento al cavaliere presente nel livello
    private Knight _knight;

    // Oggetti presenti nel livello, come le monete
    private final ArrayList<Coin> _coins;
    private final ArrayList<Orc> _orcs;

    private int _numberCoin;

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

        this._leftHeartKnightPicture = ResourceLoader.getTexture(ResourceEnum.LEFT_HEART);
        this._rightHeartKnightPicture = ResourceLoader.getTexture(ResourceEnum.RIGHT_HEART);
        this._coinPicture = ResourceLoader.getTexture(ResourceEnum.COIN_GOLD);
        this._fontNumberCoin = new BitmapFont();

        this._knight = null;

        this._numberCoin = 0;

        // Inizializzazione degli oggetti nel livello (ad es. monete)
        this._coins = new ArrayList<>();
        this._orcs = new ArrayList<>();

        initObjGame();
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

    private void initObjGame(){
        // Riempio arraylist coins
        for (int i = 0; i < 10; i++) {
            createNewCoin();
        }

        for (int i = 0; i < 5; i++) {
            createNewOrc();
        }
    }

    private void createNewCoin() {
        Coin coin = new Coin();
        coin.setX((float) (4 + Math.random() * 15));
        coin.setY((float) (0.5 + Math.random() * 2));
        coin.setVelocity(this._speed, 0);
        _coins.add(coin);
    }

    private void createNewOrc() {
        Orc orc = new Orc();
        orc.setX((float) (10 + Math.random() * 15));
        orc.setY(0.2f);
        orc.setVelocity(this._speed, 0);
        _orcs.add(orc);
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
            for (Coin coin : _coins) {
                if (coin != null) coin.draw(sb);
            }

            for (Orc orc : _orcs) {
                if (orc != null) orc.draw(sb);
            }

            drawHelthBar(sb);
            drawCoinBar(sb);
        }

        // Disegna il cavaliere
        _knight.draw(sb);

        // Disegna il terreno in primo piano
        sb.draw(_gPicture, _fx, 0, _width, _height);
        sb.draw(_gPicture, _fx + _width, 0, _width, _height);
    }

    private void drawHelthBar(SpriteBatch sb) {
        // System.out.println("Drawing health bar at: " + xOffset + ", " + yOffset);

        int numberHeart = (int) (_knight.getHealth() / (_knight.getMaxHealth()) * 20);
        // System.out.println(numberHeart);

        for (int i = 0; i < numberHeart; i++) {
            if (i % 2 == 1) sb.draw(_rightHeartKnightPicture, _healthBarXOffset + (_dimensionXHeartBar * i), _healthBarYOffset, _dimensionXHeartBar, _dimensionYHeartBar);
            else sb.draw(_leftHeartKnightPicture, _healthBarXOffset + (_dimensionXHeartBar * i), _healthBarYOffset, _dimensionXHeartBar, _dimensionYHeartBar);
        }
    }

    private void drawCoinBar(SpriteBatch sb) {
        sb.draw(_coinPicture, _coinNumberXOffset, _coinNumberYOffset, _dimensionCoinBar, _dimensionCoinBar);
        _fontNumberCoin.setColor(Color.BLACK);
        _fontNumberCoin.draw(sb, " Coin: " + _numberCoin, _coinNumberXOffset, _coinNumberYOffset + _dimensionCoinBar + 0.02f);
    }

    /**
     * Metodo per aggiornare lo stato del livello, incluso il cavaliere e gli oggetti.
     */
    public void update() {
        _knight.update();

        if (!_knight.isWalking()) {
            // Aggiorna la posizione degli oggetti e gestisce le collisioni
            // Coin
            for (int i = 0; i < _coins.size(); i++) {
                Coin coinTmp = _coins.get(i);

                if (coinTmp != null) {
                    coinTmp.update();

                    // Gestisce le collisioni tra il cavaliere e gli oggetti
                    if (_knight.collidesWith(coinTmp)) {
                        _knight.manageCollisionWith(coinTmp);
                        coinTmp.manageCollisionWith(_knight);

                        collectedCoin();
                        refreshCoin(i);
                    }
                }
            }

            for (int i = 0; i < _orcs.size(); i++) {
                Orc orcTmp = _orcs.get(i);

                if (orcTmp != null) {
                    orcTmp.update();

                    for (int j = 0; j < _coins.size(); j++) {
                        Coin coinTmp = _coins.get(j);

                        if (coinTmp != null) {

                            if (orcTmp.collidesWith(coinTmp)) {
                                refreshCoin(j);
                            }
                        }
                    }

                    // Gestisce le collisioni tra il cavaliere e gli oggetti
                    if (_knight.collidesWith(orcTmp)) {
                        _knight.manageCollisionWith(orcTmp);
                        orcTmp.manageCollisionWith(_knight);

                        refreshOrc(i);
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

    private void collectedCoin(){
        _numberCoin++;
    }

    private void refreshCoin(int index){
        _coins.remove(index);
        createNewCoin();
    }

    private void refreshOrc(int index){
        _orcs.remove(index);
        createNewOrc();
    }
}
