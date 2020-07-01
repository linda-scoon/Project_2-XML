import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Flourish Task 4
 */
public class WeatherApp2 {

	private String bbcUrlString;
	private URL url;
	private String weatherReport;
	private String[] state;
	private DocumentBuilder builder;
	private Document doc;
	private NodeList nlist;
	private DocumentBuilderFactory factory;
	private int geonameId;

	public WeatherApp2() {

	}

	/**
	 * Retrieves geonameId from the geonames api
	 * 
	 * @param location
	 */
	public void retrieveLocation(String location) {
		String apiUrlString = "http://api.geonames.org/search?q=" + location + "&maxRows=1&lang=en&username=lns18qlr";
		try {
			url = new URL(apiUrlString);
			// the DOM parser// instatiating a factory
			factory = DocumentBuilderFactory.newInstance();

			// using the factory to instatiate a builder that contains the xml parser
			builder = factory.newDocumentBuilder();
			doc = builder.parse(url.openStream());// parsing the URL
			doc.getDocumentElement().normalize();
			nlist = doc.getElementsByTagName("geoname");

			Node node = nlist.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				geonameId = Integer.parseInt(e.getElementsByTagName("geonameId").item(0).getTextContent());
				setBbcUrlString(geonameId);
			}
		} catch (MalformedURLException e) {
			System.err.println("INVALID API URL");
		} catch (ParserConfigurationException e1) {
		} catch (SAXException e1) {
		} catch (IOException e1) {
		}
	}

	/**
	 * Retrieves weather report from the BBC rss feed
	 */
	public void retrieveReport() {
		try {
			url = new URL(getBbcUrlString());

			// the DOM parser// instatiating a factory
			factory = DocumentBuilderFactory.newInstance();

			// using the factory to instatiate a builder that contains the xml parser
			builder = factory.newDocumentBuilder();
			doc = builder.parse(url.openStream());// parsing the URL
			doc.getDocumentElement().normalize();// setting result into tree format
			nlist = doc.getElementsByTagName("item");

			Node node = nlist.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				weatherReport = e.getElementsByTagName("title").item(0).getTextContent();
				setState(weatherReport);
			}
		} catch (MalformedURLException e) {
			System.err.println("INVALID URL");
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * Retrieves the weather condition to be passed to the icon selector
	 * 
	 * @param weatherReport
	 */
	private void setState(String weatherReport) {
		state = weatherReport.split(":|,");

		// for debugging
		System.out.println(state[2]);
	}

	/**
	 * @return the weatherReport
	 */
	public String getWeatherReport() {
		return weatherReport;
	}

	/**
	 * @param weatherReport the weatherReport to set
	 */
	public void setWeatherReport(String weatherReport) {
		this.weatherReport = weatherReport;
	}

	/**
	 * Determines which icon to show depending on the weather condition(state)
	 * 
	 * @return icon
	 */
	public ImageIcon getIcon() {
		switch (state[2]) {
		case " Sunny":
			return new ImageIcon("weatherIcons/sunny.png");
		case " Cloudy":
			return new ImageIcon("weatherIcons/cloudy.png");
		case " Light Clouds":
			return new ImageIcon("weatherIcons/light clouds.png");
		case " Windy":
			return new ImageIcon("weatherIcons/windy.png");
		case " Heavy Rain":
			return new ImageIcon("weatherIcons/heavy rain.png");
		case " Light Rain Showers":
			return new ImageIcon("weatherIcons/rain showers_light rain.png");
		case " Snow":
			return new ImageIcon("weatherIcons/snow.png");
		case " Lightning":
			return new ImageIcon("weatherIcons/lightning.png");
		default:
			return new ImageIcon("weatherIcons/default.png");
		}
	}

	/**
	 * @return the bbcUrlString
	 */
	public String getBbcUrlString() {
		return bbcUrlString;
	}

	/**
	 * passes the geoId to te BBC feed to set location
	 * 
	 * @param bbcUrlString the bbcUrlString to set
	 */
	public void setBbcUrlString(int geoId) {
		this.bbcUrlString = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/" + geoId;
	}
}
