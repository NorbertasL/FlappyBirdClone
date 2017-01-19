package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Red on 19/01/2017.
 */

public class Bird {
    private static final int gravity = -15;
    private Vector2 position;
    private Vector2 velocity;
    private Texture bird;

    public Bird(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        bird = new Texture("Bird.png");
    }
    public void update(float deltaTime){

        if(position.y > 0)
            velocity.add(0, gravity);
        velocity.scl(deltaTime);
        position.add(0, velocity.y);

        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/deltaTime);

    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getBirdTexture() {
        return bird;
    }

    public void jump(){
        velocity.y = 250;
    }
}