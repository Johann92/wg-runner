package com.wg.game.parallax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.wg.game.Player;

/**
 * Created by franjo on 19.01.17.
 */
public class Parallax {

    private Array<Layer> layers;

    public Parallax() {

        layers = new Array<Layer>();
        layers.addAll(
                new Layer(0, 100, -90, 0),
                new Layer(0, 150, -80, 0),
                new Layer(0, 150, -70, 0),
                new Layer(0, 150, -60, 0),
                new Layer(0, 150, -50, 0),
                new Layer(0, 200, -40, 0)
        );

    }

    class Layer {
        private float velocityX, velocityY;
        private float offsetX, offsetY;
        public float x, y;
        Texture texture;

        public Layer() {
            this(0, 0, 0, 0);
        }

        public Layer(float dx, float dy) {
            this(0, 0, dx, dy);
        }

        public Layer(float offsetX, float offsetY, float velocityX, float velocityY) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.velocityX = velocityX;
            this.velocityY = velocityY;

            texture = new Texture("background/cloud_00.png");

        }

        public void update(float delta) {
            x += velocityX * delta;
            y += velocityY * delta;
        }

    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < layers.size; i++) {
            Layer l = layers.get(i);

            float alpha = l.velocityX / -90f;

            batch.setColor(1, 1, 1, alpha);
            batch.draw(l.texture, l.x + l.offsetX, l.y + l.offsetY);
        }

        batch.setColor(1, 1, 1, 1);

    }

    public void update(float delta, Player player) {


        for (int i = 0; i < layers.size; i++) {
            Layer l = layers.get(i);
            l.update(delta);

            if (l.x < player.getX() - 200) {

                System.out.println("reposition cloud :" + l.x);

                l.x = player.getX() + Gdx.graphics.getWidth() + MathUtils.random(20, 150);
                l.y = MathUtils.random(50, 180);



            }
        }


    }


}
