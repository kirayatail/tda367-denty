package com.googlecode.tda367.denty.ctrl;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Input;

import com.googlecode.tda367.denty.core.DentyModel;
import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.core.dynamicbody.MoveableBody;
import com.googlecode.tda367.denty.core.level.Level;
import com.googlecode.tda367.denty.util.Util;

/**
 * Handles all keyboard and mouse input.
 * 
 */
public class InputController {
	private Input input;
	private Vec2 mousePressedPos;
	private DentyModel model;

	private boolean exited = false;

	public InputController(DentyModel model) {
		this.model = model;
	}

	public void update(Input input) {
		this.input = input;

		MoveableBody denty = this.model.getLevel().getDenty();
		Level level = this.model.getLevel();
		Vec2 camera = new Vec2(level.getCamera().getCameraX(), level
				.getCamera().getCameraY());

		if (this.input.isKeyPressed(Input.KEY_SPACE)
				|| this.input.isKeyPressed(Input.KEY_UP)
				|| this.input.isKeyPressed(Input.KEY_W)) {
			MoveController.jump(denty);
		}

		if (this.input.isKeyDown(Input.KEY_LEFT)
				|| this.input.isKeyDown(Input.KEY_A)) {
			MoveController.moveLeft(denty);

		}

		if (this.input.isKeyDown(Input.KEY_RIGHT)
				|| this.input.isKeyDown(Input.KEY_D)) {
			MoveController.moveRight(denty);
		}

		if (this.input.isKeyPressed(Input.KEY_DELETE)) {
			GameData.getInstance().fireLevelFinished();
		}

		if (this.input.isKeyPressed(Input.KEY_ESCAPE)) {
			this.exited = true;
		}

		int mouseX = this.input.getMouseX();
		int mouseY = this.input.getMouseY();

		if (wasMousePressed()) {
			this.mousePressedPos = new Vec2(mouseX, mouseY);
			model.setMousePressedPos(this.mousePressedPos);
		} else if (wasMouseClicked(mouseX, mouseY)) {
			int x = ((int) (camera.x + mousePressedPos.x / 32));
			int y = ((int) (camera.y + mousePressedPos.y / 32));
			if (this.model.canPlace(x, y)) {
				level.placeBlock(x, y);
			} else {
				this.model.releaseBody(x, y);
			}
			this.mousePressedPos = null;
		}

		else if (wasMouseDragged(mouseX, mouseY)) {
			if (this.model.canThrow((int) (camera.x + mousePressedPos.x / 32),
					(int) (camera.y + mousePressedPos.y / 32))) {
				level.throwNewBlock(camera.x + mousePressedPos.x / 32, camera.y
						+ mousePressedPos.y / 32, new Vec2(
						(mouseX - mousePressedPos.x) * 1.2f,
						(mouseY - mousePressedPos.y) * 1.2f));
			}
			mousePressedPos = null;
		}

		if (mousePressedPos == null) {
			model.setWillThrow(false);
			model.setMousePressedPos(null);
		} else {
			model.setWillThrow(Util.distance(mousePressedPos, new Vec2(mouseX,
					mouseY)) > 32);
		}
	}

	public boolean hasExited() {
		return exited;
	}

	private boolean wasMousePressed() {
		return input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& mousePressedPos == null;
	}

	private boolean wasMouseClicked(int x, int y) {
		return !this.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& mousePressedPos != null
				&& Math.abs(x - mousePressedPos.x) <= 60
				&& Math.abs(y - mousePressedPos.y) <= 60;
	}

	private boolean wasMouseDragged(int x, int y) {
		return !this.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& mousePressedPos != null
				&& (Math.abs(x - mousePressedPos.x) > 60 || Math.abs(y
						- mousePressedPos.y) > 60);
	}

	public boolean willThrow(int x, int y) {
		return mousePressedPos != null
				&& (Math.abs(x - mousePressedPos.x) > 60 || Math.abs(y
						- mousePressedPos.y) > 60);
	}

}
