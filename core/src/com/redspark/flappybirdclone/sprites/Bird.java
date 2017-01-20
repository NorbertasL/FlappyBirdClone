package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Red on 19/01/2017.
 */

public class Bird extends GameObject {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector2 velocity;
    private Animation animation;
    private Sound flap;

    public Bird(int x, int y){
        setPosition(new Vector2(x, y));
        setTexture(new Texture("BirdAnimation.png"));
        animation = new Animation(super.getTexture(), 8, 1);
        setBounds(new Rectangle(x, y, (getTexture().getWidth()/animation.getFrameCount())-10, (getTexture().getHeight())-10));
        velocity = new Vector2(0, 0);
        flap = Gdx.audio.newSound(Gdx.files.internal("Flap.ogg"));
    }
    @Override
    public void reposition(float deltaTime){
        animation.update(deltaTime);
        if(getPosition().y > 0)
            velocity.add(0, GRAVITY);
        velocity.scl(deltaTime);
        getPosition().add(MOVEMENT*deltaTime, velocity.y);

        if(getPosition().y < 0)
            getPosition().y = 0;

        velocity.scl(1/deltaTime);

        getBounds().setPosition(getPosition().x, getPosition().y);

    }
    //@Override
    public TextureRegion getTextureR(){
        return animation.getFrame();

    }

    public void jump(){
        velocity.y = 250;
        flap.play(0.3f);
    }
    @Override
    public void dispose(){
        super.dispose();
        flap.dispose();

    }

}
