package application.Model;

public class Property {

	// Private fields
	private int propertyID;
	private String street;
	private String town;
	private int receptions;
	private String country;
	private String type;
	private int baths;
	private int beds;
	private double price;
	private int userID;


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getReceptions() {
		return receptions;
	}

	public void setReceptions(int receptions) {
		this.receptions = receptions;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBaths() {
		return baths;
	}

	public void setBaths(int baths) {
		this.baths = baths;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Property(int propertyID, String street, String town, int receptions, String country, String type, int baths, int beds,
			double price, int userID) {
		this.propertyID = propertyID;
		this.street = street;
		this.town = town;
		this.receptions = receptions;
		this.country = country;
		this.type = type;
		this.baths = baths;
		this.beds = beds;
		this.price = price;
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "PropertyClass [street=" + propertyID + ", treet=" + street + ", town=" + town + ", receptions=" + receptions + ", country="
				+ country + ", type=" + type + ", baths=" + baths + ", beds=" + beds + ", price=" + price + " userID"+userID+"]";
	}

	public int getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}

}
