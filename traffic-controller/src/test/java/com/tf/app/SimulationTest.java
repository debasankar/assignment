package com.tf.app;

import org.junit.Test;

public class SimulationTest {
	@Test
	public void testTrafficControl()throws Exception{
		TrafficSimulator sim = new TrafficSimulator(20, 1000);
		sim.runSimulation();
	}

}
