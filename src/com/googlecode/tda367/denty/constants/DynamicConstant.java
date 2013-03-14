package com.googlecode.tda367.denty.constants;

/**
 * Contains key values to constants related to dynamic properties in
 * DynamicBody.
 * 
 * Keys are to be used in hashmaps for each DynamicBody, with <code>float</code>
 * values.
 * 
 * @author Max Witt
 * 
 */

public enum DynamicConstant {
	VELOCITY_H_MAX, VELOCITY_H_INC, VELOCITY_V_MAX, VELOCITY_V_INC, JUMP_FORCE, BOUNDS_EAST, // Defines
																								// x-values
																								// of
																								// roaming
																								// boundaries
																								// (concerns
																								// autonomous
																								// enemies)
	BOUNDS_WEST, // -- ' ' --
	BOUNDS_NORTH, // Same but y-values
	BOUNDS_SOUTH,
}
