package com.mygdx.ergame.object;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.ergame.resource.ResourceEnum;
import com.mygdx.ergame.resource.ResourceLoader;

public class Knight extends GameObject {

    enum KNIGHT_STATE {
        IDLE,
        WALK,
        RUN,
        JUMP
    }

    private final Texture[] _animIdle;
    private final Texture[] _animWalk;
    private final Texture[] _animRun;
    private final Texture[] _animJump;

    // Variabili per il cambio di stato
    private boolean _isWalking = false;
    private boolean _isRunning = false;
    private boolean _isJumping = false;

    private KNIGHT_STATE _state;

    private int _frameSkip = 0;

    public Knight() {
        super();

        _animIdle = ResourceLoader.getAnimation(ResourceEnum.KN_IDLE);
        _animWalk = ResourceLoader.getAnimation(ResourceEnum.KN_WALK);
        _animRun = ResourceLoader.getAnimation(ResourceEnum.KN_RUN);
        _animJump = ResourceLoader.getAnimation(ResourceEnum.KN_JUMP);

        _sprite.setAnimation(_animIdle);
        _sprite.setWidth(3);
        _sprite.setOffsetX(-1.5f);
        _sprite.setOffsetY(-0.5f);

        _state = KNIGHT_STATE.IDLE;

        entryWalk();
    }

    public void setWalk(boolean walk){
        _isWalking = walk;
    }

    public void setRun(boolean run){
        _isRunning = run;
    }

    public void setJump(boolean jump){
        _isJumping = jump;
    }

    public boolean isWalking(){
        return _state == KNIGHT_STATE.WALK;
    }

    public boolean isRunning(){
        return _state == KNIGHT_STATE.RUN;
    }

    public boolean isJumping(){
        return _state == KNIGHT_STATE.WALK && _velocity.y >= 0;
    }

    public boolean isFalling(){
        return _state == KNIGHT_STATE.JUMP && _velocity.y <= 0;
    }

    public void update(){
        switch (_state) {
            case WALK:
                if (_isRunning){
                    entryRun();
                    doRun();
                }
                else doWalk();
                break;

            case RUN:
                if (_isJumping){
                    entryJump();
                    doJump();
                }
                else doRun();
                break;

            case JUMP:
                if (_isRunning){
                    entryRun();
                    doRun();
                }
                else doJump();
                break;
        }
    }

    private void entryWalk(){
        _state = KNIGHT_STATE.WALK;
        _sprite.setAnimation(_animWalk);
        _frameSkip = 0;
        _velocity.x = 0.01f;

        _isWalking = false;
    }

    private void doWalk(){
        if (_frameSkip % 2 == 0) {
            _sprite.update();
            _frameSkip++;
        }
        else _frameSkip = 0;

        updatePhysics();
    }

    private void entryRun(){
        _state = KNIGHT_STATE.RUN;
        _sprite.setAnimation(_animRun);
        _frameSkip = 0;
        setVelocity(0, 0);
        setAcceleration(0, 0);

        _isRunning = false;
    }

    private void doRun(){
        if (_frameSkip % 2 == 0) {
            _sprite.update();
            _frameSkip++;
        }
        else _frameSkip = 0;
    }

    private void entryJump(){
        _state = KNIGHT_STATE.JUMP;
        _sprite.setAnimation(_animJump);
        _frameSkip = 0;
        _velocity.y = 0.12f;
        _acceleration.y = -0.005f;

        _isJumping = false;
    }

    private void doJump(){
        if (_frameSkip % 2 == 0) {
            _sprite.update();
            _frameSkip++;
        }
        else _frameSkip = 0;

        updatePhysics();
    }
}
