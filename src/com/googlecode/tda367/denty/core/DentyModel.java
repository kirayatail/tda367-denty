package com.googlecode.tda367.denty.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Settings;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;

import com.googlecode.tda367.denty.constants.Constants;
import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.level.ClimbLevel;
import com.googlecode.tda367.denty.core.level.FastLevel;
import com.googlecode.tda367.denty.core.level.Level;
import com.googlecode.tda367.denty.core.level.PuzzleLevel;
import com.googlecode.tda367.denty.core.level.TutorialLevel;

/**
 * Singleton model containing game information above Level and below Slick.
 * 
 * Handles some information passthrough from InputController to View (mouse
 * positions etc.)
 * 
 * 
 */
public class DentyModel implements PropertyChangeListener {
	private static DentyModel instance;
	private Level level;
	private ArrayList<Class<? extends Level>> levelList;
	private Vec2 mousePressedPos;
	private boolean throwing;

	public Vec2 getMousePressedPos() {
		return mousePressedPos;
	}

	public void setMousePressedPos(Vec2 pos) {
		this.mousePressedPos = pos;
	}

	private DentyModel() {
		// private constructor to ensure the class is a singleton class
	}

	public static DentyModel getInstance() {
		if (instance == null) {
			DentyModel.instance = new DentyModel();
		}
		return instance;
	}

	public void init() {
		levelList = new ArrayList<Class<? extends Level>>();
		levelList.add(TutorialLevel.class);
		levelList.add(FastLevel.class);
		levelList.add(ClimbLevel.class);
		levelList.add(PuzzleLevel.class);
		this.loadNextLevel();
		GameData.getInstance().setLives(Constants.STARTING_LIVES);
		Settings.linearSlop = 0.0001f;
	}

	public void update(GameContainer container, int delta) {
		level.update();
	}

	public boolean canReleaseBody(float x, float y) {
		return level.canReleaseBody(x + 0.2f, y + 0.2f, x + 0.8f, y + 0.8f);
	}

	public void releaseBody(int x, int y) {
		if (this.canReleaseBody(x, y))
			level.releaseBodies(x + 0.2f, y + 0.2f, x + 0.8f, y + 0.8f);
	}

	public Level getLevel() {
		return level;
	}

	public List<DynamicBody> getDynamicBodies() {
		return this.level.getDynamicBodies();
	}

	public boolean canPlace(float x, float y) {
		return level.canAddBlock()
				&& level.isAreaFree(x + 0.2f, y + 0.2f, x + 0.8f, y + 0.8f);
	}

	public Vec2 getDentyPosition() {
		return new Vec2(level.getDenty().getPosition());
	}

	public boolean canThrow(int x, int y) {
		return this.canPlace(x, y);
	}

	public void setWillThrow(boolean b) {
		this.throwing = b;
	}

	public boolean willThrow() {
		return this.throwing;
	}

	private void loadNextLevel() {
		if (!levelList.isEmpty()) {
			Class<? extends Level> nextLevel = levelList.get(0);
			levelList.remove(0);
			try {
				this.level = nextLevel.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				handleLoadingError(nextLevel);
			} catch (Error e) {
				e.printStackTrace();
				handleLoadingError(nextLevel);
			}
		}
	}

	private <T extends Level> void handleLoadingError(Class<T> level) {
		System.out.println("Couldn't load level \""
				+ level.getSimpleName()
				+ "\""
				+ (levelList.isEmpty() ? "." : ", will try to load level \""
						+ levelList.get(0).getSimpleName() + "\"."));
		if (!levelList.isEmpty())
			loadNextLevel();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == "killed") {
			level.restart();
		} else if (evt.getPropertyName() == "levelFinished") {
			if (levelList.isEmpty()) {
				System.out.println("Game complete! Congratulations!");
				System.exit(0);
			} else {
				this.loadNextLevel();
				GameData gd = GameData.getInstance();
				gd.fireLevelChanged();
			}
		} else if (evt.getPropertyName() == "gameReset") {
			levelList.clear();
			this.init();
			GameData gd = GameData.getInstance();
			gd.fireLevelChanged();
		}
	}
}
