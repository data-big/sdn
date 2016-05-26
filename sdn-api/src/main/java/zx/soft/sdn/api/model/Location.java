package zx.soft.sdn.api.model;

/**
 * 基站位置信息模型
 * 
 * @author xuran
 *
 */
public class Location {

	/**主键**/
	private String id;
	/**基站SAC信息**/
	private String sac;
	/**基站LAC信息**/
	private String lac;
	/**地址信息**/
	private Address address;
	/**地址描述**/
	private String addressDescription;
	/**经度**/
	private String longitude;
	/**纬度**/
	private String latitude;
	/**精确度**/
	private String accuracy;

	public Location() {
		super();
	}

	public Location(String id, String sac, String lac, Address address,
			String addressDescription, String longitude, String latitude, String accuracy) {
		super();
		this.id = id;
		this.sac = sac;
		this.lac = lac;
		this.address = address;
		this.addressDescription = addressDescription;
		this.longitude = longitude;
		this.latitude = latitude;
		this.accuracy = accuracy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSac() {
		return sac;
	}

	public void setSac(String sac) {
		this.sac = sac;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

}
