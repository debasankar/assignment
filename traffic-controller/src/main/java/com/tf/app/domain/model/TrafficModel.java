package com.tf.app.domain.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


public class TrafficModel {

    private Road roadNS;
    private Road roadSN;
    private Road roadWE;
    private Road roadEW;
  
    
    /**
     * Model with 4 roads.
   
     */
    public TrafficModel() {
        roadNS = new Road("Snell Rd",Direction.N);
        roadSN = new Road("Snell Rd",Direction.S);
        roadWE = new Road("Weaver Rd",Direction.W);
        roadEW = new Road("Weaver Rd",Direction.E);      
    }   

    /**
     * Obtains a road based on direction.
     * @param dir Direction.
     * @return Road.
     * @throws NoSuchElementException If no road going that direction.
     */
    public Road getRoad(Direction dir) {
        Road[] roads = new Road[] {roadNS, roadSN, roadEW, roadWE};
        for (Road r : roads) {
            if (r.getDirection() == dir) {
                return r;
            }
        }
        throw new NoSuchElementException();
    }

    
    /**
     * Obtains all the roads in this traffic model
     * 
     * @return	a set of roads
     */
    public Set<Road> getRoads() {
    	Road[] roads = new Road[] {roadNS, roadSN, roadEW, roadWE};
    	Set<Road> rs = new HashSet<Road>(Arrays.asList(roads));
    	return rs;
    }

}
