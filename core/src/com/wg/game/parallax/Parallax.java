package com.wg.game.parallax;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
                new Layer(0,100,0.9f, 0),
                new Layer(0,50,0.7f, 0),
                new Layer(0,0,0.5f, 0)
        );

    }

    class Layer {
        private float dx, dy;
        private float offsetX, offsetY;
        public float x, y;
        Texture texture;

        public Layer() {
            this(0,0,0,0);
        }

        public Layer(float dx, float dy) {
            this(0,0,dx,dy);
        }

        public Layer(float offsetX, float offsetY, float dx, float dy) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.dx = dx;
            this.dy = dy;
            texture = new Texture("silhouette_1.png");

        }

        public void update(Player player) {
            x = player.getX() * dx;
            y = player.getY() * dy;
        }

    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < layers.size; i++) {
            Layer l = layers.get(i);
            batch.draw(l.texture, l.x + l.offsetX, l.y + l.offsetY);
        }
    }

    public void update(Player player) {

        for (int i = 0; i < layers.size; i++) {
            Layer l = layers.get(i);
            l.update(player);
        }
    }


}
