package render;

import java.util.List;

import backend.Logician;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import entities.Fireball;
import entities.Map_struct;
import entities.PlayerCharacter;

public class Renderer {

	public SpriteBatch batch;
	public OrthographicCamera camera;
	public int camera_width = 1024, camera_height = 700; // These won't be final, because there may be a time when a screen object would want to change these.

	public Map_struct currentMap;
	public PlayerCharacter thingToFollow; // Note that it could be possible to follow something that isn't a player.
											// Thus, an Abstract Game Object would be better.

	public Renderer(boolean createOwnRenders, int cameraViewPortWidth, int cameraViewPortHeight) {
		if (createOwnRenders) {
			this.batch = new SpriteBatch();
			this.camera = new OrthographicCamera();
		}

		setCameraViewPorts(cameraViewPortWidth, cameraViewPortHeight);

		// TODO Should really put all rendering into here (such as loading textures/textureatlases/textureregions)
		// instead of the Screen class.

	}

	// Can be used for each new screen created, so make the arguments a list of things to load, such as:
	// TextureAtlases to load, TextureRegions to load, regular Textures to load, and even Sprites.
	public void loadThings(List<TextureAtlas> texture_atlases, List<TextureRegion> texture_regions,
			List<Texture> textures) {

	}

	public void renderPlayer(Batch batch, PlayerCharacter playerinfo) {
		batch.begin();

		batch.draw(playerinfo.texture, playerinfo.position.x, playerinfo.position.y);

		batch.end();
	}

	public void renderFireblast(Fireball fireball, float x, float y) {

	}

	public void setCameraViewPorts(int viewWidth, int viewHeight) {
		this.camera.setToOrtho(false, viewWidth, viewHeight);
	}

	public void setCurrentMap(Map_struct map) {
		this.currentMap = map;
	}

	// TODO: Have a way to tell a Renderer object from a caller to control a camera based on the requests of a caller.
	public void renderStates(Logician logicdata) { // Will act as a main rendering loop.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		currentMap.mapRenderer.setView(camera);
		currentMap.mapRenderer.render();

		for (PlayerCharacter player : logicdata.players) {
			renderPlayer(currentMap.mapRenderer.getBatch(), player);
		}

		this.thingToFollow = logicdata.myPlayer;
		camera.position.set(thingToFollow.position.x + 50, thingToFollow.position.y + 50, 0); // I know that the character is 100 pixels high and wide.
		camera.update();

		// TODO Use an abstract game object that can be generic for all types of game objects such as player entities,
		// skill effects, mob entities, and environmental entities.

		for (Fireball fireball : logicdata.fireballs) {
			renderFireblast(fireball, fireball.position.x, fireball.position.y);
		}
	}

}