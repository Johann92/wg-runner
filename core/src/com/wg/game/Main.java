package com.wg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.sun.javafx.geom.AreaOp;
import com.wg.game.parallax.Parallax;

public class Main extends ApplicationAdapter {

    OrthographicCamera camera;
    SpriteBatch batch;
    Texture img;
    Player player;
    Parallax background;

    Array<Obstacle> obstacles;

    @Override
    public void create() {
        System.out.println("hello wg runner");

        // create camera for projection
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        player = new Player();
        background = new Parallax();

        // create
        obstacles = new Array<Obstacle>();

        float previousX = Gdx.graphics.getWidth();
        for (int i = 0; i < 20; i++) {
            float x = previousX + MathUtils.random(280, 500);
            float y = 0;
            float width = MathUtils.random(20, 40);
            float height = MathUtils.random(20, 70);

            obstacles.add(new Obstacle(x,y,width,height));

            previousX = x + width;

        }

    }

    /**
     * updates the frame
     *
     * @param delta time in ms
     */
    public void update(float delta) {
        handleDebugInput();

        player.update(delta);
        background.update(delta,player);

        camera.position.set(camera.viewportWidth/2 + player.getX() - camera.viewportWidth/6,camera.viewportHeight/2,0);
        camera.update();

    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        update(delta);


        // clear GLFramebuffer with single color
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set current projection view
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        background.draw(batch);
        player.draw(batch);

        // render all obstacles
        for (int i = 0; i < obstacles.size; i++) {
            Obstacle o = obstacles.get(i);
            o.draw(batch);
        }

        batch.end();
    }

    /**
     * deallocate objects and free system memory
     */
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public void handleDebugInput() {

        // restart the game
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            dispose();
            create();
        }

    }
}
