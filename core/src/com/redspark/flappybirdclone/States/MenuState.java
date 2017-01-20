package com.redspark.flappybirdclone.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redspark.flappybirdclone.FlappyBirdClone;

/**
 * Created by Red on 19/01/2017.
 */

public class MenuState extends  State{
    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager gsm){
        super(gsm);
        camera.setToOrtho(false, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);
        background = new Texture("Background.png");
        playBtn = new Texture("Start.png");
    }


    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState((gsm)));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth()/2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
