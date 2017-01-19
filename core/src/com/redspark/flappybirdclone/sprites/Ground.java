package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Red on 19/01/2017.
 */

public class Ground extends GameObject{

    @Override
    public void reposition(float deltaTime) {
       // setPosition(new Vector2(x, y));
        setTexture(new Texture("Ground.png"));
       // setBounds(new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight()));

    }
}
