package render;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

import backend.Logician;
import backend.Main;
import backend.PlayerHandler;
import entities.Map_struct;
import entities.PlayerCharacter;

public class PlayScreen implements Screen {

	// Don't let there be any textures here and let the Renderer take care of those things.

	public static final String windowTitle = "Xiabween";
	public static int windowWidth = 840;
	public static int windowHeight = 800;

	public static int viewPortWidth = 600, viewPortHeight = 600;

	public Renderer renderer;

	public PlayerCharacter shana, player2;
	public Main game;

	public Logician determinator;

	public Map_struct onlymap;
	public List<Map_struct> tiledmaps;

	public PlayScreen(final Main g) {
		this.game = g;
	}

	@Override
	public void show() { // This is where Xiabween determines what happens initially...

		renderer = new Renderer();
		renderer.setCameraViewPorts(viewPortWidth, viewPortHeight);

		determinator = new Logician();
		shana = PlayerHandler.createPlayer(new Vector2(0, 0));
		determinator.players.add(shana);
		determinator.setCurrentPlayerCharacter(shana);

		shana.textures = Loader.loadPlayerAtlas("shana_final.atlas", renderer);

		tiledmaps = new ArrayList<Map_struct>();
		onlymap = new Map_struct("grass.tmx");
		tiledmaps.add(onlymap);
		Loader.loadMaps(tiledmaps);

		renderer.setCurrentMap(onlymap);
		renderer.setThingToFollow(determinator.myPlayer);
		renderer.thingsToRender.add(shana);

		// TEST
		// create a new player
		player2 = PlayerHandler.createPlayer(new Vector2(200, 200));
		determinator.players.add(player2);
		player2.textures = Loader.loadPlayerAtlas("shana_final.atlas", renderer);
		renderer.thingsToRender.add(player2);

	}

	@Override
	public void render(float delta) { // ..and then from now on.
		// Game logic first...
		determinator.processStates();

		// ...then rendering.
		renderer.renderStates(determinator);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		renderer.gpu_keeper.delete();
	}

}
