package com.tf.app.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a traffic model with four direction.
 */
public class TrafficModel {

    private Road roadNS;
    private Road roadSN;
    private Road roadWE;
    private Road roadEW;
  
   
    public TrafficModel() {
        roadNS = new Road("Snell Rd",Direction.N);
        roadSN = new Road("Snell Rd",Direction.S);
        roadWE = new Road("Weaver Rd",Direction.W);
        roadEW = new Road("Weaver Rd",Direction.E);      
    }   

    /**
     * Get a road based on direction.
     * @param dir Direction of the flow of traffic.
     * @return Road.
     * @throws NoSuchElementException If no road going that direction.
     */
    public Road getRoad(Direction dir) {
        Road[] roads = new Road[] {roadNS, roadSN, roadEW, roadWE};
        for (Road rd : roads) {
            if (rd.getDirection() == dir) {
                return rd;
            }
        }
        throw new NoSuchElementException("no road going this direction");
    }

    
    /**
     * Get all the roads in this traffic model
     * 
     * @return	a List all of roads
     */
    public List<Road> getRoads() {
    	Road[] roads = new Road[] {roadNS, roadSN, roadEW, roadWE};
    	List<Road> rs = new ArrayList<Road>(Arrays.asList(roads));
    	return rs;
    }

}
