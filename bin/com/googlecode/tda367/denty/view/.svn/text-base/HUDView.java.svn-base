package com.googlecode.tda367.denty.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.googlecode.tda367.denty.core.DentyModel;
import com.googlecode.tda367.denty.core.data.GameData;

/**
 * Renders information outside the game world (lives, HP, blocks available)
 * 
 */
public class HUDView implements PropertyChangeListener {

	private Image lifeIndicator;
	private Image blockIndicator;
	private GameData gameData;
	private int lives;
	private int hp;
	private int blocks;
	private int maxBlocks;
	private static HUDView instance;
	private String levelName;

	private HUDView() {
		this.gameData = GameData.getInstance();
	}

	public static HUDView getInstance() {
		if (instance == null) {
			instance = new HUDView();
		}
		return instance;
	}

	public void render(GameContainer container, Graphics g) {

		// Render HP-bar.
		g.setColor(Color.white);
		g.fillRect(100, 10, 104, 20);
		if (hp > 0) {
			if (hp <= 25) {
				g.setColor(Color.red);
			} else if (hp <= 50) {
				g.setColor(Color.orange);
			} else if (hp <= 75) {
				g.setColor(Color.yellow);
			} else {
				g.setColor(Color.green);
			}
			g.fillRect(102, 12, hp, 16);
		}
		g.setColor(Color.black);

		// Render Life and block indicator
		g.drawImage(this.lifeIndicator, 210, 10);
		g.drawString(lives + "", 234, 10);
		g.drawImage(this.blockIndicator, 258, 10);
		g.drawString(blocks + "/" + maxBlocks, 282, 10);
		g.drawString(levelName, 350, 10);

		g.setColor(Color.white);
	}

	public void init() throws SlickException {
		this.lifeIndicator = new Image("res/img/denty-indicator.png");
		this.blockIndicator = new Image("res/img/block-indicator.png");
		this.levelName = DentyModel.getInstance().getLevel().getName();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == "lives") {
			this.lives = gameData.getLives();
		} else if (evt.getPropertyName() == "hp") {
			this.hp = gameData.getHP();
		} else if (evt.getPropertyName() == "blocks") {
			this.blocks = gameData.getBlocks();
		} else if (evt.getPropertyName() == "maxBlocks") {
			this.maxBlocks = gameData.getMaxBlocks();
		} else if (evt.getPropertyName() == "levelChanged") {
			this.levelName = DentyModel.getInstance().getLevel().getName();
		}
	}
}
