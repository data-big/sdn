package zx.soft.sdn.api.domain;

import zx.soft.sdn.model.VPNUser;

/**
 * VPN用户查询业务，传值类。
 * 
 * @author xuran
 *
 */
public class VPNUserVO extends VPNUser {

	/**开始时间**/
	private String startTime;
	/**结束时间**/
	private String endTime;

	public VPNUserVO() {
		super();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
