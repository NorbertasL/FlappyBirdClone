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
    private static final int PIPE_COUNT = 4;//this is pairs of pipes

    private Bird bird;
    private Texture background;

    private Array<Pipe> topPipes;
    private Array<Pipe> botPipes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 250);
        camera.setToOrtho(false, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);
        background = new Texture("Background.png");


        topPipes = new Array<Pipe>();
        botPipes = new Array<Pipe>();

        for(int i=1; i<=PIPE_COUNT; i++){
            topPipes.add(new Pipe(i*(PIPE_SPACING+Pipe.PIPE_WIDTH)));
            botPipes.add(new Pipe(topPipes.peek()));
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
        bird.reposition(deltaTime);
        camera.position.x = bird.getPosition().x+80;

        for(int i = 0; i < topPipes.size; i++){
            Pipe topPipe = topPipes.get(i);
            Pipe botPipe = botPipes.get(i);
            if(camera.position.x - (camera.viewportWidth/2) > topPipe.getPosition().x+topPipe.getTexture().getWidth()){
                topPipe.reposition(topPipe.getPosition().x + ((Pipe.PIPE_WIDTH+PIPE_SPACING)*PIPE_COUNT));
                botPipe.reposition(botPipe.getPosition().x + ((Pipe.PIPE_WIDTH+PIPE_SPACING)*PIPE_COUNT));


            }
            if(topPipe.collision(bird.getBounds()) || botPipe.collision(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Pipe topPipe:topPipes) {//renders top pipes

            sb.draw(topPipe.getTexture(), topPipe.getPosition().x, topPipe.getPosition().y);
        }
        for(Pipe botPipe:botPipes){//renders bot pipes
            sb.draw(botPipe.getTexture(), botPipe.getPosition().x, botPipe.getPosition().y);
        }


        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for(Pipe topPipe:topPipes)
            topPipe.dispose();
        for(Pipe botPipe:botPipes)
            botPipe.dispose();

    }
}
