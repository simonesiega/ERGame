package com.mygdx.ergame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.ergame.object.Coin;
import com.mygdx.ergame.object.GameObject;
import com.mygdx.ergame.object.Knight;
import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;
import com.mygdx.library.Drawable;
import com.mygdx.library.Parameters;

public class Level implements Drawable {
    private final String _name;

    private float _length;
    private float _speed;

    private float _fx;
    private float _mx;
    private float _bx;

    private final float _width;
    private final float _height;

    private final Texture _bgPicture;
    private final Texture _fgPicture;
    private final Texture _gPicture;
    private final Texture _mgPicture;
    private final Texture _skyPicture;

    private Knight _knight;

    private GameObject[] _object;

    public Level(String name, float height) {
        super();

        this._name = name;
        this._length = 0.0f;
        this._speed = -0.03f;

        this._fx = 0.0f;
        this._mx = 0.0f;
        this._bx = 0.0f;

        this._height = height;
        this._width = _height * Parameters.getAspectRatio();

        this._bgPicture = ResourceLoader.getTexture(ResourceEnum.BG_LEVEL);
        this._fgPicture = ResourceLoader.getTexture(ResourceEnum.FG_LEVEL);
        this._gPicture = ResourceLoader.getTexture(ResourceEnum.G_LEVEL);
        this._mgPicture = ResourceLoader.getTexture(ResourceEnum.MG_LEVEL);
        this._skyPicture = ResourceLoader.getTexture(ResourceEnum.SKY_LEVEL);

        this._knight = null;

        this._object = new GameObject[100];

        _object[0] = new Coin();
        _object[0].setX(4);
        _object[0].setY(0.5f);
        _object[0].setVelocity(this._speed, 0);
    }

    public float getSpeed() {
        return this._speed;
    }

    public void setSpeed(float speed) {
        this._speed = speed;
    }

    public String getName() {
        return this._name;
    }

    public Knight getKnight() {
        return this._knight;
    }

    public void setKnight(Knight knight) {
        this._knight = knight;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(_skyPicture, 0, 0, _width, _height);
        sb.draw(_bgPicture, _bx, 0, _width, _height);
        sb.draw(_bgPicture, _bx + _width, 0, _width, _height);
        sb.draw(_mgPicture, _mx, 0, _width, _height);
        sb.draw(_mgPicture, _mx + _width, 0, _width, _height);
        sb.draw(_fgPicture, _fx, 0, _width, _height);
        sb.draw(_fgPicture, _fx + _width, 0, _width, _height);

        if (!_knight.isWalking()) {
            for (GameObject object : _object) {
                if (object != null) object.draw(sb);
            }
        }

        _knight.draw(sb);

        sb.draw(_gPicture, _fx, 0, _width, _height);
        sb.draw(_gPicture, _fx + _width, 0, _width, _height);
    }

    public void update() {
        _knight.update();

        if (!_knight.isWalking()) {

            for (GameObject object : _object) {
                if (object != null) object.update();
            }

            _fx += _speed;

            if (_fx <= -_width) _fx = _fx + _width;

            _mx += _speed * 0.6f;
            if (_mx <= -_width) _mx = _mx + _width;

            _bx += _speed * 0.25f;
            if (_bx <= -_width) _bx = _bx + _width;
        }
    }
}
