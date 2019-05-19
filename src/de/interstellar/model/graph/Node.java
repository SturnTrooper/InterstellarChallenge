package de.interstellar.model.graph;

import java.util.ArrayList;

/** This class represents a Node. A node consists of a unique name
 *  and a list of all its neighbor Nodes. This means all the nodes
 *  to which the specific node is connected to.
 * 
 * @author Florian Sturn
 * @date 16.05.2019
 *
 */
public class Node {
	
	private int id;
	private String name;
	private ArrayList<Integer> neighbors;
	
	public Node(int pId, String pName) {
		
		this.id = pId;
		this.name = pName;
		this.neighbors = new ArrayList<Integer>();
				
	}
	
	public void setName(String pName) {
		this.name = pName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Integer> getNeighbors(){
		return this.neighbors;
	}
	
	public int getId() {
		return this.id;
	}
	
	

}
