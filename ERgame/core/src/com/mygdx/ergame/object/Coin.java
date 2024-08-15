package com.mygdx.ergame.object;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

/**
 * La classe Coin rappresenta una moneta nel gioco, che può interagire con altri oggetti,
 * come il cavaliere. Estende {@link GameObject} e implementa la logica specifica
 * per il comportamento della moneta, inclusa l'animazione e il suono della collisione.
 */
public class Coin extends GameObject {

    // Suono riprodotto quando la moneta collide con il cavaliere
    private final Sound _collisionKnightSound;

    /**
     * Costruttore di default della classe Coin. Inizializza l'animazione, le dimensioni,
     * il baricentro, il raggio di collisione e il suono associato alla collisione.
     */
    public Coin(){
        super();

        // Imposta l'animazione della moneta con l'animazione caricata dalle risorse
        _sprite.setAnimation(ResourceLoader.getAnimation(ResourceEnum.COIN_GOLD));

        // Imposta la larghezza dello sprite della moneta
        _sprite.setWidth(0.3f);
        // Imposta l'offset orizzontale per centrare l'animazione
        _sprite.setOffsetX(-0.15f);
        // Imposta l'offset verticale per centrare l'animazione
        _sprite.setOffsetY(-0.15f);

        // Imposta il baricentro della moneta nel centro dell'oggetto
        setBarycentre(0, 0);
        // Imposta il raggio della sfera di collisione della moneta
        setRadius(0.15f);

        // Carica il suono associato alla collisione della moneta con il cavaliere
        _collisionKnightSound = ResourceLoader.getSound(ResourceEnum.AUDIO_COIN);
    }

    /**
     * Aggiorna lo stato della moneta, inclusa l'animazione e la fisica.
     */
    public void update(){
        _sprite.update();  // Aggiorna l'animazione dello sprite
        updatePhysics();   // Aggiorna la posizione e la velocità della moneta
    }

    /**
     * Gestisce la collisione della moneta con un altro oggetto di gioco.
     * Quando si verifica una collisione, viene riprodotto il suono della moneta.
     *
     * @param other l'altro oggetto di gioco con cui la moneta ha colliso.
     */
    @Override
    public void manageCollisionWith(GameObject other) {
        _collisionKnightSound.play(); // Riproduce il suono della collisione
    }
}
