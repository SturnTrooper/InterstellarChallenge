package de.interstellar.model.graph;

import java.util.ArrayList;

public class Node {
	
	private String name;
	private ArrayList<String> vertices;
	
	public Node(String pName) {
		
		this.name = pName;
		vertices = new ArrayList<String>();
				
	}
	
	public void setName(String pName) {
		this.name = pName;
	}
	
	public String getName() {
		return this.name;
	}
	
	

}
