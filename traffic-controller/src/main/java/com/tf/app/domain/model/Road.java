package com.tf.app.domain.model;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Road {
	private String name;
    private Queue<Car> vehicleQ = new LinkedBlockingQueue<Car> ();
    private Direction directn;

    private Road(){};
    
    public Road(String name,Direction directn){
    	this.name = name;
    	this.directn= directn;    	
    }
        
    public Direction getDirection() {
        return directn;
    }
   
    public String getName() {
        return name;
    }
    
    public void addCar(Car c) {
        if (vehicleQ.contains(c)) {
            throw new IllegalArgumentException("vehicle is already on this road.");
        }        
        vehicleQ.add(c);
    }    
    public void removeCar(Car c) {
        if (vehicleQ.contains(c)) {
        	vehicleQ.remove(c);
        }
    }    
    
    public void removeCar() {        
    	vehicleQ.remove();       
    }

    public int getTotalVehicles() {
        return vehicleQ.size();
    }
    
    
   
	
}
