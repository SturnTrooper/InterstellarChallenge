package de.interstellar.model.graph;

/**
 * 
 * 
 * @author Florian Sturn
 * @date 16.05.2019
 *
 */
public class Edge {
	
	private String name;
	private double cost;
	private Node startNode;
	private Node destinationNode;
	
	public Edge(String pName, Node pStart, Node pDestination, double pCost){
		
		this.name = pName;
		this.startNode = pStart;
		this.destinationNode = pDestination;
		this.cost = pCost;
				
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public Node getStartNode() {
		return this.startNode;
	}
	
	public Node getDestinatonNode() {
		return this.destinationNode;
	}
	
}
