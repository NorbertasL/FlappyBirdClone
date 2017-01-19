package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Red on 19/01/2017.
 */

public class Pipe {
    private static final int FLUCTUATION = 130;
    private static final int PIPE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int PIPE_WIDTH = 80;

    private Texture topPipe, botPipe;
    private Vector2 posTopPipe, posBotPipe;
    private Rectangle boundsTop, boundsBot;

    private Random random;


    public Pipe(float x){
        topPipe = new Texture("TopPipe.png");
        botPipe = new Texture("BotPipe.png");
        random = new Random();

        posTopPipe = new Vector2(x, random.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        posBotPipe = new Vector2(x, posTopPipe.y - PIPE_GAP - botPipe.getHeight());

        boundsTop = new Rectangle(posTopPipe.x, posTopPipe.y, topPipe.getWidth(), topPipe.getHeight());
        boundsBot = new Rectangle(posBotPipe.x, posBotPipe.y, botPipe.getWidth(), botPipe.getHeight());
    }

    public Texture getTopPipe() {
        return topPipe;
    }

    public Texture getBotPipe() {
        return botPipe;
    }

    public Vector2 getPosTopPipe() {
        return posTopPipe;
    }

    public Vector2 getPosBotPipe() {
        return posBotPipe;
    }

    public void reposition(float x){
        posTopPipe.set(x, random.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        posBotPipe.set(x, posTopPipe.y - PIPE_GAP - botPipe.getHeight());

        boundsTop.setPosition(posTopPipe.x, posTopPipe.y);
        boundsBot.setPosition(posBotPipe.x, posBotPipe.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){
        botPipe.dispose();
        topPipe.dispose();
    }

}
