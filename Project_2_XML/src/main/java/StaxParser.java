import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Flourish Tak 6
 */

public class StaxParser {

	private URL url;
	private int geoId;
	private boolean found;
	private ArrayList<Search> searches;

//	public static void main(String args[]) {
//		StaxParser st = new StaxParser();
//		st.parse("redruth");
//	}

	public StaxParser() {
		this.geoId = 0;
		this.found = false;
		searches = new ArrayList<>();
	}

	/**
	 * parses the xml with stax
	 * 
	 * @param location
	 */
	public void parse(String location) {
		String apiUrlString = "http://api.geonames.org/search?q=" + location + "&maxRows=1&lang=en&username=lns18qlr";
		this.found = false;
		try {
			url = new URL(apiUrlString);
			// creating the parser from a factory
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader parser = factory.createXMLStreamReader(url.openStream());

			// iterating through xml document
			while (parser.hasNext()) {
				/*
				 * the next() method returns an interger that corresponds with an element type
				 * XMLStreamConstants is an interface that matches the numbers with a type
				 */
				int e = parser.next();
				if (e == XMLStreamConstants.START_ELEMENT) {
					/*
					 * after checking that the current element in the iteration is a startelement
					 * then check that the localname == desired nametag
					 */
					if (parser.getLocalName().equals("geonameId")) {
						geoId = Integer.parseInt(parser.getElementText());
						setFound(true);

						// for debugging
						System.out.println(geoId + "|" + found);
					}
				}
			}
			write(location);
		} catch (XMLStreamException | IOException e) {
		}
	}

	/**
	 * writes searches to file
	 * 
	 * @param location
	 */
	private void write(String location) {
		searches.add(new Search(new Date(), location, isFound(), getGeoId()));

		// http://tutorials.jenkov.com/java-xml/stax-xmlstreamwriter.html 
		try {
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("geoData.xml"));

			writer.writeStartDocument();
			writer.writeStartElement("weatherSearches");

			for (Search search : searches) {
				writer.writeStartElement("search");
				writer.writeAttribute("date", search.getDate() + "");
				writer.writeStartElement("term");
				writer.writeCharacters(search.getLocation());
				writer.writeEndElement();
				writer.writeStartElement("found");
				writer.writeCharacters(search.isFound() + "");
				writer.writeEndElement();
				writer.writeStartElement("geonameId");
				writer.writeCharacters(search.getGeoId() + "");
				writer.writeEndElement();
			}

			writer.writeEndElement();
			writer.writeEndDocument();

			writer.flush();
			writer.close();

		} catch (XMLStreamException | IOException e) {
		}

	}

	/**
	 * @return the geoId
	 */
	public int getGeoId() {
		return geoId;
	}

	/**
	 * @return the found
	 */
	public boolean isFound() {
		return found;
	}

	/**
	 * @param found the found to set
	 */
	public void setFound(boolean found) {
		this.found = found;
	}
}
