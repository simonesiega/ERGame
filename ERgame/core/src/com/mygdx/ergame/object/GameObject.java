package com.mygdx.ergame.object;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.library.AnimatedSprite;
import com.mygdx.library.GraphicObject;

public class GameObject extends GraphicObject {
    protected final AnimatedSprite _sprite;

    protected final Vector2 _velocity;    // Velocity sull'asse x e y
    protected final Vector2 _barycentre;  // Baricentro rispetto alla posizione dell oggetto

    protected float _radius;

    public GameObject() {
        super();

        _sprite = new AnimatedSprite();

        _velocity = new Vector2(0, 0);
        _barycentre = new Vector2(0, 0);

        _radius = 0;
    }

    /**
     * Restituisce una copia della velocity del cavaliere nel momento in cui ho fatto richiesta
     * return _velocity restituisce l istanza della velocity del cavaliere che puó essere modificata in qualunque momento
     *
     * @return foto velocity del cavaliere
     */
    public Vector2 getVelocity() {
        return new Vector2(_velocity);
    }

    /**
     * Imposta la velocitá dell oggetto
     *
     * @param v un vettore contenente la velocitá lungo i due assi di riferimento
     */
    public void setVelocity(Vector2 v) {
        _velocity.x = v.x;
        _velocity.y = v.y;
    }

    /**
     * Imposta la velocitá dell oggetto
     *
     * @param vx la velocitá sull asse x
     * @param vy la velocitá sull asse y
     */
    public void setVelocity(float vx, float vy){
        _velocity.x = vx;
        _velocity.y = vy;
    }

    /**
     * Restituisce una copia della velocity del cavaliere nel momento in cui ho fatto richiesta
     * return _velocity restituisce l istanza della velocity del cavaliere che puó essere modificata in qualunque momento
     * @return foto velocity del cavaliere
     */
    public Vector2 getBarycentre() {
        return new Vector2(_barycentre);
    }

    /**
     * Imposta la velocitá dell oggetto
     *
     * @param b un vettore contenente la velocitá lungo i due assi di riferimento
     */
    public void setBarycentre(Vector2 b) {
        _barycentre.x = b.x;
        _barycentre.y = b.y;
    }

    /**
     * Imposta la velocitá dell oggetto
     *
     * @param bx la velocitá sull asse x
     * @param by la velocitá sull asse y
     */
    public void setBarycentre(float bx, float by){
        _barycentre.x = bx;
        _barycentre.y = by;
    }

    public float getRadius() {
        return _radius;
    }

    public void setRadius(float r) {
        _radius = r;
    }

    protected void updatePhysics() {
        setX(_x + _velocity.x);
        setY(_y + _velocity.y);
    }
}
