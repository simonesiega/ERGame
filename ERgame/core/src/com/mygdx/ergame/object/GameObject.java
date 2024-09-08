package com.mygdx.ergame.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.AnimatedSprite;
import com.mygdx.library.GraphicObject;

/**
 * Classe astratta che rappresenta un oggetto di gioco. Estende {@link GraphicObject}
 * e contiene logica per la gestione di posizione, velocità, accelerazione,
 * e collisioni. Gli oggetti di gioco che derivano da questa classe possono
 * implementare logiche specifiche sovrascrivendo i metodi astratti.
 */
public abstract class GameObject extends GraphicObject {

    // Sprite animato che rappresenta graficamente l'oggetto di gioco.
    protected final AnimatedSprite _sprite;

    // Vettore che rappresenta la velocità dell'oggetto lungo gli assi x e y.
    protected final Vector2 _velocity;

    // Vettore che rappresenta l'accelerazione dell'oggetto lungo gli assi x e y.
    protected final Vector2 _acceleration;

    // Vettore che rappresenta il baricentro dell'oggetto rispetto alla sua posizione.
    protected final Vector2 _barycentre;

    // Raggio dell'oggetto, utilizzato per il calcolo delle collisioni.
    protected float _radius;

    /**
     * Costruttore di default della classe GameObject. Inizializza gli attributi di base
     * e carica le risorse grafiche necessarie.
     */
    public GameObject() {
        super();

        _sprite = new AnimatedSprite();

        _velocity = new Vector2(0, 0);
        _acceleration = new Vector2(0, 0);
        _barycentre = new Vector2(0, 0);

        _radius = 0;

        /*
        // Carica la texture della risorsa per la gestione delle collisioni
        ResourceLoader.getTexture(ResourceEnum.BUBBLE_COLLISION);
         */
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        _sprite.setX(x); // Sincronizza la posizione dello sprite con la nuova posizione dell'oggetto
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        _sprite.setY(y); // Sincronizza la posizione dello sprite con la nuova posizione dell'oggetto
    }

    /**
     * Restituisce una copia della velocità corrente dell'oggetto.
     *
     * @return una nuova istanza di {@link Vector2} che rappresenta la velocità dell'oggetto.
     */
    public Vector2 getVelocity() {
        return new Vector2(_velocity);
    }

    /**
     * Imposta la velocità dell'oggetto.
     *
     * @param v un vettore contenente la velocità lungo i due assi di riferimento.
     */
    public void setVelocity(Vector2 v) {
        _velocity.x = v.x;
        _velocity.y = v.y;
    }

    /**
     * Imposta la velocità dell'oggetto.
     *
     * @param vx la velocità sull'asse x.
     * @param vy la velocità sull'asse y.
     */
    public void setVelocity(float vx, float vy){
        _velocity.x = vx;
        _velocity.y = vy;
    }

    /**
     * Restituisce una copia dell'accelerazione corrente dell'oggetto.
     *
     * @return una nuova istanza di {@link Vector2} che rappresenta l'accelerazione dell'oggetto.
     */
    public Vector2 getAcceleration() {
        return new Vector2(_acceleration);
    }

    /**
     * Imposta l'accelerazione dell'oggetto.
     *
     * @param a un vettore contenente l'accelerazione lungo i due assi di riferimento.
     */
    public void setAcceleration(Vector2 a) {
        _acceleration.x = a.x;
        _acceleration.y = a.y;
    }

    /**
     * Imposta l'accelerazione dell'oggetto.
     *
     * @param ax l'accelerazione sull'asse x.
     * @param ay l'accelerazione sull'asse y.
     */
    public void setAcceleration(float ax, float ay){
        _acceleration.x = ax;
        _acceleration.y = ay;
    }

    /**
     * Restituisce la posizione del baricentro dell'oggetto nelle coordinate del mondo.
     *
     * @return una nuova istanza di {@link Vector2} che rappresenta la posizione del baricentro nel mondo.
     */
    public Vector2 getWorldBarycentre(){
        return new Vector2(_x + _barycentre.x, _y + _barycentre.y);
    }

    /**
     * Restituisce una copia del baricentro dell'oggetto.
     *
     * @return una nuova istanza di {@link Vector2} che rappresenta il baricentro dell'oggetto.
     */
    public Vector2 getBarycentre() {
        return new Vector2(_barycentre);
    }

    /**
     * Imposta il baricentro dell'oggetto.
     *
     * @param b un vettore contenente le coordinate del baricentro rispetto alla posizione dell'oggetto.
     */
    public void setBarycentre(Vector2 b) {
        _barycentre.x = b.x;
        _barycentre.y = b.y;
    }

    /**
     * Imposta il baricentro dell'oggetto.
     *
     * @param bx la coordinata x del baricentro rispetto alla posizione dell'oggetto.
     * @param by la coordinata y del baricentro rispetto alla posizione dell'oggetto.
     */
    public void setBarycentre(float bx, float by){
        _barycentre.x = bx;
        _barycentre.y = by;
    }

    /**
     * Restituisce il raggio dell'oggetto, utilizzato per il calcolo delle collisioni.
     *
     * @return il raggio dell'oggetto.
     */
    public float getRadius() {
        return _radius;
    }

    /**
     * Imposta il raggio dell'oggetto, utilizzato per il calcolo delle collisioni.
     *
     * @param r il nuovo valore del raggio.
     */
    public void setRadius(float r) {
        _radius = r;
    }

    public int getCurrentFrame() {
        return _sprite.getCurrentFrame();
    }

    /**
     * Aggiorna la fisica dell'oggetto, applicando l'accelerazione e aggiornando la posizione
     * in base alla velocità corrente.
     */
    protected void updatePhysics() {
        _velocity.x += _acceleration.x;
        _velocity.y += _acceleration.y;

        // Aggiorna la posizione dell'oggetto basata sulla velocità
        setX(_x + _velocity.x);
        setY(_y + _velocity.y);
    }

    /**
     * Metodo astratto che deve essere implementato dalle sottoclassi per gestire
     * l'aggiornamento dello stato dell'oggetto.
     */
    public abstract void update();

    /**
     * Disegna l'oggetto utilizzando il {@link SpriteBatch} fornito.
     *
     * @param sb il {@link SpriteBatch} utilizzato per disegnare l'oggetto.
     */
    @Override
    public void draw(SpriteBatch sb) {
        _sprite.draw(sb);

        // Calcola la posizione del baricentro nel mondo
        Vector2 pb = getWorldBarycentre();

        /*
        // Disegna la bolla di collisione attorno all'oggetto
        sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE_COLLISION), pb.x - _radius, pb.y - _radius, _radius * 2, _radius * 2);
        */
    }

    /**
     * Verifica se l'oggetto corrente collide con un altro {@link GameObject}.
     *
     * @param other l'altro oggetto di gioco con cui verificare la collisione.
     * @return true se i due oggetti collidono, false altrimenti.
     */
    public boolean collidesWith(GameObject other) {
        // Calcola la distanza tra i baricentri dei due oggetti
        Vector2 pA = this.getWorldBarycentre();
        Vector2 pB = other.getWorldBarycentre();

        float distance = pA.dst(pB); // Distanza tra i due baricentri
        float sumRadius = this.getRadius() + other.getRadius(); // Somma dei raggi

        return (distance < sumRadius); // Ritorna true se la distanza è minore della somma dei raggi
    }

    /**
     * Metodo astratto che deve essere implementato dalle sottoclassi per gestire
     * la logica di gestione delle collisioni con un altro {@link GameObject}.
     *
     * @param other l'altro oggetto di gioco con cui si è verificata la collisione.
     */
    public abstract void manageCollisionWith(GameObject other);
}
