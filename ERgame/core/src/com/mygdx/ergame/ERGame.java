package com.mygdx.ergame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygdx.library.Parameters;

public class ERGame extends ApplicationAdapter implements InputProcessor {
	Texture _img, img2;

	SpriteBatch _batch;
	final int _camWidth = 4;
	Camera _cam;

	Knight _knight;
	boolean _walk = true;
	
	@Override
	public void create () {
		Parameters.setAspectRatio(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		_cam = new OrthographicCamera(_camWidth, _camWidth * Parameters.getInverseAspectRatio());
		_cam.position.set(_camWidth * 0.5f, _camWidth * Parameters.getInverseAspectRatio() * 0.5f, 0);

		_batch = new SpriteBatch();

		_knight = new Knight();
		_knight.setX(1);
		_knight.setY(0.3f);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		_knight.update();

		_cam.update();
		_batch.setProjectionMatrix(_cam.combined);

		_batch.begin();

		_knight.draw(_batch);

		_batch.end();
	}
	
	@Override
	public void dispose () {
		_batch.dispose();
		_img.dispose();
	}

	@Override
	public boolean keyDown(int i) {
		return false;
	}

	@Override
	public boolean keyUp(int i) {
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	@Override
	public boolean touchDown(int i, int i1, int i2, int i3) {
		_walk = !_walk;
		_knight.setWalk(_walk);
		return true;
	}

	@Override
	public boolean touchUp(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchCancelled(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i1, int i2) {
		return false;
	}

	@Override
	public boolean mouseMoved(int i, int i1) {
		return false;
	}

	@Override
	public boolean scrolled(float v, float v1) {
		return false;
	}
}
