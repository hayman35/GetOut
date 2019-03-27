package com.rabbitstudios.getout.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rabbitstudios.getout.GetOut;
import static com.badlogic.gdx.Input.Keys.L;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    // These are the Widgets
    private Label countdownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label worldLabel;
    private Label levelLabel;
    private Label BunnyLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(GetOut.v_width,GetOut.v_height, new OrthographicCamera());
        stage = new Stage(viewport,sb); // Creates the stage with the sprites in there

        // making a table so we can put objects or labels in there
        Table table = new Table();
        table.top(); // aligning to the top of the stage
        table.setFillParent(true); // table is the size of the stage

        // sets the labels
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =  new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel =  new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel =  new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        BunnyLabel =  new Label("BUNNY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel =  new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(BunnyLabel).expandX(); // expandx would extend it to the top of the screen and padtop would assign it to 10 pixels of section
        table.add(worldLabel).expandX();
        table.add(timeLabel).expandX();
        table.row(); // creates a new row
        table.add(scoreLabel);
        table.add(levelLabel);
        table.add(countdownLabel);

        stage.addActor(table); // adds the table to the stage




    }
}
