package com.googlecode.tda367.denty.view;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.googlecode.tda367.denty.core.DentyModel;
import com.googlecode.tda367.denty.core.camera.Camera;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.util.Util;

/**
 * Renders the game environment
 * 
 */
public class DentyView implements PropertyChangeListener {

	private DentyModel model;

	private Map<String, Image> dbImgMap;

	private TiledMap tiledMap;

	private Image blockOkImg;

	private Image blockNotOkImg;

	private Image blockDropImg;

	private HUDView hud;

	private static DentyView instance;

	private DentyView() {
		// private constructor to ensure the class is a singleton class
		// the model will be needed to know where to draw what
		this.model = DentyModel.getInstance();
	}

	public static DentyView getInstance() {
		if (instance == null) {
			DentyView.instance = new DentyView();
		}
		return DentyView.instance;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setAntiAlias(true);
		this.model.getLevel().getCamera()
				.updateCamera(this.model.getDentyPosition());
		g.setBackground(Color.cyan);

		// Render background and ground layer of map.
		this.drawMap(0);
		this.drawMap(1);

		// Draws all dynamic objects
		List<DynamicBody> bodies = this.model.getDynamicBodies();
		Camera camera = this.model.getLevel().getCamera();
		for (DynamicBody body : bodies) {
			if (camera.isOnCamera(body.getPosition(), body.getDimension())) {
				Image bodyImage = this.getDBImage(body.getImagePath());
				bodyImage.setRotation(Util.toDegrees(body.getRotation()));
				Vec2 dbPos = body.getPosition();
				bodyImage.drawCentered((dbPos.x - camera.getCameraX()) * 32,
						(dbPos.y - camera.getCameraY()) * 32);

			}
		}

		// Draws foreground of map
		this.drawMap(2);

		this.drawMouseRelated(container, g);
		this.hud.render(container, g);

	}

	private Image getDBImage(String imagePath) throws SlickException {
		Image image;
		if (this.dbImgMap.containsKey(imagePath)) {
			image = this.dbImgMap.get(imagePath);
		} else {
			image = new Image(imagePath);
			this.dbImgMap.put(imagePath, image);
		}
		return image;
	}

	private void drawMouseRelated(GameContainer container, Graphics g) {
		Vec2 pressedPos = model.getMousePressedPos();
		Point mousePos = new Point(container.getInput().getMouseX(), container
				.getInput().getMouseY());
		Image pointerImage;

		float cameraX = this.model.getLevel().getCamera().getCameraX();
		float cameraY = this.model.getLevel().getCamera().getCameraY();
		float x = (int) (cameraX + (pressedPos != null ? pressedPos.x
				: mousePos.x) / 32);
		float y = (int) (cameraY + (pressedPos != null ? pressedPos.y
				: mousePos.y) / 32);

		if (model.canPlace(x, y)) {
			pointerImage = this.blockOkImg;
		} else if (model.canReleaseBody(x, y)) {
			pointerImage = this.blockDropImg;
		} else {
			pointerImage = this.blockNotOkImg;
		}

		pointerImage.draw((x - cameraX) * 32, (y - cameraY) * 32);

		if (model.willThrow()) {
			g.setColor(model.canThrow((int) x, (int) y) ? Color.black
					: Color.red);
			g.drawLine(container.getInput().getMouseX(), container.getInput()
					.getMouseY(), (x - cameraX) * 32 + pointerImage.getWidth()
					/ 2, (y - cameraY) * 32 + pointerImage.getHeight() / 2);
		}
	}

	private void drawMap(int layer) {
		Camera c = this.model.getLevel().getCamera();
		this.tiledMap.render(-(int) (c.getCameraX() * 32),
				-(int) (c.getCameraY() * 32), layer);
	}

	public void init() throws SlickException {
		this.tiledMap = new TiledMap(this.model.getLevel().getTiledMapPath());
		this.dbImgMap = this.createMap();
		this.blockOkImg = new Image("res/img/block-ok.png");
		this.blockNotOkImg = new Image("res/img/block-not-ok.png");
		this.blockDropImg = new Image("res/img/block-drop.png");
		this.hud = HUDView.getInstance();
		this.hud.init();
	}

	private Map<String, Image> createMap() {
		return new HashMap<String, Image>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == "levelChanged") {
			try {
				this.tiledMap = new TiledMap(this.model.getLevel()
						.getTiledMapPath());
			} catch (SlickException se) {
				se.printStackTrace();
			}
		}
	}
}
