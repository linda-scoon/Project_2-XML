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
 * @author Flourish Task 2-3
 */
public class WeatherApp {

	private String urlString;
	private URL url;
	private String weatherReport;
	private String[] state;

	public WeatherApp() {

	}

	/**
	 * Retrieves weather report from the BBC rss feed
	 */
	public void retrieveReport() {
		try {
			url = new URL(getUrlString());

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
	 * Retrieves the weather condition for the image icon
	 * 
	 * @param weatherReport
	 */
	private void setState(String weatherReport) {
		weatherReport.toLowerCase();
		state = weatherReport.split(":|,");

		// for debugging
		System.out.println(state[2]);
	}

	/**
	 * @return the urlString
	 */
	public String getUrlString() {
		return urlString;
	}

	/**
	 * @param urlString the urlString to set
	 */
	public void setUrlString(String urlString) {
		this.urlString = urlString;
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
}
