package zx.soft.sdn.api.model;

public class Location {
	private Address address;
	private String addressDescription;
	private String longitude;
	private String latitude;
	private String accuracy;

	public Location() {
		super();
	}

	public Location(Address address, String addressDescription, String longitude, String latitude, String accuracy) {
		super();
		this.address = address;
		this.addressDescription = addressDescription;
		this.longitude = longitude;
		this.latitude = latitude;
		this.accuracy = accuracy;
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
