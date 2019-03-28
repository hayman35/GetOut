package com.rabbitstudios.getout.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
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
import com.rabbitstudios.getout.Sprites.bunny;

import javax.swing.Renderer;

public class PlayScreen implements Screen {
    private GetOut game;
    private bunny player;
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;


    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;


    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(GetOut game){
        this.game = game;
        //follows the bunny around
        gamecam = new OrthographicCamera();
        this.gamePort = new FitViewport(GetOut.V_WIDTH,GetOut.V_HEIGHT,gamecam);
        gamePort.apply();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        hud = new Hud(game.batch);

        //Load our map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        //initially set our gamcam to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10), true); // for the gravity
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        player = new bunny(world);

        //Create ground body to stay still
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+ rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() /2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        // create pipe bodies
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //create brick bodies
            for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

                body = world.createBody(bdef);

                shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
        // create coin bodies
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

       world.step(1/60f,6,2);
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

        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);


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
