package com.anthonyostrich.gta;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by anthony on 12/22/15.
 */
public class Ship extends Entity {
    public Ship(Texture texture) {
        super(texture);
    }

    public void rotateTowards(Vector2 goal, float maxAngularAcceleration){
        Vector2 location = body.getPosition();
        goal.sub(location);
        goal.scl(-1);
        body.setTransform(body.getPosition(), goal.angleRad());
    }


}
