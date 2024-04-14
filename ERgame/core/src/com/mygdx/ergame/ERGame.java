package com.mygdx.ergame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.AnimatedSprite;
import com.mygdx.library.Parameters;
import com.mygdx.library.Sprite;

public class ERGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img2;
	final int camWidth = 4;
	Camera cam;
	AnimatedSprite knight;
	boolean run = true;
	
	@Override
	public void create () {
		Parameters.setAspectRatio(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam = new OrthographicCamera(camWidth, camWidth * Parameters.getInverseAspectRatio());
		cam.position.set(camWidth * 0.5f, camWidth * Parameters.getInverseAspectRatio() * 0.5f, 0);

		batch = new SpriteBatch();

		knight = new AnimatedSprite(ResourceLoader.getAnimation(ResourceEnum.KN_RUN));
		knight.setWidth(2);
		knight.setOffsetX(1);
		knight.setOffsetY(0.30f);
		knight.setX(0);
		knight.setY(1.5f);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		if (run){
			if (Math.random() < 0.1f){
				knight.setAnimation(ResourceLoader.getAnimation(ResourceEnum.KN_JUMP));
				run = false;
			}
		} else {
			if (Math.random() < 0.1f){
				knight.setAnimation(ResourceLoader.getAnimation(ResourceEnum.KN_RUN));
				run = true;
			}
		}

		knight.update();

		cam.update();
		batch.setProjectionMatrix(cam.combined);

		batch.begin();

		knight.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
