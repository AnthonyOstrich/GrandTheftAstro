package com.anthonyostrich.gta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by anthony on 12/19/15.
 */
public class Player implements InputProcessor {

    Ship controlled;

    public void setControlled(Ship toControll) {
        controlled = toControll;
    }

    public void processFrameInput(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            controlled.accelerateForwards(10);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            controlled.turn(10);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            controlled.turn(-10);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
