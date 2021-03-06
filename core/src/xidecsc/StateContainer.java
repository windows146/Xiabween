package xidecsc;

import com.badlogic.gdx.math.Vector2;

import entities.PlayerEntity;
import player.ActualPlayerClass;

/**
 * Contains and modifies the state of game logic objects, such as if an object
 * even exists at all or its position. A {@link XIDECSCComponent}.
 *
 * @author sorcerer
 *
 */
public class StateContainer {

	public void movePlayer(ActualPlayerClass player, PlayerEntity entity, double angle, String direction, Vector2 displacement) {
		player.radians_Direction = angle;
		entity.direction = direction;

		/*
		 * Check for collision elsewhere, by the way, not here in this method.
		 */

		player.x += displacement.x * player.speed;
		player.y += displacement.y * player.speed;
		entity.position.x += displacement.x * player.speed;
		entity.position.y += displacement.y * player.speed;

		entity.playerRectangle.x = entity.position.x;
		entity.playerRectangle.y = entity.position.y;

		entity.isAnimating = true;
	}

}
