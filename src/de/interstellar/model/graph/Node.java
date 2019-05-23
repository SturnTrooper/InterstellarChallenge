package de.interstellar.model.graph;

import java.util.ArrayList;
import java.util.LinkedList;

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
	private ArrayList<Node> neighbors;
	
	private LinkedList<Node> shortestPath;
	private double totalCost;
	
	public Node(int pId, String pName) {
		
		this.id = pId;
		this.name = pName;
		this.neighbors = new ArrayList<Node>();
		 //Initialize the total cost with ifinity
		this.totalCost = Double.MAX_VALUE;
		this.shortestPath = new LinkedList<Node>();
				
	}
	
	public void setName(String pName) {
		this.name = pName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Node> getNeighbors(){
		return this.neighbors;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getTotalCost() {
		return this.totalCost;
	}
	
	public void updateTotalCost(double cost) {
		this.totalCost = cost;
	}
	
	public LinkedList<Node> getShortestPath(){
		return this.shortestPath;
	}
	
	public void updateShortestPath(LinkedList<Node> path) {
		this.shortestPath = path;
	}
	
	

}
