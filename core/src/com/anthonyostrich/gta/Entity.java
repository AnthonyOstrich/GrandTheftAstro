package com.anthonyostrich.gta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by anthony on 12/19/15.
 */
public class Entity {
    protected Body body;
    protected Sprite sprite;
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;


    public Entity(Texture texture){
        sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch batch){
        sprite.setCenter(this.getPosition().x, this.getPosition().y);
        sprite.setOriginCenter();
        sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
        sprite.draw(batch);

    }

    public void act(float delta){

    }

    public void addToWorld(World world, Vector2 location, float scale){
        BodyDef bodyDef = generateBodyDef(location);
        sprite.setSize(scale, scale*sprite.getHeight()/sprite.getWidth());
        FixtureDef fixtureDef = this.generateFixtureDef(scale);
        addToWorld(world, bodyDef, fixtureDef);
    }

    protected FixtureDef generateFixtureDef(float scale){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(scale, scale * sprite.getHeight()/sprite.getWidth());

        sprite.setSize(scale * 2, scale * 2*sprite.getHeight()/sprite.getWidth());
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10;
        fixtureDef.friction = 10;

        return fixtureDef;
    }

    protected BodyDef generateBodyDef(Vector2 location){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(location);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    public void addToWorld(World world, BodyDef bodyDef, FixtureDef fixtureDef){

        Body body = world.createBody(bodyDef);
        Fixture fixture = body.createFixture(fixtureDef);
        this.body = body;
        body.setFixedRotation(false);
    }
    public Body getBody(){
        return body;
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public void accelerateForwards(float amount){
        Vector2 dv = Vector2.X.cpy().scl(amount).rotateRad(body.getAngle());
        body.applyForceToCenter(dv,true);

    }

    public void accelerate(Vector2 amount){
        body.applyForceToCenter(amount, true);
    }

    public void turn(float amount){
        body.applyTorque(amount, true);
    }

    public Vector2 getRotation(){
        return Vector2.X.cpy().rotateRad(body.getAngle());
    }

    public void setPosition(Vector2 position){
        body.getTransform().setPosition(position);
    }
}
