package com.mygdx.ergame.resource;

/**
 * Enum `ResourceEnum` rappresenta tutte le risorse utilizzate nel gioco.
 * Queste risorse includono animazioni, texture di sfondo, elementi di gioco
 * e risorse audio. Ogni costante in questo enum è associata a una risorsa specifica.
 */
public enum ResourceEnum {

    // Logo
    LOGO,

    // Animazioni del cavaliere
    KN_IDLE,   // Animazione del cavaliere in stato di inattività
    KN_WALK,   // Animazione del cavaliere mentre cammina
    KN_RUN,    // Animazione del cavaliere mentre corre
    KN_JUMP,   // Animazione del cavaliere mentre salta

    // Animazioni dell' orc
    OR_IDLE,   // Animazione del cavaliere in stato di inattività
    OR_WALK,   // Animazione dell' orc mentre cammina
    OR_RUN,    // Animazione dell' orc mentre corre
    OR_SLID,   // Animazione dell' orc mentre scivola

    // Texture di sfondo e livelli
    SKY_LEVEL, // Texture per il cielo del livello
    BG_LEVEL,  // Texture di background (sfondo) del livello
    MG_LEVEL,  // Texture di midground (strato intermedio) del livello
    FG_LEVEL,  // Texture di foreground (primo piano) del livello
    G_LEVEL,   // Texture per il terreno del livello

    // Elementi di gioco
    COIN_GOLD, // Texture della moneta d'oro

    // Texture per la collisione
    BUBBLE_COLLISION, // Texture per la bolla di collisione

    // Risorse audio
    AUDIO_COIN, // Effetto sonoro quando il cavaliere raccoglie una moneta

    // Texture dati Knight
    LEFT_HEART,
    RIGHT_HEART,

    GAME_OVER,
}
