package com.redspark.flappybirdclone.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redspark.flappybirdclone.FlappyBirdClone;
import com.redspark.flappybirdclone.sprites.Bird;

/**
 * Created by Red on 19/01/2017.
 */

public class PlayState extends State {
    private Bird bird;
    private Texture background;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 250);
        camera.setToOrtho(false, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);
        background = new Texture("Background.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(bird.getBirdTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();


    }

    @Override
    public void dispose() {
    }
}
