package backend;

import com.badlogic.gdx.Game;

import render_subsystem.PlayScreen;

public class Main extends Game {

	@Override
	public void create() {
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
