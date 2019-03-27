package com.vendavo.interview.thermometer.impl;

import com.vendavo.interview.thermometer.Color;

/**
 * Color intervals enumeration
 * 
 * Is able to check if given float number belongs into specific interval. Each
 * interval has specified color representation.
 * 
 * Cases:
 * 
 * over 40> ... RED 
 * < 35 - 40) ... ORANGE 
 * < 25 - 35) ... YELLOW 
 * < 10 - 25) ... GREEN 
 * < 5 - 10) ... LIGHT_BLUE 
 * <-10 - 5 ) ... BLUE 
 * below -10) ... DARK_BLUE
 * 
 * @author vblahoz
 *
 */
public enum ColorInterval {
	RED(Color.RED, 40d, Double.POSITIVE_INFINITY), 
	ORANGE(Color.ORANGE, 35d, 40d), 
	YELLOW(Color.YELLOW, 25d, 35d),
	GREEN(Color.GREEN, 10d, 25d), 
	LIGHT_BLUE(Color.LIGHT_BLUE, 5d, 10d), 
	BLUE(Color.BLUE, -10d, 5d),
	DARK_BLUE(Color.DARK_BLUE, Double.NEGATIVE_INFINITY, -10d);

	private Color color;
	double lowF;
	double highF;

	private ColorInterval(Color color, double lowD, double highD) {
		this.color = color;
		this.lowF = lowD;
		this.highF = highD;
	}

	/**
	 * Check if the given number belongs to the interval
	 * 
	 * @param check number to check
	 * @return <true> if given number belongs to the interval, <false> otherwise
	 */
	public boolean belongsToInterval(double check) {
		return (check >= lowF)
				&& ((check < highF) || (check == Double.POSITIVE_INFINITY && highF == Double.POSITIVE_INFINITY));
	}

	/**
	 * Get the interval color representation
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}

}
