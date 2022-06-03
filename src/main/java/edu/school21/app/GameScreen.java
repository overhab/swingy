package edu.school21.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.lwjgl.opengl.GL20;

import static edu.school21.app.StaticVariables.PPM;
import static edu.school21.app.StaticVariables.SCALE;

public class GameScreen extends ScreenAdapter {

    private final OrthographicCamera orthographicCamera;
    private final SpriteBatch spriteBatch;
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private Texture texture;
    private TextureRegion region;

    public GameScreen(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
        this.spriteBatch = new SpriteBatch();
        this.world = new World(new Vector2(0, 0 ), false);
        this.debugRenderer = new Box2DDebugRenderer();
        this.texture = new Texture(Gdx.files.internal("grass.png"));
        this.region = new TextureRegion(texture, 20, 20, 32, 32);
    }

    private void update() {
        world.step(1 / 60f, 6, 2);
        cameraUpdate();

        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void cameraUpdate() {
//        orthographicCamera.position.set(new Vector3(0, 0,0));
//        orthographicCamera.position.set(800/2, 600/2, 0);
        orthographicCamera.update();
    }

    @Override
    public void render(float delta) {
        this.update();

        Gdx.gl.glClearColor(0.1f, 0.0f, 0.2f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        //render objects
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                spriteBatch.draw(region, i * SCALE, j * SCALE);
            }
        }
        spriteBatch.end();

        debugRenderer.render(world, orthographicCamera.combined.scl(PPM));
    }

    public void dispose() {
        spriteBatch.dispose();
    }
}
