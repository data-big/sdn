package zx.soft.sdn.api.model;

/**
 * VPN用户地理位置信息模型
 * 
 * @author xuran
 *
 */
public class VPNPostion {

	/**主键**/
	private String id;
	/**真实号**/
	private String realNumber;
	/**基站SAC信息或CELLID信息：参考数据34162**/
	private String sac;
	/**基站LAC信息：参考数据25840**/
	private String lac;
	/**时间：格式2016-01-01 00:00:00**/
	private String time;
	/**基站位置信息**/
	private Location location;

	public VPNPostion() {
		super();
	}

	public VPNPostion(String id, String realNumber, String sac, String lac, String time, Location location) {
		super();
		this.id = id;
		this.realNumber = realNumber;
		this.sac = sac;
		this.lac = lac;
		this.time = time;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealNumber() {
		return realNumber;
	}

	public void setRealNumber(String realNumber) {
		this.realNumber = realNumber;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
