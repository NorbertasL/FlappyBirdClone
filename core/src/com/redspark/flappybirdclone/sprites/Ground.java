package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Red on 19/01/2017.
 */

public class Ground extends GameObject{

    public static final int GROUND_Y_OFFSET = -30;

    public Ground(Vector2 position){
        setTexture(new Texture("Ground.png"));
        setPosition(position);
        setBounds(new Rectangle(getPosition().x, getPosition().y, getTexture().getWidth(), getTexture().getHeight()));
    }

    @Override
    public void reposition(float x) {
        getPosition().set(x, GROUND_Y_OFFSET);
        getBounds().setPosition(getPosition().x, getPosition().y);


    }
}
