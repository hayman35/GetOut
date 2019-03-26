package com.rabbitstudios.getout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rabbitstudios.getout.Screens.PlayScreen;

public class GetOut extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this)); // sets the screen to the playscreen
	}

	@Override
	public void render () {
		super.render(); // delegates the render to whatever screen is active

	}
}
