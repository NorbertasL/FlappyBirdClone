package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Red on 19/01/2017.
 */

public class Bird extends GameObject {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector2 velocity;

    public Bird(int x, int y){
        setPosition(new Vector2(x, y));
        setTexture(new Texture("Bird.png"));
        setBounds(new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight()));
        velocity = new Vector2(0, 0);
    }
    @Override
    public void reposition(float deltaTime){

        if(getPosition().y > 0)
            velocity.add(0, GRAVITY);
        velocity.scl(deltaTime);
        getPosition().add(MOVEMENT*deltaTime, velocity.y);

        if(getPosition().y < 0)
            getPosition().y = 0;

        velocity.scl(1/deltaTime);

        getBounds().setPosition(getPosition().x, getPosition().y);

    }

    public void jump(){
        velocity.y = 250;
    }

}
