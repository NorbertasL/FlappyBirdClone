package com.redspark.flappybirdclone.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.redspark.flappybirdclone.FlappyBirdClone;
import com.redspark.flappybirdclone.sprites.Bird;
import com.redspark.flappybirdclone.sprites.Pipe;

/**
 * Created by Red on 19/01/2017.
 */

public class PlayState extends State {

    private static final int PIPE_SPACING = 125;
    private static final int PIPE_COUNT = 4;

    private Bird bird;
    private Texture background;


    private Array<Pipe> pipes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 250);
        camera.setToOrtho(false, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);
        background = new Texture("Background.png");

        pipes = new Array<Pipe>();

        for(int i=1; i<=PIPE_COUNT; i++){
            pipes.add(new Pipe(i*(PIPE_SPACING+Pipe.PIPE_WIDTH)));
        }
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
        camera.position.x = bird.getPosition().x+80;

        for(Pipe pipe: pipes){
            if(camera.position.x - (camera.viewportWidth/2) > pipe.getPosTopPipe().x+pipe.getTopPipe().getWidth()){
                pipe.reposition(pipe.getPosTopPipe().x + ((Pipe.PIPE_WIDTH+PIPE_SPACING)*PIPE_COUNT));

            }
            if(pipe.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
                return;
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(bird.getBirdTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Pipe pipe:pipes) {
            sb.draw(pipe.getTopPipe(), pipe.getPosTopPipe().x, pipe.getPosTopPipe().y);
            sb.draw(pipe.getBotPipe(), pipe.getPosBotPipe().x, pipe.getPosBotPipe().y);
        }


        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for(Pipe pipe:pipes)
            pipe.dispose();

    }
}
