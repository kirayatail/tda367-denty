package com.googlecode.tda367.denty.core.data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Data container at the absolute bottom of the class hierarchy. Global
 * variables and events are saved and triggered here.
 * 
 */
public class GameData {

	private static GameData instance;
	private int hp;
	private int lives;
	private int blocks;
	private int maxBlocks;
	private PropertyChangeSupport pcs;

	private GameData() {
		this.pcs = new PropertyChangeSupport(this);
	}

	public static GameData getInstance() {
		if (instance == null) {
			instance = new GameData();
		}
		return instance;
	}

	public int getHP() {
		return this.hp;
	}

	public void setHP(int hp) {
		int oldHP = this.hp;
		this.hp = hp;
		pcs.firePropertyChange("hp", oldHP, this.hp);
	}

	public int getBlocks() {
		return this.blocks;
	}

	public void setBlocks(int blocks) {
		int oldBlocks = this.blocks;
		this.blocks = blocks;
		pcs.firePropertyChange("blocks", oldBlocks, this.blocks);
	}

	public int getLives() {
		return this.lives;
	}

	public void setLives(int lives) {
		int oldLives = this.lives;
		this.lives = lives;
		this.pcs.firePropertyChange("lives", oldLives, this.lives);
	}

	public void setMaxBlocks(int maxBlocks) {
		int oldMaxBlocks = this.maxBlocks;
		this.maxBlocks = maxBlocks;
		pcs.firePropertyChange("maxBlocks", oldMaxBlocks, this.maxBlocks);
	}

	public int getMaxBlocks() {

		return maxBlocks;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return this.pcs;

	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.removePropertyChangeListener(pcl);
	}

	public void fireDentyKilled() {
		this.pcs.firePropertyChange("killed", false, true);
	}

	public void fireLevelChanged() {
		pcs.firePropertyChange("levelChanged", false, true);
	}

	public void fireLevelFinished() {
		pcs.firePropertyChange("levelFinished", false, true);
	}

	public void fireGameReset() {
		pcs.firePropertyChange("gameReset", false, true);
	}
}
