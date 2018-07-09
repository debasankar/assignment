package com.tf.app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VehicleQ<T> {
	
	private List<T> v = new ArrayList<T>();
	
	public void add(T t){		
		v.add(t);
	}
	
	public void remove(T t){		
		if(v.contains(t)) v.remove(t);
	}	
	
	public void remove(){	
		if(!v.isEmpty()) v.remove(v.size()-1);
	}
	
	public void remove(int position){
		if(!v.isEmpty()) v.remove(v.size()- position);
	}
	
	public int getTotal(){		
		return v.size();
	}
	
	public boolean isEmpty(){		
		return v.isEmpty();
	}
	
	public boolean cotains(T t){		
		return v.contains(t);
	}
}
