package zx.soft.sdn.api.model;

public class Address {

	private String region;
	private String county;
	private String street;
	private String street_number;
	private String city;
	private String country;

	public Address() {
		super();
	}

	public Address(String region, String county, String street, String street_number, String city, String country) {
		super();
		this.region = region;
		this.county = county;
		this.street = street;
		this.street_number = street_number;
		this.city = city;
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
