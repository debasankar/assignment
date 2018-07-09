package com.tf.app;

import java.util.Set;

import com.tf.app.domain.model.Car;
import com.tf.app.domain.model.Direction;
import com.tf.app.domain.model.Road;
import com.tf.app.domain.model.Signal;
import com.tf.app.domain.model.TrafficLight;
import com.tf.app.domain.model.TrafficModel;



public class TrafficSimulator{
    private int cycle;
    private long waitTime;
    private int clock =1;

  
    TrafficSimulator(int cycle, long waitTime) {
        this.cycle = cycle;
        this.waitTime = waitTime;
    }
    
    public static void main(String[] args) throws Exception {
    	TrafficSimulator sim = new TrafficSimulator(20,1000);
    	sim.runSimulation();
	}

 
    protected void runSimulation() throws Exception {
        
    	TrafficModel tfModel = new TrafficModel();
        TrafficLight tfLight = new TrafficLight();    	
    	
        for (int i = 0; i < cycle; ++i) {            

        	 addVehicles(tfModel);
             processStep(tfModel, tfLight);
             
             tfModel.getRoads().stream().map(r -> r.getDirection()+ ""+"=" + r.getTotalVehicles()).reduce((x, y) -> x +"," + y)
		       .ifPresent(s -> System.out.println(clock +"  - "+s));;

            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (Exception ex) {
                }
            }
            clock ++;
        }// end of loop
        
    }
    
    
  void addVehicles(TrafficModel tfModel) throws Exception {
            Car car = new Car();
        	Set<Road> roads =tfModel.getRoads();
        	for (Road road : roads) {
				road.addCar(car);
			}
    }

    
    public void processStep(TrafficModel tfm, TrafficLight light) {
       
        Set<Road> roads = tfm.getRoads();
        for (Road rd : roads) {          
        	
        	Direction roadDir = rd.getDirection();
            
           Signal signal=  light.getSignal(roadDir);
            
        //   System.out.println(clock + "- Direction "+ roadDir+" light " +signal);
            
            if(clock < 4 && clock > 0 && signal == Signal.GREEN){        	
            	//System.out.println(clock + "- Direction "+ roadDir );
            	rd.removeCar();        	
            }  
            
            
           /* if (clock%4 ==0  && signal == Signal.RED){
            	//System.out.println(clock + "- Direction "+ rd.getDirection()+" light " +signal);
            	rd.removeCar();
            }*/
            
            if (clock >= 4 && clock%4 >=2 && signal == Signal.GREEN){
            	//System.out.println(clock + "- Direction "+ rd.getDirection() +" light " +light.getSignal(roadDir) );
            	rd.removeCar(); 
            }
            
            if (clock >= 4 && clock%4 ==0 && signal == Signal.GREEN){
            	//System.out.println(clock + "- Direction "+ rd.getDirection()+" light " +light.getSignal(roadDir));
            	light.changeSignal(roadDir, Signal.RED);
            	//System.out.println(clock + "- Changed Direction "+ rd.getDirection() +" light " +light.getSignal(roadDir));
            }
                  
     /*        if (clock%4 ==0  && signal == Signal.RED){
        	System.out.println(clock + "- Direction "+ rd.getDirection()+" light " +signal);
        	rd.removeCar();
        }*/
            
           if (clock%4 ==0 && rd.getTotalVehicles()>=3 && light.getSignal(roadDir) == Signal.RED){
            	//System.out.println(clock + "- Direction "+ rd.getDirection()+" light " +light.getSignal(roadDir));
            	rd.removeCar();
            }
            
          
           /* if (clock%4 ==0 ){
            	System.out.println(clock + "- Direction "+ rd.getDirection()+" light " +light.getSignal(roadDir));
            	rd.removeCar();
            }*/
          
            if (clock >= 4 && clock%4 ==0 && signal == Signal.RED){
            	//System.out.println(clock + "- Direction "+ rd.getDirection()+" light " +light.getSignal(roadDir) );
            	light.changeSignal(roadDir, Signal.GREEN); 
            	//System.out.println(clock + "- Changed Direction "+ rd.getDirection()+" light " +light.getSignal(roadDir) );
            }
            
        //  System.out.println(clock + "- Changed Direction "+ rd.getDirection() +" light " +light.getSignal(roadDir));
            
           // System.out.println(clock +"- Road - " + rd.getName()+ "::  Direction "+ roadDir + "::  Light Signal : " + light.getSignal(roadDir)+":: Vehicles on Road = " + rd.getTotalVehicles());
        }
    }

}
