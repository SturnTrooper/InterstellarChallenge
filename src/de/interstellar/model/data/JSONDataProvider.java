package de.interstellar.model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/** This class provides the functionality to obtain data that is formatted in the
 *  JavaScript Object Notation (JSON) file format. 
 *  
 *  The data can be received from an URL or the file system.
 *  
 * @author Florian Sturn
 * @date 14.05.2019
 *
 */
public class JSONDataProvider {
		
	
	/** This function obtains the JSON data from a given website (URL). In order to receive the
	 *  data, a connection will be established. Afterwards an InputStream decodes the bytes into
	 *  characters.
	 *   
	 *  The characters are transformed into text and the result is a String representing JSON data. 
	 * 
	 * @author Florian Sturn
	 * @date 16.05.2019
	 * 
	 * @param pDataUrl => a String representing the URL to the JSON data.
	 * @return => String containing the URL's JSON data.
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String retrieveDataFromUrl(String pDataUrl) throws MalformedURLException, IOException {
		
		//Connect to the given URL
		URLConnection connection = establishDataConnection(pDataUrl);
		
		//Create an InputSteamReader in order to read the bytes from the given connection and
		//decode them into characters based on the UTF-8 charset. 
		InputStreamReader is = new InputStreamReader(connection.getInputStream(),"UTF8");		
		//Create a BufferedReader to read text from the character input stream. 
		BufferedReader br = new BufferedReader(is);
		
		StringBuilder response = new StringBuilder();
		String currentInputLine;
		
		//Read the data and concatenate it.		
		while((currentInputLine = br.readLine()) != null) {
			response.append(currentInputLine);
		}
		
		//Reading done, closing all readers to avoid memory leaks.
		br.close();
		is.close();
		
		return response.toString();
	}
	
	/** This function establishes a connection to the given URL in order to
	 *  retrieve the provided JSON Data.
	 * 
	 * @author Florian Sturn
	 * @date 16.05.2019
	 * 
	 * @param pDataUrl => a String representing the URL to the JSON data
	 * @return => a connection object
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private URLConnection establishDataConnection(String pDataUrl) throws MalformedURLException, IOException {
				
		URL website = new URL(pDataUrl);
		URLConnection connection = website.openConnection();
		
		return connection;
				
	}

}
