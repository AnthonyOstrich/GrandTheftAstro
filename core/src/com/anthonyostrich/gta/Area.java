package com.anthonyostrich.gta;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by anthony on 12/19/15.
 */
public class Area implements Screen, InputProcessor {
    private World world;
    private Player player;
    private Vector2 spawnLocation;
    private ArrayList<Entity> entities;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    Ship box;

    public Area(){
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.isDrawVelocities();
        camera = new OrthographicCamera(10, 10f * Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
        camera.position.set(0,0,1);
        batch = new SpriteBatch();
        entities = new ArrayList<Entity>();
        world = new World(Vector2.Zero,true);
        box = Ship.generateFromFile(Gdx.files.internal("ships/falcon"));
        box.addToWorld(world, Vector2.Zero, 1);
        entities.add(box);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            box.accelerateForwards(20);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            box.turn(20);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            box.turn(-20);
        }

        world.step(delta, 6, 2);

        for(Entity e : entities)
            e.act(delta);

        Collections.sort(entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity entity, Entity t1) {
                if(entity.getPosition().y == t1.getPosition().y)
                    return 0;
                else return entity.getPosition().y > t1.getPosition().y ? 1 : -1;
            }
        });


        batch.begin();
        for(int i = 0; i < entities.size(); i ++){
            entities.get(i).draw(batch);
        }
        batch.end();
        debugRenderer.render(world,camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        Vector3 position = camera.position.cpy();
        camera = new OrthographicCamera(10, 10f * Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
        camera.position.set(position);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
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
        Vector3 unprojected = camera.unproject(new Vector3(screenX, screenY, 1));
        Vector2 world = new Vector2(unprojected.x, unprojected.y);
        System.out.println(world);
        box.rotateTowards(world,1);
        return true;
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
