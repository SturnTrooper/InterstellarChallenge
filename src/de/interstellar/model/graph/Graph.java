package de.interstellar.model.graph;

import java.util.HashMap;

/** This class represents a Graph. A Graph consists of Nodes, which are connected
 *  to each other by edges.
 * 
 * @author Florian Sturn
 * @date 16.05.2019
 *
 */
public class Graph {
	
	private HashMap<Integer,Node> nodes;
	private HashMap<String,Edge> edges;
	
	public Graph(HashMap<Integer,Node> pNodeList, HashMap<String,Edge> pEdgeList) {
		
		this.nodes = pNodeList;
		this.edges = pEdgeList;
	}
	
	public HashMap<Integer,Node> getNodeList(){
		return this.nodes;
	}
	
	public HashMap<String,Edge> getEdgeList(){
		return this.edges;
	}

}
