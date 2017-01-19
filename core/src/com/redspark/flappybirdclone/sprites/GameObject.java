package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Red on 19/01/2017.
 */

public abstract class GameObject {

    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;

    abstract public void reposition(float x);


    public boolean collision(Rectangle object){
        return object.overlaps(bounds);
    }
    public void setTexture(Texture texture){
        this.texture = texture;
    }
    public void setPosition(Vector2 position){
        this.position = position;
    }
    public void setBounds(Rectangle bounds){
        this.bounds = bounds;
    }

    public Texture getTexture(){
        return texture;
    }
    public Vector2 getPosition(){
        return position;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void dispose(){
        texture.dispose();
    }
}
