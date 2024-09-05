package com.mygdx.ergame.object;

public abstract class Character extends GameObject{

    /**
     * Enum che definisce i vari stati del personaggio.
     */
    protected enum CHARACTER_STATE {
        IDLE,   // Personaggio fermo
        WALK,   // Personaggio che cammina
        RUN,    // Personaggio che corre
        JUMP,   // Personaggio che salta
        SLIDING // Personaggio che scrivola
    }

    // Stato attuale del personaggio
    protected CHARACTER_STATE _state;

    protected float _health;

    @Override
    public abstract void update();

    @Override
    public abstract void manageCollisionWith(GameObject other);
}
