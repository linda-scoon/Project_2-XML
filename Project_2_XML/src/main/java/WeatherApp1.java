import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Flourish Task1
 */
public class WeatherApp1 {

	public static void main(String args[]) {
		String urlString = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2648108";
		URL url;
		String weatherReport;

		try {
			url = new URL(urlString);

			// the DOM parser// instatiating a factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			// using the factory to instatiate a builder that contains the xml parser
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url.openStream());// parsing the URL

			doc.getDocumentElement().normalize();

			NodeList nlist = doc.getElementsByTagName("item");
			Node node = nlist.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				weatherReport = e.getElementsByTagName("title").item(0).getTextContent();
				System.out.println(weatherReport);
			}
		} catch (MalformedURLException e) {
			System.err.println("INVALID URL");
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		}
	}
}
