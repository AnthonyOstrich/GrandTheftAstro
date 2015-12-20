package com.anthonyostrich.gta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by anthony on 12/19/15.
 */
public class Entity {
    private Body body;
    private Sprite sprite;

    public Entity(Texture texture){
        sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch batch){
        sprite.setCenter(this.getPosition().x, this.getPosition().y);
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
        sprite.draw(batch);

    }

    public void addToWorld(World world, Vector2 location){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(location);
        bodyDef.type = BodyDef.BodyType.DynamicBody;


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, sprite.getHeight()/sprite.getWidth());

        sprite.setSize(2, 2*sprite.getHeight()/sprite.getWidth());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        addToWorld(world, bodyDef, fixtureDef);

    }

    public void addToWorld(World world, BodyDef bodyDef, FixtureDef fixtureDef){

        Body body = world.createBody(bodyDef);
        Fixture fixture = body.createFixture(fixtureDef);
        this.body = body;
    }
    public Body getBody(){
        return body;
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }
}
