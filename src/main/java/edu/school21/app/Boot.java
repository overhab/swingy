package edu.school21.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Boot extends Game {

    public static Boot INSTANCE;
    private int width, height;
    private OrthographicCamera orthographicCamera;

    public Boot() {
        INSTANCE = this;
    }

    @Override
    public void create() {
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        this.orthographicCamera = new OrthographicCamera(width, height);
        this.orthographicCamera.setToOrtho(false, width, height);
//        orthographicCamera.position.set(width/2f, height/2f, 0);
        Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
//        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        setScreen(new GameScreen(orthographicCamera));
    }
}
