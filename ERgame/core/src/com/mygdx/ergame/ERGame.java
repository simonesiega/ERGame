package com.mygdx.ergame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygdx.ergame.object.Knight;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.Parameters;

/**
 * La classe `ERGame` gestisce l'intero ciclo di vita dell'applicazione,
 * compresa la creazione, il rendering e la gestione dell'input.
 * Estende `ApplicationAdapter` e implementa `InputProcessor` per gestire l'input dell'utente.
 */
public class ERGame extends ApplicationAdapter implements InputProcessor {
	// Texture utilizzate nel gioco
	Texture _img;

	// Batch per disegnare gli sprite
	SpriteBatch _batch;

	// Dimensione della camera in larghezza
	final int _camWidth = 4;

	// Camera per gestire la visualizzazione della scena
	Camera _cam;

	// Oggetto rappresentante il cavaliere
	Knight _knight;

	// Oggetto rappresentante il livello di gioco
	Level _level;

	// Variabile di stato per controllare il movimento
	boolean _walk = true;

	// Altezza del piano del livello
	float _floorLevel = 0.2f;

	/**
	 * Metodo chiamato all'avvio dell'applicazione.
	 * Inizializza la camera, lo sprite batch, il cavaliere e il livello.
	 */
	@Override
	public void create () {
		// Imposta il rapporto d'aspetto basato sulle dimensioni della finestra di gioco
		Parameters.setAspectRatio(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Inizializza la camera ortografica con larghezza definita
		_cam = new OrthographicCamera(_camWidth, _camWidth * Parameters.getInverseAspectRatio());
		_cam.position.set(_camWidth * 0.5f, _camWidth * Parameters.getInverseAspectRatio() * 0.5f, 0);

		// Inizializza lo sprite batch
		_batch = new SpriteBatch();

		// Crea e posiziona il cavaliere
		_knight = new Knight();
		_knight.setX(-0.5f);
		_knight.setY(_floorLevel);

		// Crea il livello e assegna il cavaliere al livello
		_level = new Level("Lvl 00", _camWidth * Parameters.getInverseAspectRatio());
		_level.setKnight(_knight);

		// Imposta l'input processor per gestire l'input dell'utente
		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Metodo di rendering chiamato ad ogni frame.
	 * Gestisce l'aggiornamento del gioco e il disegno della scena.
	 */
	@Override
	public void render () {
		// Pulisce lo schermo con un colore rosso
		ScreenUtils.clear(1, 0, 0, 1);

		// Controlla lo stato del cavaliere per decidere se farlo correre o saltare
		if (_knight.isWalking() && (_knight.getX() >= _camWidth * 0.25f)){
			_knight.setRun(true);
		}
		else if (_knight.isFalling() && (_knight.getY() <= _floorLevel)){
			_knight.setRun(true);
			_knight.setY(_floorLevel);
		}

		// Aggiorna lo stato del livello
		_level.update();

		// Aggiorna e applica la matrice di proiezione della camera
		_cam.update();
		_batch.setProjectionMatrix(_cam.combined);

		// Inizia a disegnare gli oggetti sulla scena
		_batch.begin();

		// Disegna il livello (incluso il cavaliere)
		_level.draw(_batch);

		// Fine del disegno
		_batch.end();
	}

	/**
	 * Metodo chiamato quando l'applicazione viene chiusa per liberare risorse.
	 */
	@Override
	public void dispose () {
		_batch.dispose();
		_img.dispose();
	}

	/**
	 * Gestisce l'input da tastiera (quando un tasto viene premuto).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean keyDown(int i) {
		return false;
	}

	/**
	 * Gestisce l'input da tastiera (quando un tasto viene rilasciato).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean keyUp(int i) {
		return false;
	}

	/**
	 * Gestisce l'input da tastiera (quando un carattere viene digitato).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	/**
	 * Gestisce l'input tattile (quando viene rilevato un tocco sullo schermo).
	 * Se il cavaliere sta correndo, inizia a saltare.
	 */
	@Override
	public boolean touchDown(int i, int i1, int i2, int i3) {
		if (_knight.isRunning()) _knight.setJump(true);
		return true;
	}

	/**
	 * Gestisce l'input tattile (quando il tocco viene rilasciato).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean touchUp(int i, int i1, int i2, int i3) {
		return false;
	}

	/**
	 * Gestisce l'input tattile (quando il tocco viene cancellato).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean touchCancelled(int i, int i1, int i2, int i3) {
		return false;
	}

	/**
	 * Gestisce l'input tattile (quando il tocco viene trascinato sullo schermo).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean touchDragged(int i, int i1, int i2) {
		return false;
	}

	/**
	 * Gestisce l'input del mouse (quando il mouse viene spostato).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean mouseMoved(int i, int i1) {
		return false;
	}

	/**
	 * Gestisce l'input della rotella del mouse (quando viene fatta scorrere).
	 * Non utilizzato in questo contesto.
	 */
	@Override
	public boolean scrolled(float v, float v1) {
		return false;
	}
}
