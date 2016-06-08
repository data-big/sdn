package zx.soft.sdn.model;

/**
 * 地址信息模型
 * 
 * @author xuran
 *
 */
public class Address {

	/**主键**/
	private String id;
	/**国**/
	private String country;
	/**省**/
	private String region;
	/**市**/
	private String city;
	/**县**/
	private String county;
	/**街**/
	private String street;
	/**街道号**/
	private String street_number;

	public Address() {
		super();
	}

	public Address(String id, String country, String region, String city, String county, String street,
			String street_number) {
		super();
		this.id = id;
		this.country = country;
		this.region = region;
		this.city = city;
		this.county = county;
		this.street = street;
		this.street_number = street_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
