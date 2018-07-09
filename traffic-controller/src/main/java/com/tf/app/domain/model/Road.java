package com.tf.app.domain.model;

public class Road {
	private String name;
    private VehicleQ<Car> carQ = new VehicleQ<Car>();
    private Direction directn;

    private Road(){};
    
    
    /**
     * Creates a new road with no cars on it.
     * @param directn Direction of road flow.
     * @param name Name of the road
     */
    public Road(String name,Direction directn){
    	this.name = name;
    	this.directn= directn;    	
    }
        
    /**
     * @return The flow of this road.
     */
    public Direction getDirection() {
        return directn;
    }
   
    /**
     * @return The name of this road.
     */
    public String getName() {
        return name;
    }
    
    public void addCar(Car c) {
        if (carQ.cotains(c)) {
            throw new IllegalArgumentException("Cars is already on this road.");
        }        
        carQ.add(c);
    }    
   
    public void removeCar(Car c) {
        if (carQ.cotains(c)) {
        	carQ.remove(c);
        }
    }    
    
    public void removeCar(int position) {
        	carQ.remove(position);
    }    
    
    public void removeCar() {        
    	carQ.remove();       
    }

    public int getTotalCars() {
        return carQ.getTotal();
    }
}
