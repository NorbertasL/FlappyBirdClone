package com.redspark.flappybirdclone.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.redspark.flappybirdclone.FlappyBirdClone;
import com.redspark.flappybirdclone.sprites.Bird;
import com.redspark.flappybirdclone.sprites.Ground;
import com.redspark.flappybirdclone.sprites.Pipe;

/**
 * Created by Red on 19/01/2017.
 */

public class PlayState extends State {

    private static final int PIPE_SPACING = 125;
    private static final int PIPE_COUNT = 4;//this is pairs of pipes

    private boolean gameOver = false;

    private Bird bird;
    private Texture background;
    private Ground ground1, ground2;

    private Array<Pipe> topPipes;
    private Array<Pipe> botPipes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 250);
        camera.setToOrtho(false, FlappyBirdClone.WIDTH/2, FlappyBirdClone.HEIGHT/2);
        background = new Texture("Background.png");

        ground1 = new Ground(new Vector2(camera.position.x - camera.viewportWidth/2, Ground.GROUND_Y_OFFSET));
        ground2 = new Ground(new Vector2((camera.position.x - camera.viewportWidth/2)+ground1.getTexture().getWidth(), Ground.GROUND_Y_OFFSET));

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
        if(gameOver) {//game restarts
            gsm.set(new PlayState(gsm));
        }
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
                gameOver =true;
            }
        }

        if(camera.position.x -(camera.viewportWidth/2)>ground1.getPosition().x+ground1.getTexture().getWidth()){
            ground1.reposition(ground1.getPosition().x + ground1.getTexture().getWidth() *2);
        }
        if(camera.position.x -(camera.viewportWidth/2)>ground2.getPosition().x+ground2.getTexture().getWidth()){
            ground2.reposition(ground2.getPosition().x + ground2.getTexture().getWidth() *2);
        }
        if(bird.collision(ground1.getBounds()) || bird.collision(ground2.getBounds())) {
            gameOver = true;
        }


        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(bird.getTextureR(), bird.getPosition().x, bird.getPosition().y);

        for(Pipe topPipe:topPipes) {//renders top pipes

            sb.draw(topPipe.getTexture(), topPipe.getPosition().x, topPipe.getPosition().y);
        }
        for(Pipe botPipe:botPipes){//renders bot pipes
            sb.draw(botPipe.getTexture(), botPipe.getPosition().x, botPipe.getPosition().y);
        }
        sb.draw(ground1.getTexture(), ground1.getPosition().x, ground1.getPosition().y);
        sb.draw(ground2.getTexture(), ground2.getPosition().x, ground2.getPosition().y);


        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground1.dispose();
        ground2.dispose();

        for(Pipe topPipe:topPipes)
            topPipe.dispose();
        for(Pipe botPipe:botPipes)
            botPipe.dispose();

    }
}
