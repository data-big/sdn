package zx.soft.sdn.api.domain;

/**
 * OpenTSDB 标签数据模型
 * 
 * @author xuran
 *
 */
public class TSDBTags {

	/**VPN用户真是号**/
	private String realNumber;
	/**业务IP**/
	private String bizIP;
	/**VPN用户地理位置基站SAC信息**/
	private String sac;
	/**VPN用户地理位置基站LAC信息**/
	private String lac;

	public TSDBTags() {
		super();
	}

	public TSDBTags(String realNumber, String sac, String lac) {
		super();
		this.realNumber = realNumber;
		this.sac = sac;
		this.lac = lac;
	}

	public String getRealNumber() {
		return realNumber;
	}

	public void setRealNumber(String realNumber) {
		this.realNumber = realNumber;
	}

	public String getBizIP() {
		return bizIP;
	}

	public void setBizIP(String bizIP) {
		this.bizIP = bizIP;
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



}
