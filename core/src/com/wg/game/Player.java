package com.wg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Johann on 13.01.2017.
 */
public class Player {

    private int height;
    private int width;
    private float x;
    private float y;
    private float acceleration = 1.02f;
    private float velocityX = 60;
    private float velocityY = 0;
    private float accelerationY = 9.81f;
    private float gravity = -9.81f;

    Texture pixel;

    public Player() {
        this.height = 80;
        this.width = 40;
        pixel = new Texture("Pixel.jpg");
    }

    public void draw(SpriteBatch batch) {
        batch.draw(pixel, x, y, width, height);
    }


    public void update(float deltaTime) {
        //velocityX *= acceleration;
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            System.out.println("up");
            velocityY = 60;
        }

        velocityY += gravity;

        if (y <= 0) {
            y = 0;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
