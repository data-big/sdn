package zx.soft.sdn.model;

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
	/**ICCID**/
	private String iccid;
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
	private String registerDate;
	/**销户时间**/
	private String cancelDate;
	/**开户代理商**/
	private String registerAgent;
	/**变更时间**/
	private String modifyDate;
	/**身份认证照片一**/
	private String imageOne;
	/**身份认证照片二**/
	private String imageTwo;
	/**身份认证照片三**/
	private String imageThree;
	/**是否有效 0 有效 1 无效**/
	private Integer invalid;

	public VPNUser() {
		super();
	}

	public VPNUser(String id, String realNumber, String iccid, String sponsorNumber, String sponsorName,
			Integer sponsorIDType, String sponsorIDNumber, String userNumber, String userName, Integer userIDType,
			String userIDNumber, String registerDate, String cancelDate, String registerAgent, String modifyDate,
			String imageOne, String imageTwo, String imageThree, Integer invalid) {
		super();
		this.id = id;
		this.realNumber = realNumber;
		this.iccid = iccid;
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
		this.modifyDate = modifyDate;
		this.imageOne = imageOne;
		this.imageTwo = imageTwo;
		this.imageThree = imageThree;
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

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getRegisterAgent() {
		return registerAgent;
	}

	public void setRegisterAgent(String registerAgent) {
		this.registerAgent = registerAgent;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getImageOne() {
		return imageOne;
	}

	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}

	public String getImageTwo() {
		return imageTwo;
	}

	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}

	public String getImageThree() {
		return imageThree;
	}

	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
	}

	public Integer getInvalid() {
		return invalid;
	}

	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}

}
