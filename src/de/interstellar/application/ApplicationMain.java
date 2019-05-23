package de.interstellar.application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import org.json.JSONObject;

import de.interstellar.algorithm.ShortestPathAlgorithm;
import de.interstellar.algorithm.ShortestPathResult;
import de.interstellar.model.data.JSONDataProvider;
import de.interstellar.model.data.JSONParser;
import de.interstellar.model.graph.Graph;
import de.interstellar.model.graph.GraphBuilder;
import de.interstellar.model.graph.Node;


/** This class represents the main entry point of the Application.
 *  
 * 
 * @author Florian Sturn
 * @date 23.05.2019
 *
 */
public class ApplicationMain {
	
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		//Receive the data
		JSONDataProvider dataProvider = new JSONDataProvider();
		String data = dataProvider.retrieveDataFromUrl("https://www.get-in-it.de/imgs/it/codingCompetition/graph/generatedGraph.json");
		
		//Parse the data
		JSONParser parser = new JSONParser();
		JSONObject jsonData = parser.createJSONObject(data);
		
		//Create a graph out of the given data
		GraphBuilder graphBuilder = new GraphBuilder();
		Graph g = graphBuilder.createGraph(jsonData);
		
		//Calculate the shortest distance between the source node and the target node
		ShortestPathAlgorithm sp = new ShortestPathAlgorithm(g,"Erde","b3-r7-r4nd7");
		ShortestPathResult algorithmResult = sp.calculateShortestDistance();
		
		if(algorithmResult != null) {
			
			//Pretty print the result to the console
			String sourceNode = algorithmResult.getSourceNodeName();
			String targetNode = algorithmResult.getTargetNodeName();
			
			LinkedList<Node> shortestPath = algorithmResult.getShortestPath();
			
			StringBuilder strBuilder = new StringBuilder();
			
			for(int i=0; i < shortestPath.size(); i++) {
				strBuilder.append(shortestPath.get(i).getName());
				strBuilder.append(" -> ");
			}
			
			strBuilder.append(targetNode);
			
			System.out.println("----------- RESULT -----------");
			System.out.println("");
			System.out.println("Startplanet: " + sourceNode);
			System.out.println("Zielplanet: " + targetNode);
			System.out.println("");
			System.out.println("Gesamtkosten: " + algorithmResult.getTotalCost());
			System.out.println("");
			System.out.println("Kürzester Pfad: " + strBuilder.toString());
			
		} else {
		
			System.out.println("----------- RESULT -----------");
			System.out.println("");
			System.out.println("Kein Ergebnis ermittelbar. Der Startknoten existiert nicht");
		
		}
	}
}
