package com.tf.app.domain.model;

import java.util.NoSuchElementException;

/**
 * Represents a traffic light
 */
public class TrafficLight {
	
	/**
	 * the current Westbound signal of the traffic light.
	 */
	private Signal signalW;
	
	/**
	 * the current Eastbound signal of the traffic light.
	 */
	private Signal signalE;
	/**
	 * the current Northbound signal of the traffic light.
	 */
	private Signal signalN;
	
	/**
	 * the current Southbound signal of the traffic light.
	 */
	private Signal signalS;
	
	/**
	 * A new traffic light. 
	 * The initial signal of West-East & East-West is GREEN, while that of North-South & 
	 * South-North  is RED.
	 */
	public TrafficLight() {
		this.signalW = Signal.RED;
		this.signalE = Signal.RED;
		this.signalN = Signal.GREEN;
		this.signalS = Signal.GREEN;
	}
	
	
	/**
	 * A new traffic light
	 * 
	 * @param sgnW	Westbound signal
	 * @param sgnE	Eastbound signal
	 * @param sgnN	Northbound signal
	 * @param sgnS	Southbound signal
	 */
	public TrafficLight(Signal sgnW,Signal sgnE, Signal sgnN,Signal sgnS) {
		this.signalW = sgnW;
		this.signalE = sgnE;
		this.signalN = sgnN;
		this.signalS = sgnS;
	}
	
	/**
	 * Change the traffic light signal in the given direction
	 * 
	 * @param dir	the direction of the road which the signal affects
	 * @param signal signal that needs to be set for given direction.
	 */
	public void changeSignal(Direction dir,Signal signal) {
		
		switch(dir){
		case E:  signalE=signal; break;
		case W:  signalW=signal; break;
		case S:  signalS=signal; break;
		case N:  signalN=signal; break;
		}		
	}	

	/**
	 * Get the traffic light signal in the given direction
	 * 
	 * @param dir	the direction of the road which the signal affects
	 * @return	the current signal of the traffic light
	 */
	public Signal getSignal(Direction dir) {
		switch(dir){
		case E:  return signalE;
		case W:  return signalW; 
		case S:  return signalS;
		case N:  return signalN;
		}		
		throw new NoSuchElementException("invalid direction");
	}

}
