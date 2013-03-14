package com.googlecode.tda367.denty.ctrl;

import java.awt.Dimension;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.googlecode.tda367.denty.constants.ResolutionConstants;
import com.googlecode.tda367.denty.core.DentyModel;
import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.level.Level;
import com.googlecode.tda367.denty.view.DentyView;
import com.googlecode.tda367.denty.view.HUDView;

/**
 * Extension of a required class in the Slick framework. Acts as the main
 * controller that drives all other controllers
 * 
 * 
 * 
 */
public class GameController extends BasicGame {

	private DentyModel model;
	private DentyView view;
	private InputController inputController;

	public GameController(String title) {
		super(title);
		GameData gd = GameData.getInstance();
		gd.addPropertyChangeListener(HUDView.getInstance());
		view = DentyView.getInstance();
		gd.addPropertyChangeListener(view);
		model = DentyModel.getInstance();
		gd.addPropertyChangeListener(model);
		inputController = new InputController(model);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		this.view.render(container, g);

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		ResolutionConstants.HEIGHT = (float) container.getHeight() / 32;
		ResolutionConstants.WIDTH = (float) container.getWidth() / 32;

		// Loads physics engine, level and so on
		this.model.init();
		// Loads graphics
		this.view.init();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		model.update(container, delta);
		inputController.update(container.getInput());
		if (inputController.hasExited())
			container.exit();

		List<DynamicBody> bodyList = model.getDynamicBodies();
		Level level = model.getLevel();
		Dimension levelDimension = level.getDimension();
		AutoMoveController.update(bodyList, level.getDenty().getPosition());
		RecycleController.update(bodyList, levelDimension.width,
				levelDimension.height);
	}

}
