package de.interstellar.test.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import de.interstellar.algorithm.ShortestPathAlgorithm;
import de.interstellar.algorithm.ShortestPathResult;
import de.interstellar.model.graph.Edge;
import de.interstellar.model.graph.Graph;
import de.interstellar.model.graph.Node;

/** Simple test class, to validate the correctness of the algorithm
 * 
 * @author Florian Sturn
 * @date 24.05.2019
 *
 */
public class ShortestPathAlgorithmTest {
	
	
	
	
	@Test
	public void calculateShortestPathTest() {
		
		HashMap<Integer,Node> nodeList = new HashMap<Integer,Node>();
		nodeList.put(0,new Node(0,"node_0"));
		nodeList.put(1,new Node(1,"node_1"));
		nodeList.put(2,new Node(2,"node_2"));
		nodeList.put(3,new Node(3,"node_3"));
		nodeList.put(4,new Node(4,"node_4"));
		nodeList.put(5,new Node(5,"node_5"));
		
		HashMap<String,Edge> edgeList = new HashMap<String,Edge>();
		edgeList.put("0To1", new Edge("0To1",nodeList.get(0),nodeList.get(1),10.0));
		edgeList.put("1To0", new Edge("1To0",nodeList.get(1),nodeList.get(0),10.0));
		edgeList.put("0To2", new Edge("0To2",nodeList.get(0),nodeList.get(2),15.0));
		edgeList.put("2To0", new Edge("2To0",nodeList.get(2),nodeList.get(0),10.0));
		
		nodeList.get(0).getNeighbors().add(nodeList.get(1));
		nodeList.get(0).getNeighbors().add(nodeList.get(2));
		nodeList.get(1).getNeighbors().add(nodeList.get(0));
		nodeList.get(2).getNeighbors().add(nodeList.get(0));
		
		edgeList.put("1To3", new Edge("1To3",nodeList.get(1),nodeList.get(3),12.0));
		edgeList.put("3To1", new Edge("3To1",nodeList.get(3),nodeList.get(1),12.0));
		edgeList.put("1To5", new Edge("1To5",nodeList.get(1),nodeList.get(5),15.0));
		edgeList.put("5To1", new Edge("5To1",nodeList.get(5),nodeList.get(1),15.0));
		
		nodeList.get(1).getNeighbors().add(nodeList.get(3));
		nodeList.get(1).getNeighbors().add(nodeList.get(5));
		nodeList.get(3).getNeighbors().add(nodeList.get(1));
		nodeList.get(5).getNeighbors().add(nodeList.get(1));

		edgeList.put("2To4", new Edge("2To4",nodeList.get(2),nodeList.get(4),10.0));
		edgeList.put("4To2", new Edge("4To2",nodeList.get(4),nodeList.get(2),10.0));
		
		nodeList.get(2).getNeighbors().add(nodeList.get(4));
		nodeList.get(4).getNeighbors().add(nodeList.get(2));
		
		edgeList.put("3To4", new Edge("3To4",nodeList.get(3),nodeList.get(4),2.0));
		edgeList.put("4To3", new Edge("4To3",nodeList.get(4),nodeList.get(3),2.0));
		edgeList.put("3To5", new Edge("3To5",nodeList.get(3),nodeList.get(5),1.0));
		edgeList.put("5To3", new Edge("5To3",nodeList.get(5),nodeList.get(3),1.0));

		nodeList.get(3).getNeighbors().add(nodeList.get(4));
		nodeList.get(3).getNeighbors().add(nodeList.get(5));
		nodeList.get(4).getNeighbors().add(nodeList.get(3));
		nodeList.get(5).getNeighbors().add(nodeList.get(3));
		
		edgeList.put("5To4", new Edge("5To4",nodeList.get(5),nodeList.get(4),5.0));
		edgeList.put("4To5", new Edge("4To5",nodeList.get(4),nodeList.get(5),5.0));
		
		nodeList.get(4).getNeighbors().add(nodeList.get(5));
		nodeList.get(5).getNeighbors().add(nodeList.get(4));
		
		Graph g = new Graph(nodeList,edgeList);
		
		//Calculate the shortest distance between the source node and the target node
		ShortestPathAlgorithm sp = new ShortestPathAlgorithm(g,"node_0","node_5");
		ShortestPathResult result = sp.calculateShortestDistance();
		
		double totalCost = result.getTotalCost();
		
		StringBuilder path = new StringBuilder();
		
		for(int i=0; i < result.getShortestPath().size(); i++) {
			path.append(result.getShortestPath().get(i).getName());
			path.append(" -> ");
		}

		path.append(result.getTargetNodeName());
		
		assertEquals(23.0,totalCost);
		assertEquals("node_0 -> node_1 -> node_3 -> node_5",path.toString());
				
	}
}
