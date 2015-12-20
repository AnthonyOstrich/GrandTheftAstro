package com.anthonyostrich.gta;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by anthony on 12/19/15.
 */
public class Area implements Screen{
    private World world;
    private Player player;
    private Vector2 spawnLocation;
    private ArrayList<Entity> entities;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    public Area(){
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(10, 10f * Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
        camera.position.set(0,0,1);
        batch = new SpriteBatch();
        entities = new ArrayList<Entity>();
        world = new World(new Vector2(0,-9.8f),true);
        Texture boxTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        Entity box = new Entity(boxTexture);
        box.addToWorld(world, Vector2.Zero);
        entities.add(box);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 6, 2);

        System.out.println(1/delta);
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
}
