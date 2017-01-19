package com.redspark.flappybirdclone.States;

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
        background = new Texture("Background.png");
        playBtn = new Texture("Start.png");
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappyBirdClone.WIDTH, FlappyBirdClone.HEIGHT);
        sb.draw(playBtn, FlappyBirdClone.WIDTH/2-(playBtn.getWidth()/2), FlappyBirdClone.HEIGHT/2 -(playBtn.getHeight()/2));
        sb.end();
    }
}