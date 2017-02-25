package com.wg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by franjo on 25.02.17.
 */
public class Obstacle {

    public float x,y;
    public float width,height;
    public Texture texture;

    public Obstacle(float x,float y,float width,float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        texture = new Texture("dot.png");
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);
    }

}
