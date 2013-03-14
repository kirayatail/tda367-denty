package com.googlecode.tda367.denty.ctrl;

import java.util.List;

import org.jbox2d.common.Vec2;

import com.googlecode.tda367.denty.constants.DynamicConstant;
import com.googlecode.tda367.denty.constants.StateConstant;
import com.googlecode.tda367.denty.core.DentyModel;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.MoveableBody;
import com.googlecode.tda367.denty.core.dynamicbody.ShootingEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.ThrowableBlock;
import com.googlecode.tda367.denty.util.FilteringUtil;
import com.googlecode.tda367.denty.util.Util;

/**
 * Modifies and reads state constants, and passes enemies that should move
 * through the MoveController.
 * 
 */
public class AutoMoveController {

	public static void update(List<DynamicBody> bodies, Vec2 dentyPostion) {
		modifyStates(bodies);
		performMoves(bodies, dentyPostion);
	}

	private static void modifyStates(List<DynamicBody> bodies) {
		for (MoveableBody db : FilteringUtil.getListOf(bodies,
				MoveableBody.class)) {
			Vec2 dbPos = new Vec2(db.getPosition());
			if (db.getState(StateConstant.MOVING_LEFT)
					&& dbPos.x < db.getConstant(DynamicConstant.BOUNDS_WEST))
				db.setState(StateConstant.MOVING_RIGHT, true);

			if (db.getState(StateConstant.MOVING_RIGHT)
					&& dbPos.x > db.getConstant(DynamicConstant.BOUNDS_EAST))
				db.setState(StateConstant.MOVING_LEFT, true);

			if (db.getState(StateConstant.CONTACT_LEFT))
				db.setState(StateConstant.MOVING_RIGHT, true);
			if (db.getState(StateConstant.CONTACT_RIGHT))
				db.setState(StateConstant.MOVING_LEFT, true);
		}
	}

	private static void performMoves(List<DynamicBody> bodies,
			Vec2 dentyPosition) {
		for (MoveableBody db : FilteringUtil.getListOf(bodies,
				MoveableBody.class)) {
			if (db.getState(StateConstant.AUTO_MOVE)) {
				moveLeftRight(db);

				if (db.getState(StateConstant.FOLLOWING_DENTY)
						&& Util.distance(dentyPosition, db.getPosition()) < 15) {
					Vec2 dbPosition = db.getPosition();
					if (dbPosition.x < dentyPosition.x) {
						MoveController.moveRight(db);

					} else if (dbPosition.x > dentyPosition.x) {
						MoveController.moveLeft(db);
					}
				}
			} else if (db.getState((StateConstant.SHOOTING))
					&& ((ShootingEnemy) db).canShoot()) {
				ThrowableBlock tb = new ThrowableBlock(db.getPosition().x,
						db.getPosition().y - 1.5f, DentyModel.getInstance()
								.getLevel().getWorld());
				bodies.add(tb);
				Vec2 throwingForce = new Vec2(-10
						* (db.getPosition().x - dentyPosition.x), -3.5f
						* (db.getPosition().y - dentyPosition.y));
				tb.applyForce(throwingForce);
			}
		}
	}

	private static void moveLeftRight(MoveableBody db) {
		if (db.getState(StateConstant.MOVING_LEFT)) {
			MoveController.moveLeft(db);
		} else if (db.getState(StateConstant.MOVING_RIGHT)) {
			MoveController.moveRight(db);
		}
	}

}
