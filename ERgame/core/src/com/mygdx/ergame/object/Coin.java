package com.mygdx.ergame.object;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

/**
 * La classe Coin rappresenta una moneta all'interno del gioco endless runner,
 * Estende {@link GameObject} e gestisce la logica della moneta, inclusa l'animazione
 * e il suono di collisione quando il cavaliere la raccoglie.
 */
public class Coin extends GameObject {

    /** Suono riprodotto quando la moneta collide con il cavaliere. */
    private final Sound _collisionKnightSound;

    /**
     * Costruttore di default della classe Coin. Inizializza l'animazione della moneta,
     * le sue dimensioni, il baricentro e il raggio di collisione. Carica inoltre il suono associato
     * alla raccolta della moneta da parte del cavaliere.
     */
    public Coin() {
        super();

        // Imposta l'animazione della moneta caricandola dalle risorse di gioco
        _sprite.setAnimation(ResourceLoader.getAnimation(ResourceEnum.COIN_GOLD));

        // Definisce la larghezza dello sprite della moneta
        _sprite.setWidth(0.3f);
        // Centra orizzontalmente l'animazione regolando l'offset
        _sprite.setOffsetX(-0.15f);
        // Centra verticalmente l'animazione regolando l'offset
        _sprite.setOffsetY(-0.15f);

        // Imposta il baricentro della moneta al centro dell'oggetto
        setBarycentre(0, 0);
        // Definisce il raggio della sfera di collisione della moneta
        setRadius(0.15f);

        // Carica il suono che viene riprodotto quando la moneta viene raccolta
        _collisionKnightSound = ResourceLoader.getSound(ResourceEnum.AUDIO_COIN);
    }

    /**
     * Aggiorna lo stato della moneta nel ciclo di gioco. Gestisce l'aggiornamento
     * dell'animazione e le dinamiche fisiche della moneta.
     */
    public void update() {
        _sprite.update();  // Aggiorna l'animazione dello sprite della moneta
        updatePhysics();   // Aggiorna la posizione e il movimento della moneta
    }

    /**
     * Gestisce la collisione tra la moneta e un altro oggetto di gioco.
     * Quando il cavaliere raccoglie la moneta, viene riprodotto un suono di conferma.
     *
     * @param other L'oggetto con cui la moneta ha colliso, come il cavaliere.
     */
    @Override
    public void manageCollisionWith(GameObject other) {
        // Esegui il suono solo se è disponibile
        if (_collisionKnightSound != null) {
            _collisionKnightSound.stop(); // Ferma eventuali suoni precedenti
            _collisionKnightSound.play(); // Riproduci il suono della raccolta
        }
    }
}
