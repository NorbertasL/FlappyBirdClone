package com.redspark.flappybirdclone.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Red on 19/01/2017.
 */

public class GameStateManager {

    private Stack<State> states;

    public  GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        this.pop();
        this.push(state);
    }

    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
