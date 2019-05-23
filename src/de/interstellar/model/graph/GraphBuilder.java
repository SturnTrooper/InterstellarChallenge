package de.interstellar.model.graph;


import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import de.interstellar.model.data.JSONParser;

/** This class provides the functionality to build a Graph from a given
 *  input Data. The input data needs to be available in JSON format.
 * 
 * @author Florian Sturn
 * @date 16.05.2019
 *
 */
public class GraphBuilder {
	
	private JSONParser parser;
	private HashMap<Integer,Node> nodes;
	private HashMap<String,Edge> edges;
	
	public GraphBuilder() {
		
		this.parser = new JSONParser();
		this.nodes = new HashMap<Integer,Node>();
		this.edges = new HashMap<String,Edge>();
	}
	
	/** Function to create a Graph based on the given input data. The data is formatted as
	 *  a JSON object that contains two arrays. One containing information about the nodes
	 *  of the graph. The other one about the edges between the nodes.
	 * 
	 * @author Florian Sturn
	 * @date 16.05.2019
	 * 
	 * @param pObject => JSON object containing the information to create a graph
	 * @return the created graph.
	 */
	public Graph createGraph(JSONObject pObject) {
		
		JSONArray nodeArray = pObject.getJSONArray("nodes");
		nodes = createNodeList(nodeArray);
		
		JSONArray edgeArray = pObject.getJSONArray("edges");
		edges = createEdgeList(edgeArray);
		
		Graph g = new Graph(nodes,edges);
		
		return g;
	}
	
	/** This function creates the nodes of the Graph and adds them
	 *  to a HashMap.
	 * 
	 * @author Florian Sturn
	 * @date 16.05.2019
	 * 
	 * @param pJsonArray => containing the Nodes of the Graph
	 * @return a list representing the nodes of the Graph
	 */
	private HashMap<Integer,Node> createNodeList(JSONArray pJsonArray){
		
		HashMap<Integer,Node> nodeList = new HashMap<Integer,Node>();
		
		for(int i=0; i < pJsonArray.length(); i++) {
			
			//Retrieve the name of each node, based on the keyword "label"
			String nodeName = parser.retrieveStringValue(pJsonArray.getJSONObject(i), "label");
			//Create the node. The node has a name and an id. Id is the position of the node in the
			//JSON "nodes" array.
			Node node = new Node(i,nodeName);
			nodeList.put(i, node);

		}
		
		return nodeList;
	}
	
	/** This function creates the edges for the graph and adds them to a
	 *  HashMap. As we are dealing with an undirected graph, this function
	 *  not only creates an Edge from node A to node B, but also from 
	 *  Node B to A, with the same costs.
	 *  
	 * @author Florian Sturn
	 * @date 16.05.2019 
	 * 
	 * @param pJsonArray
	 * @return
	 */
	private HashMap<String,Edge> createEdgeList(JSONArray pJsonArray){
		
		HashMap<String,Edge> edgeList = new HashMap<String,Edge>();
		
		for(int i=0; i < pJsonArray.length(); i++) {
			
			//Retrieve the current edge data (source, target, costs) from the "edges" JSON Array
			JSONObject currentEdgeData = pJsonArray.getJSONObject(i);

			//Parse out the ids of the source and target nodes
			int sourceID = parser.retrieveIntegerValue(currentEdgeData, "source");
			int destinationID = parser.retrieveIntegerValue(currentEdgeData, "target");
			
			//Parse out the costs for that edge.
			double cost = parser.retrieveDoubleValue(currentEdgeData, "cost");
			
			//Obtain the source and target nodes from the graphs node HashMap
			Node sourceNode = nodes.get(sourceID);
			Node destinationNode = nodes.get(destinationID);
			
			//Update the neighbor list of the source and target node. Also update the
			//target as we are dealing with an undirected Graph.			
			if(!sourceNode.getNeighbors().contains(destinationNode)) {
				sourceNode.getNeighbors().add(destinationNode);
				//nodes.put(sourceID, sourceNode);
			}
			
			if(!destinationNode.getNeighbors().contains(sourceNode)) {
				destinationNode.getNeighbors().add(sourceNode);
				//nodes.put(destinationID, destinationNode);
			}
						
			//Create an edge based on the given source, target and costs. The name of the edge
			//is build by id of source and target.
			Edge sourceToDestinationEdge = new Edge(sourceID + "To" + destinationID,sourceNode,destinationNode,cost);
			edgeList.put(sourceToDestinationEdge.getName(), sourceToDestinationEdge);
			
			//System.out.println(sourceToDestinationEdge.getName());
			
			//As the graph is undirected we also create a edge for the reverse direction (target to source) with
			//the same cost given by the data.
			Edge destinationToSourceEdge = new Edge(destinationID + "To" + sourceID,destinationNode,sourceNode,cost);
			edgeList.put(destinationToSourceEdge.getName(), destinationToSourceEdge);
			
			//System.out.println(destinationToSourceEdge.getName());
		
		}
				
		return edgeList;		
	}

}
