package com.tf.app.domain.model;

/**
 * Represents a traffic light
 */
public class TrafficLight {
	
	/**
	 * the current signal of the traffic light in West-East direction
	 */
	private Signal signalW;
	private Signal signalE;
	/**
	 * the current signal of the traffic light in North-South direction
	 */
	private Signal signalN;
	private Signal signalS;
	
	/**
	 * A new traffic light. 
	 * The initial signal of West-East is GREEN, while that of North-South is RED.
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
	 * @param sgnWE	the initial signal in West-East direction
	 * @param sgnNS	the initial signal in North-South direction
	 */
	/*public TrafficLight(Signal sgnWE, Signal sgnNS) {
		if(isValidSignal(sgnWE, sgnNS)==false) {
			throw new IllegalArgumentException();
		}
		this.signalWE = sgnWE;
		this.signalNS = sgnNS;
	}*/
	
	private boolean isValidSignal(Signal sgn1, Signal sgn2) {
		
		if(sgn1.equals(Signal.GREEN) && sgn2.equals(Signal.GREEN) ) {
			return false;
		}		
		return true;
	}

	
	public void changeSignal(Direction dir,Signal signal) {
		
		switch(dir){
		case E:  signalE=signal; break;
		case W:  signalW=signal; break;
		case S:  signalS=signal; break;
		case N:  signalN=signal; break;
		}
		
		
	}
	

	/**
	 * Obtains the traffic light signal in the given direction
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
		
		return null;
	}

}
