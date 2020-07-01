import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Flourish
 *
 */
public class WeatherApp3Test {
	private WeatherApp3 weatherApp3;

	@Before
	public void before() {
		weatherApp3 = new WeatherApp3();
	}

	/**
	 * Test method for {@link WeatherApp3#retrieveLocation(java.lang.String)}.
	 */
	@Test
	public void testRetrieveLocation() {

		// if location == null or empty: should return 404 code
		weatherApp3.retrieveLocation(null);
		assertEquals("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/6269134",
				weatherApp3.getBbcUrlString());

		// if location is valid
		weatherApp3.retrieveLocation("bangor");
		assertEquals("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2656396",
				weatherApp3.getBbcUrlString());
	}

	/**
	 * Test method for {@link WeatherApp3#retrieveReport()}.
	 */
	@Test
	public void testRetrieveReport() {
		// if urlString == null or empty
		try {
			weatherApp3.setBbcUrlString(null);
			weatherApp3.retrieveReport();

		} catch (Throwable t) {
			fail("exception should have been handled");
		}

		// if url is invalid
		try {
			weatherApp3.setBbcUrlString("123");
			weatherApp3.retrieveReport();
		} catch (Throwable t) {
			fail("exception should have been handled");
		}

		// if url is incomplete
		try {
			weatherApp3.setBbcUrlString("https://");
			weatherApp3.retrieveReport();
		} catch (Throwable t) {
			fail("exception should have been handled");
		}

		// if url is missing geoID
		weatherApp3.setBbcUrlString("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/");
		weatherApp3.retrieveReport();
		assertNull(weatherApp3.getWeatherReport());

		// if url is valid
		weatherApp3.setBbcUrlString("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2656397");
		weatherApp3.retrieveReport();
		assertNotNull(weatherApp3.getWeatherReport());
	}

	/**
	 * Test method for {@link WeatherApp3#getIcon()}.
	 */
	@Test
	public void testGetIcon() {
		// if report is null or empty
		try {
			weatherApp3.setWeatherReport(null);
			weatherApp3.getIcon();
			fail("should throw NullPointerException");
		} catch (Throwable t) {
			assertTrue(t instanceof NullPointerException);
		}
		// if report is valid
		weatherApp3.retrieveLocation("bangor");
		weatherApp3.retrieveReport();
		assertTrue(weatherApp3.getIcon() instanceof ImageIcon);
	}

	@Test
	public void testGetDescParams() {
		// if report is null or empty
		weatherApp3.setWeatherReport(null);
		assertNull(weatherApp3.getDescParams());

		// if report is valid
		weatherApp3.retrieveLocation("bangor");
		weatherApp3.retrieveReport();
		assertTrue(weatherApp3.getDescParams().length == 7);
	}

	@Test
	public void testStaxParser() {
		fail();
	}
}
