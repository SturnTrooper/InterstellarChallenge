package de.interstellar.application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONObject;

import de.interstellar.model.data.JSONDataProvider;
import de.interstellar.model.data.JSONParser;
import de.interstellar.model.graph.Graph;
import de.interstellar.model.graph.GraphBuilder;

public class ApplicationMain {
	
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		JSONDataProvider provider = new JSONDataProvider();
		String data = provider.retrieveDataFromUrl("https://www.get-in-it.de/imgs/it/codingCompetition/graph/generatedGraph.json");
		
		JSONParser parser = new JSONParser();
		JSONObject obj = parser.createJSONObject(data);
		
		GraphBuilder gBuilder = new GraphBuilder();
		Graph g = gBuilder.createGraph(obj);
		
		ArrayList<Integer> bla = g.getNodeList().get(99).getNeighbors();
		
		for(int i=0; i < bla.size(); i++) {
			System.out.println(bla.get(i));
		}
		
	
	}

}
