package com.mygdx.ergame.object;

/**
 * La classe astratta {@code Character} rappresenta un oggetto del gioco che è un personaggio.
 * Questa classe serve come base per tutti gli oggetti che rappresentano personaggi nel gioco,
 * ereditando le funzionalità generali da {@link GameObject}.
 *
 * Ogni personaggio ha uno stato, definito dall'enum {@link CHARACTER_STATE},
 * e un attributo salute, che indica il suo stato di vitalità.
 */
public abstract class Character extends GameObject {

    /**
     * Enum che definisce i vari stati in cui un personaggio può trovarsi durante il gioco.
     * Questi stati influenzano il comportamento e le animazioni del personaggio.
     */
    protected enum CHARACTER_STATE {
        /** Stato in cui il personaggio è fermo e inattivo. */
        IDLE,
        /** Stato in cui il personaggio sta camminando. */
        WALK,
        /** Stato in cui il personaggio sta correndo. */
        RUN,
        /** Stato in cui il personaggio sta saltando. */
        JUMP,
        /** Stato in cui il personaggio sta scivolando. */
        SLIDING,
        /** Stato in cui il personaggio muore. */
        DIE,
        /** Stato in cui il personaggio subisce un danno. */
        HURT
    }

    /**
     * Stato attuale del personaggio, indicato dall'enum {@link CHARACTER_STATE}.
     * Determina il comportamento e l'animazione del personaggio.
     */
    protected CHARACTER_STATE _state;

    /**
     * Livello di salute attuale del personaggio. Un valore che rappresenta
     * la quantità di danni che il personaggio può subire prima di morire.
     */
    protected float _health;

    /**
     * Metodo astratto che deve essere implementato dalle sottoclassi per aggiornare
     * lo stato del personaggio in base alla logica di gioco.
     * Questo metodo viene chiamato a ogni aggiornamento del ciclo di gioco.
     */
    @Override
    public abstract void update();

    /**
     * Metodo astratto che deve essere implementato dalle sottoclassi per gestire
     * le collisioni tra il personaggio e altri oggetti del gioco.
     *
     * @param other L'oggetto con cui il personaggio è entrato in collisione.
     */
    @Override
    public abstract void manageCollisionWith(GameObject other);
}
