package zx.soft.sdn.api.model;

import java.util.Date;

/**
 * VPN用户信息模型
 * 
 * @author xuran
 *
 */
public class VPNUser {

	/**主键**/
	private String id;
	/**真实号**/
	private String realNumber;
	/**担保人手机号**/
	private String sponsorNumber;
	/**担保人姓名**/
	private String sponsorName;
	/**担保人证件类型**/
	private Integer sponsorIDType;
	/**担保人证件号码**/
	private String sponsorIDNumber;
	/**使用人手机号**/
	private String userNumber;
	/**使用人姓名**/
	private String userName;
	/**使用人证件类型**/
	private Integer userIDType;
	/**使用人证件号码**/
	private String userIDNumber;
	/**开户时间**/
	private Date registerDate;
	/**销户时间**/
	private Date cancelDate;
	/**开户代理商**/
	private String registerAgent;
	/**变更类型：1.实名信息变更 2.IP地址变更 3.销户**/
	private Integer modifyType;
	/**变更时间**/
	private Date modifyDate;

	public VPNUser() {
		super();
	}

	public VPNUser(String id, String realNumber, String sponsorNumber, String sponsorName, Integer sponsorIDType,
			String sponsorIDNumber, String userNumber, String userName, Integer userIDType, String userIDNumber,
			Date registerDate, Date cancelDate, String registerAgent, Integer modifyType, Date modifyDate) {
		super();
		this.id = id;
		this.realNumber = realNumber;
		this.sponsorNumber = sponsorNumber;
		this.sponsorName = sponsorName;
		this.sponsorIDType = sponsorIDType;
		this.sponsorIDNumber = sponsorIDNumber;
		this.userNumber = userNumber;
		this.userName = userName;
		this.userIDType = userIDType;
		this.userIDNumber = userIDNumber;
		this.registerDate = registerDate;
		this.cancelDate = cancelDate;
		this.registerAgent = registerAgent;
		this.modifyType = modifyType;
		this.modifyDate = modifyDate;
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

	public String getSponsorNumber() {
		return sponsorNumber;
	}

	public void setSponsorNumber(String sponsorNumber) {
		this.sponsorNumber = sponsorNumber;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public Integer getSponsorIDType() {
		return sponsorIDType;
	}

	public void setSponsorIDType(Integer sponsorIDType) {
		this.sponsorIDType = sponsorIDType;
	}

	public String getSponsorIDNumber() {
		return sponsorIDNumber;
	}

	public void setSponsorIDNumber(String sponsorIDNumber) {
		this.sponsorIDNumber = sponsorIDNumber;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserIDType() {
		return userIDType;
	}

	public void setUserIDType(Integer userIDType) {
		this.userIDType = userIDType;
	}

	public String getUserIDNumber() {
		return userIDNumber;
	}

	public void setUserIDNumber(String userIDNumber) {
		this.userIDNumber = userIDNumber;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getRegisterAgent() {
		return registerAgent;
	}

	public void setRegisterAgent(String registerAgent) {
		this.registerAgent = registerAgent;
	}

	public Integer getModifyType() {
		return modifyType;
	}

	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}


}
