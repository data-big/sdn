package zx.soft.sdn.etl.component;

/**
 * 业务枚举
 * 
 * @author xuran
 *
 */
public enum Business {
	//VPN卡，VPN用户，VPN用户地理位置 
	VPNCARD("vpncard"), VPNUSER("vpnuser"), VPNPOSTION("vpnposition");
	private String value;

	Business(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
