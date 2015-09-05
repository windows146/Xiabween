package render;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.PlayerEntity;

/**
 * Handles collision detection.
 *
 * @author sorcerer
 *
 */
public class CollisionDetector {

	public static final int FEET_HEIGHT = 7;

	/**
	 * Determines if two objects will collide. The first is usually a moving
	 * object while the second is stationary, like a tile.
	 *
	 * TODO: Use only a collision layer to not iterate over a lot of stuff.
	 *
	 * @param player
	 *            Uses the bottom portion (feet area) of the
	 *            {@link PlayerEntity}'s rectangle.
	 * @param areaCoordinates
	 *            The spot the {@link PlayerEntity} wants to move to.
	 * @return true if the two objects will collide with each other.
	 */
	public static boolean willPlayerCollide(Rectangle player, Vector2 areaCoordinates) {
		Rectangle feet = new Rectangle(player.x, player.y, player.width, FEET_HEIGHT);

		return false;
	}

	/**
	 * Converts from the tiled coordinates of a .tmx map to libgdx's
	 * coordinates.
	 *
	 * @param tileCoordinates
	 *            The coordinates of the tile. Should be whole numbers (
	 *            {@link Integer}s).
	 * @return the converted game coordinates of the center of the tile.
	 * @param tileWidth
	 *            The width of an individual tile.
	 * @param tileHeight
	 *            The height of an individual tile.
	 * @param mapHeight
	 *            The height of the spot's map in tiles.
	 */
	public static Vector2 convertTileToGameCoordinates(Vector2 tileCoordinates, int tileWidth, int tileHeight, int mapHeight) {
		Vector2 newCoordinates = new Vector2();

		newCoordinates.x = (tileCoordinates.x * tileWidth) + (tileWidth / 2);
		newCoordinates.y = ((mapHeight - tileCoordinates.y - 1) * tileHeight) + (tileHeight / 2);

		return newCoordinates;
	}

	/**
	 * Converts from game coordinates to whole number tiled coordinates.
	 *
	 * @param gameCoordinates
	 *            Game coordinates.
	 * @return the converted tile coordinates in {@link Integer}s.
	 * @param tileWidth
	 *            The width of an individual tile.
	 * @param tileHeight
	 *            The height of an individual tile.
	 * @param mapHeight
	 *            The height of the tile's map in tiles.
	 */
	public static Vector2 convertGameToTileCoordinates(Vector2 gameCoordinates, int tileWidth, int tileHeight, int mapHeight) {
		Vector2 newCoordinates = new Vector2();

		newCoordinates.x = ((int) gameCoordinates.x / tileWidth);
		newCoordinates.y = mapHeight - ((int) (gameCoordinates.y / tileHeight)) - 1;

		return newCoordinates;
	}
}
