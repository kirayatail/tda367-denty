package com.googlecode.tda367.denty.util;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * A factory for creating bodies in the physical world.
 * 
 */
public class PhysicalBodiesUtil {
	private static final float standardDensity = 0.5f;
	private static final float standardLengthUnit = 0.96f;

	public static BodyDef createBodyDef(Vec2 position, BodyType bodyType,
			Object userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position = new Vec2(position);
		bodyDef.userData = userData;
		bodyDef.type = bodyType;
		return bodyDef;
	}

	public static BodyDef createBodyDef(Vec2 position, BodyType bodyType,
			Object userData, boolean hasFixedRotation, float linearDamping) {
		BodyDef bodyDef = createBodyDef(position, bodyType, userData);
		bodyDef.fixedRotation = hasFixedRotation;
		bodyDef.linearDamping = linearDamping;
		return bodyDef;
	}

	public static PolygonShape createBoxShape() {
		return createBoxShape(standardLengthUnit, standardLengthUnit);
	}

	public static PolygonShape createBoxShape(float width, float height) {
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(width / 2, height / 2);
		return ps;
	}

	public static CircleShape createCircleShape() {
		return createCircleShape(standardLengthUnit);
	}

	public static CircleShape createCircleShape(float diameter) {
		CircleShape cs = new CircleShape();
		cs.m_radius = diameter / 2;
		return cs;
	}

	public static PolygonShape createPolygonShape(Vec2[] vertices) {
		PolygonShape ps = new PolygonShape();
		ps.set(vertices, vertices.length);
		return ps;
	}

	public static FixtureDef createFixtureDef(Shape shape) {
		return createFixtureDef(shape, standardDensity, 0);
	}

	public static FixtureDef createFixtureDef(Shape shape, float density,
			float restitution) {
		FixtureDef def = new FixtureDef();
		def.shape = shape;
		def.density = density;
		def.restitution = restitution;
		return def;
	}

	public static Body createBody(World world, BodyDef bodyDef, Shape shape) {
		return createBody(world, bodyDef, shape, standardDensity);
	}

	public static Body createBody(World world, BodyDef bodyDef, Shape shape,
			float density) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(shape);
		return createBody(world, bodyDef, shapes, density);
	}

	public static Body createBody(World world, BodyDef bodyDef,
			ArrayList<? extends Shape> shapes) {
		return createBody(world, bodyDef, shapes, standardDensity);
	}

	public static Body createBody(World world, BodyDef bodyDef,
			List<? extends Shape> shapes, float density) {
		Body body = world.createBody(bodyDef);
		for (Shape shape : shapes) {
			if (shape != null) {
				body.createFixture(shape, density);
			}
		}
		return body;
	}

	public static Body createBody(World world, BodyDef bodyDef,
			FixtureDef fixtureDef) {
		ArrayList<FixtureDef> fixtureDefs = new ArrayList<FixtureDef>();
		fixtureDefs.add(fixtureDef);
		return createBody(world, bodyDef, fixtureDefs);
	}

	public static Body createBody(World world, BodyDef bodyDef,
			List<? extends FixtureDef> fixtureDefs) {
		Body body = world.createBody(bodyDef);
		for (FixtureDef fixtureDef : fixtureDefs) {
			if (fixtureDef != null) {
				body.createFixture(fixtureDef);
			}
		}
		return body;
	}
}
