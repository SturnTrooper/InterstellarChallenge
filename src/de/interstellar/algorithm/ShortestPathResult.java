package de.interstellar.algorithm;

import java.util.LinkedList;

import de.interstellar.model.graph.Node;

/** Simple class to store the result of the shortest path algorithm.
 * 
 * @author Florian Sturn
 * @date 23.05.2019
 *
 */
public class ShortestPathResult {
	
	private String sourceNodeName;
	private String targetNodeName;
	
	private double totalCost;
	private LinkedList<Node> shortestPath;
	
	
	public ShortestPathResult(String pSourceNodeName, String pTargetNodeName, double pTotalCost, LinkedList<Node> pShortestPath) {
		
		this.sourceNodeName = pSourceNodeName;
		this.targetNodeName = pTargetNodeName;
		this.totalCost = pTotalCost;
		this.shortestPath = pShortestPath;
		
	}


	public String getSourceNodeName() {
		return sourceNodeName;
	}


	public void setSourceNodeName(String sourceNodeName) {
		this.sourceNodeName = sourceNodeName;
	}


	public String getTargetNodeName() {
		return targetNodeName;
	}


	public void setTargetNodeName(String targetNodeName) {
		this.targetNodeName = targetNodeName;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	public LinkedList<Node> getShortestPath() {
		return shortestPath;
	}


	public void setShortestPath(LinkedList<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}
	
	

}
