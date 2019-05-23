package de.interstellar.model.graph;

/** This class represents an Edge. An edge connects two nodes
 *  with each other. Each edge has a source node, a target node
 *  a name and a cost, which tells us how expensive it its to get
 *  from the target to the source.
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
