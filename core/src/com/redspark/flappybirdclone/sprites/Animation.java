package com.redspark.flappybirdclone.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Red on 20/01/2017.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private TextureRegion textureRegion;
    private double maxFrameTime;
    private double currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(Texture texture, int frameCount, double cycleTime ){
        textureRegion =new TextureRegion(texture);
        frames = new Array<TextureRegion>();
        int frameWidth = textureRegion.getRegionWidth()/frameCount;
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(textureRegion, i *frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount =frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame=0;

    }
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime>maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }

    }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
    public int getFrameCount(){
        return frameCount;
    }
}
