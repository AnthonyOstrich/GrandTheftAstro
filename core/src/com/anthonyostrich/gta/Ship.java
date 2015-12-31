package com.anthonyostrich.gta;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by anthony on 12/22/15.
 */
public class Ship extends Entity {

    private FixtureDef fixtureDef;
    private BodyDef bodyDef;

    public Ship(Texture texture) {
        super(texture);
    }

    public void rotateTowards(Vector2 goal, float maxAngularAcceleration){
        Vector2 location = body.getPosition();
        goal.sub(location);
        body.setTransform(body.getPosition(), goal.angleRad());
    }

    public static Ship generateFromFile(FileHandle file){
        String text = file.readString();
        return generateFromText(text);
    }

    public static Ship generateFromText(String text){
        String[] lines = text.split("\n");
        String name = "";
        if(lines.length >= 1) {
            name = lines[0];
        }
        Texture shipTexture = new Texture(Gdx.files.internal("ships/" + name));
        Ship ship = new Ship(shipTexture);
        return ship;

    }


}
