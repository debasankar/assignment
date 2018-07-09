package com.tf.app;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

import com.tf.app.domain.model.Car;
import com.tf.app.domain.model.Direction;
import com.tf.app.domain.model.Road;
import com.tf.app.domain.model.Signal;
import com.tf.app.domain.model.TrafficLight;
import com.tf.app.domain.model.TrafficModel;

public class TrafficSimulator {
	private int runCycle;
	private long waitTime;//in milliseconds
	private int clock = 1;
	// Time duration in seconds for Green Signal Change in two roads.
	private int signalG2G = 4;

	public TrafficSimulator(int runCycle, long waitTime) {
		this.runCycle = runCycle;
		this.waitTime = waitTime;
	}

	/**
     * Run the traffic simulation with given runCycle and waitTime
     * 
     */
	public void runSimulation() throws Exception {

		TrafficModel tfModel = new TrafficModel();
		TrafficLight tfLight = new TrafficLight();

		for (int i = 0; i < runCycle; ++i) {
			addCarsToRoad(tfModel);
			process((trfModel, trfLight) -> applyTrafficRule(trfModel, trfLight),tfModel, tfLight)
			.stream()
			.map(road -> road.getDirection() + "" + "=" + road.getTotalCars())
			.reduce((result1, result2) -> result1 + "," + result2)
			.ifPresent(result -> System.out.println(clock + "  - " + result));

			if (waitTime > 0) {
				try {
					Thread.sleep(waitTime);
				} catch (Exception ex) {
				}
			}
			clock++;
		} // end of loop

	}

	/**
     * Add new cars to given traffic model
     * @param tfModel Traffic model. 
     */
	public void addCarsToRoad(TrafficModel tfModel) throws Exception {
		Random randomGenerator = new Random();

		List<Road> roads = tfModel.getRoads();
		for (Road road : roads) {
			String plateNo = randomGenerator.nextInt() + "";
			Car car = new Car(plateNo);
			road.addCar(car);
		}
	}

	/**
     * Process with given traffic model and traffic light.
     * @param impl represents logic for traffic . 
     * @return List<Road> list of roads after applying traffic rules
     */
	public List<Road> process(BiFunction<TrafficModel, TrafficLight, List<Road> > biFunction,
			TrafficModel tfm, TrafficLight light) {		
		return biFunction.apply(tfm,light);
	}
	
	/**
     * Apply traffic rule on a given traffic model 
     * @param tfm Traffic model. 
     * @param light Traffic light. 
     * @return List<Road> list of roads after applying traffic rules
     */
	public List<Road> applyTrafficRule(TrafficModel tfm, TrafficLight light){
		
		List<Road> roads = tfm.getRoads();
		for (Road rd : roads) {

			Direction roadDir = rd.getDirection();
			Signal signal = light.getSignal(roadDir);
			boolean isGreen = signal == Signal.GREEN;
			boolean isRed = signal == Signal.RED;

			if (clock < signalG2G && clock > 0 && isGreen) {
				rd.removeCar();
			}
			if (clock >= signalG2G && clock % signalG2G >= 2 && isGreen) {
				rd.removeCar();
			}
			if (clock >= signalG2G && clock % signalG2G == 0 && isGreen) {
				light.changeSignal(roadDir, Signal.RED);
			}
			if (clock % signalG2G == 0 && rd.getTotalCars() >= 3 && light.getSignal(roadDir) == Signal.RED) {
				rd.removeCar(3);
			}
			if (clock >= signalG2G && clock % signalG2G == 0 && isRed) {
				light.changeSignal(roadDir, Signal.GREEN);
			}
		}
		return roads;
	}
	
	/**
     * Run the given traffic simulation with given
     * runCycle = 20 and wait time 1 second    
     */
	public static void main(String[] args) throws Exception {
		TrafficSimulator sim = new TrafficSimulator(20, 1000);
		sim.runSimulation();
	}

}
