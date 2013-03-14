package com.googlecode.tda367.denty;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.googlecode.tda367.denty.ctrl.GameController;

/**
 * Starts slick
 * 
 * 
 */
public class Main {
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new GameController(
					"Denty"));
			app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(),
					true);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
