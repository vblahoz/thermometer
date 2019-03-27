package com.vendavo.interview.thermometer.impl;

import com.vendavo.interview.thermometer.Color;
import com.vendavo.interview.thermometer.Thermometer;

/**
 * Thermometer implementation
 * 
 * @author vblahoz
 *
 */
public class ThermometerImpl implements Thermometer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vendavo.interview.thermometer.Thermometer#measure(double)
	 */
	@Override
	public Color measure(double temperature) throws IllegalAccessException {
		if(Double.isNaN(temperature)) {
			throw new IllegalAccessException("Temperature is not a number");
		}
		
		for(ColorInterval interval : ColorInterval.values()) {
			if(interval.belongsToInterval(temperature)) {
				return interval.getColor();
			}
		}
		
		return Color.UNKNOWN;
	}

}
