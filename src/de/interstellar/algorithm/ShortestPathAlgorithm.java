package de.interstellar.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import de.interstellar.model.graph.Edge;
import de.interstellar.model.graph.Graph;
import de.interstellar.model.graph.Node;

/** This class provides the functionality to calculate the shortest path
 *  from a given source to every other node in a given undirected graph.
 *  
 * 
 *  The algorithm works in the following basic steps:
 *  	- As long as there are unvisited nodes in the graph:
 *  		- Choose the node S with the smallest total costs
 *  		- Mark this node as visited
 *  		- For each neighbor N of S which is unvisited do:
 *  			If total cost of S + the cost of the edge that leads to N is
 *  			smaller then the total cost of then update the total cost of N
 *  			to the new value, as we found a better path to N
 *  			
 *  			Mark this node as unvisited.
 * 
 * @author Florian Sturn
 * @date 23.05.2019
 *
 */
public class ShortestPathAlgorithm {
	
	private String sourceNodeName;
	private String targetNodeName;
	
	private HashMap<Integer,Node> nodes;
	private HashMap<String,Edge> edges;
	
	
	private HashSet<Node> unvisitedNodes;
	private HashSet<Node> visitedNodes;
	
	
	/** Constructor to initialize the Algorithm with the needed data as well
	 *  as the required data structures.
	 *  
	 * 
	 * @author Florian Sturn
	 * @date 23.05.2019
	 * 
	 * @param pGraph => Graph on which the Algorithm will work
	 * @param sourceName => the node from which we calculate the distances/costs
	 * @param targetName => the node we are interested in
	 */
	public ShortestPathAlgorithm(Graph pGraph, String sourceName, String targetName) {
		
		//Set the source and target for the algorithm
		this.sourceNodeName = sourceName;
		this.targetNodeName = targetName;
		
		//Create a copy of the edges and nodes in order to work on graphs data.
		this.nodes = new HashMap<Integer,Node>(pGraph.getNodeList());
		this.edges = new HashMap<String,Edge>(pGraph.getEdgeList());
		
		//Create HashSet in order to keep track of nodes for which the calculations
		//are done (visited nodes) and those who still need to be calculated (unvisited nodes)
		this.unvisitedNodes = new HashSet<Node>();
		this.visitedNodes = new HashSet<Node>();
						
	}
	
	/** This function calculates the shortest path between the source node
	 *  and target node of a undirected graph. The result contains information
	 *  about the path (which nodes lead to the target and in which order), as 
	 *  well as the total costs to get there.
	 *  
	 *  The function returns NULL, if a given source node is not contained in
	 *  the given graph.
	 *  
	 * @author Florian Sturn
	 * @date 23.05.2019 
	 * 
	 * @return => result of the Algorithm
	 */
	public ShortestPathResult calculateShortestDistance() {
		
		//Set the start node from which the algorithm will start to calculate
		//the distances/costs to each node of the graph.
		Node startNode = findNodeByName(this.sourceNodeName);
		
		if(startNode != null) {
			
			//Initialize the algorithm by setting the distance/cost of the source node
			//to zero and adding it to the unvisited nodes.
			startNode.updateTotalCost(0);
			this.unvisitedNodes.add(startNode);
			
			//Run the algorithm as long as there are unvisited nodes in the graph.
			while(this.unvisitedNodes.size() > 0) {
				
				//Select the node to which we move next
				Node currentNode = findNodeWithMinimalCost(this.unvisitedNodes);
				//Add the chosen node to the visited lists, as we are now working on it
				//and remove it from the unvisited node list.
				this.visitedNodes.add(currentNode);
				this.unvisitedNodes.remove(currentNode);
				//Calculate/update the costs to the neighbors of the node we are
				//currently working on
				calculateMinimalCosts(currentNode);			
			}
			
			//Get the target node
			Node targetNode = findNodeByName(this.targetNodeName);
			//Create the result based on the target node, for which we want to have
			//the shortest path starting from the source node.
			ShortestPathResult result = new ShortestPathResult(startNode.getName(),targetNode.getName(),
					targetNode.getTotalCost(),targetNode.getShortestPath());
			
			return result;
			
		} else {
			//Return null as a result as the source node is not present in the given
			//graph.
			return null;
		}
				
	}
	
	/** This function checks if a node with the given name exists in the graph.
	 *  If this is the case, then the node will be returned. If a node does not
	 *  exist, then NULL will be returned.
	 *  
	 * @author Florian Sturn
	 * @date 23.05.2019 
	 * 
	 * @param pNodeName
	 * @return => node that has the given name or NULL if node does not exist.
	 */
	private Node findNodeByName(String pNodeName) {
		
		Iterator<Map.Entry<Integer,Node>> iter = nodes.entrySet().iterator();
		Node resultNode = null;
		
		while(iter.hasNext() && resultNode == null) {
			
			Map.Entry<Integer, Node> pair = iter.next();
			
			if(pair.getValue().getName().equals(pNodeName)) {
				resultNode = pair.getValue();
			}
		}
		
		return resultNode;		
	}
	
	/** This function determines the Node with the smallest cost from
	 *  all the Nodes of the Graph that have not been visited so far. 
	 * 
	 * @param pNodeSet => all the nodes that have not been visited
	 * @return => node with the smallest costs
	 */
	private Node findNodeWithMinimalCost(HashSet<Node> pNodeSet) {
		
		//Initialize variable to store the result
		Node resultNode = null;
		//Set the starting value for the minimal costs
		double minimalCosts = Double.MAX_VALUE;
		
		//Loop through all the unvisited nodes of the Graph.
		for(Node currentNode: pNodeSet) {
			
			double currentNodeCost = currentNode.getTotalCost();
			
			//Check if the costs of the currentNode are smaller than the 
			//minimal costs. If yes update the value of the minimal costs
			//as well as the value for the node with the most minimal costs
			if(currentNodeCost < minimalCosts) {
				minimalCosts = currentNodeCost;
				resultNode = currentNode;
			}			
		}
	
		return resultNode;
	}
	
	/** This function calculates and updates the costs of each neighbor of the given
	 *  source node. Also the path by which the neighbor is reachable and its order 
	 *  will be updated.
	 *  
	 *  If a neighbor node is seen for the first time, it will be added to the list
	 *  of unvisited nodes.
	 *  
	 * @author Florian Sturn
	 * @date 23.05.2019 
	 * 
	 * @param sourceNoden=> the node the algorithm is working on at its current
	 * 						iteration.
	 */
	private void calculateMinimalCosts(Node sourceNode) {
		
		//Obtain the neighbors of the source node (the node the algorithm is currently
		//working on).
		ArrayList<Node> neighbors = sourceNode.getNeighbors();
		
		//Loop through the neighbors.
		for(int i=0; i < neighbors.size(); i++) {
			
			//Obtain the neighbor an check if it was already visited. If the 
			//neighbor node was already visited then the node is finished and 
			//does not need to be updated. 
			Node currentTargetNode = neighbors.get(i);
			
			if(!this.visitedNodes.contains(currentTargetNode)) {
				
				//Build the name of the edge that leads from the source to the target
				String currentEdgeName = sourceNode.getId() + "To" + currentTargetNode.getId();
				//Retrieve the edge from the edge list and get its costs.
				Edge currentEdge = edges.get(currentEdgeName);
				double edgeCost = currentEdge.getCost();
				
				//Check if the costs from the source + the costs of the edge are smaller then
				//the total cost of the target node. If this is the case, we have found a better
				//solution for the shortest path of the current target node. 
				if(sourceNode.getTotalCost() + edgeCost < currentTargetNode.getTotalCost()) {
					
					//As we found a better solution we update the total costs to the current
					//target node as well as the path (order of the nodes) which lead to
					//the target.
					currentTargetNode.updateTotalCost(sourceNode.getTotalCost() + edgeCost);
					LinkedList<Node> shortestPath = new LinkedList<Node>(sourceNode.getShortestPath());
					shortestPath.add(sourceNode);
					currentTargetNode.updateShortestPath(shortestPath);
											
				}
				//Add the node to the list of unvisited nodes. As we use a HashSet,
				//the node will only be added if it is not alreay present in the
				//list.
				this.unvisitedNodes.add(currentTargetNode);								
			}							
		}		
	}
	
}
