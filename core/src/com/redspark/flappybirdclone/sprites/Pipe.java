package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Red on 19/01/2017.
 */

public class Pipe extends GameObject{
    private static final int FLUCTUATION = 130;
    private static final int PIPE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int PIPE_WIDTH = 80;

    private Random random;
    private Pipe topPipe;
    private boolean isTopPipe = true;


    public Pipe(float x){//constructor for the Top Pipe
        random = new Random();

        setTexture(new Texture("TopPipe.png"));
        setPosition(new Vector2(x, getRandomYpos()));
        setBounds(new Rectangle(new Rectangle(getPosition().x,getPosition().y, getTexture().getWidth(), getTexture().getHeight())));

    }
    public Pipe(Pipe topPipe){//Constructor for the Bot Pipe
        this.topPipe = topPipe;
        isTopPipe = false;//making sure the class know we are generating bot pipe
        setTexture(new Texture("BotPipe.png"));//TODO check if i can just flipe the topPipe texture
        setPosition(new Vector2(topPipe.getPosition().x, topPipe.getPosition().y - PIPE_GAP - getTexture().getHeight()));
        setBounds(new Rectangle(getPosition().x, getPosition().y, getTexture().getWidth(), getTexture().getHeight()));
    }

    @Override
    public void reposition(float x){
        if(isTopPipe) {//rendering for top pipe
            getPosition().set(x, random.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        }else {//rendering for bot pipe, because bot pipe position is relative to top pipe
            getPosition().set(x, topPipe.getPosition().y - PIPE_GAP - getTexture().getHeight());
        }

        getBounds().setPosition(getPosition().x, getPosition().y);

    }
    private float getRandomYpos(){//gets a random y position for top pipe
        return random.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING;
    }


}
