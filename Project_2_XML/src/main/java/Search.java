import java.util.Date;

/**
 * @author Flourish
 *
 */
public class Search {

	private Date date;
	private String location;
	private boolean found;
	private int geoId;

	/**
	 * @param geoId
	 * @param found
	 * @param location
	 * @param date
	 * 
	 */
	public Search(Date date, String location, boolean found, int geoId) {
		this.date = date;
		this.location = location;
		this.found = found;
		this.geoId = geoId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the found
	 */
	public boolean isFound() {
		return found;
	}

	/**
	 * @return the geoId
	 */
	public int getGeoId() {
		return geoId;
	}

}
