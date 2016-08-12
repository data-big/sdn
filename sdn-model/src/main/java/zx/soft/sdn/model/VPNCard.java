package zx.soft.sdn.model;

/**
 * VPN卡信息模型
 * 
 * @author xuran
 *
 */
public class VPNCard {

	/**主键**/
	private String id;
	/**真实号**/
	private String realNumber;
	/**业务IP**/
	private String bizIP;
	/**停用IP**/
	private String stopIP;
	/**特殊IP**/
	private String specialIP;
	/**偏移后业务IP**/
	private String offsetBizIP;
	/**偏移后停机IP**/
	private String offsetStopIP;
	/**偏移后特殊IP**/
	private String offsetSpecialIP;
	/**入库时间 yyyy-MM-dd HH:mm:ss**/
	private String insertDate;
	/**是否有效 0 有效 1无效**/
	private Integer invalid;

	public VPNCard() {
		super();
	}

	public VPNCard(String id, String realNumber, String bizIP, String stopIP, String specialIP, String offsetBizIP,
			String offsetStopIP, String offsetSpecialIP, String insertDate, Integer invalid) {
		super();
		this.id = id;
		this.realNumber = realNumber;
		this.bizIP = bizIP;
		this.stopIP = stopIP;
		this.specialIP = specialIP;
		this.offsetBizIP = offsetBizIP;
		this.offsetStopIP = offsetStopIP;
		this.offsetSpecialIP = offsetSpecialIP;
		this.insertDate = insertDate;
		this.invalid = invalid;
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

	public String getBizIP() {
		return bizIP;
	}

	public void setBizIP(String bizIP) {
		this.bizIP = bizIP;
	}

	public String getStopIP() {
		return stopIP;
	}

	public void setStopIP(String stopIP) {
		this.stopIP = stopIP;
	}

	public String getSpecialIP() {
		return specialIP;
	}

	public void setSpecialIP(String specialIP) {
		this.specialIP = specialIP;
	}

	public String getOffsetBizIP() {
		return offsetBizIP;
	}

	public void setOffsetBizIP(String offsetBizIP) {
		this.offsetBizIP = offsetBizIP;
	}

	public String getOffsetStopIP() {
		return offsetStopIP;
	}

	public void setOffsetStopIP(String offsetStopIP) {
		this.offsetStopIP = offsetStopIP;
	}

	public String getOffsetSpecialIP() {
		return offsetSpecialIP;
	}

	public void setOffsetSpecialIP(String offsetSpecialIP) {
		this.offsetSpecialIP = offsetSpecialIP;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getInvalid() {
		return invalid;
	}

	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}

}
