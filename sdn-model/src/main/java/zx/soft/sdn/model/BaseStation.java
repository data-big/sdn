package zx.soft.sdn.model;

/**
 * 基站位置信息模型
 * 
 * @author xuran
 *
 */
public class BaseStation {

	/**主键**/
	private String id;
	/**基站号**/
	private String cell;
	/**基站SAC**/
	private String sac;
	/**基站LAC**/
	private String lac;
	/**经度**/
	private String lat;
	/**纬度**/
	private String lng;
	/**地址**/
	private String address;

	public BaseStation() {
		super();
	}

	public BaseStation(String id, String cell, String sac, String lac, String lat, String lng, String address) {
		super();
		this.id = id;
		this.cell = cell;
		this.sac = sac;
		this.lac = lac;
		this.lat = lat;
		this.lng = lng;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
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

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
