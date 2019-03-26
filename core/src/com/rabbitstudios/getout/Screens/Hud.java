package com.rabbitstudios.getout.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rabbitstudios.getout.GetOut;

import java.awt.Label;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label GetOutLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport((GetOut.v_width,GetOut.v_height, new OrthographicCamera()));
        stage = new Stage(viewport,sb); // Creates the stage with the sprites in there

        // making a table so we can put objects or labels in there
        Table table = new Table();
        table.top(); // alligning to the top of the stage
        table.setFillParent(true); // table is the size of the stage

        countdownLabel =
        scoreLabel =
        timeLabel =
        worldTimer =


    }
}
