package com.rabbitstudios.getout.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rabbitstudios.getout.GetOut;

public class PlayScreen implements Screen {
    private GetOut game;


    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(GetOut game) {
        this.game = game;

//        Cam used to follow sprite through cam world
        gamecam = new OrthographicCamera();

//      Create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(GetOut.v_width,GetOut.v_height);

//      Create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("./levels/1-1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr= new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
    }

    @Override
    public void show() {

    }
   public void handleInput(float dt){
    if(Gdx.input.isTouched())
        gamecam.position.x += 100 * dt;
   }
    public void update(float dt){
       handleInput(dt);
       gamecam.update();
       renderer.setView(gamecam);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            gamecam.position.x += dt;
        }
    }

    public void update (float dt) {
        handleInput(dt);

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clears the screen

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); // shows in the camera, we are showing the labels in hud
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
