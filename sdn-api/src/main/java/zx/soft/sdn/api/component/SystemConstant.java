package zx.soft.sdn.api.component;

/**
 * 系统常量
 * 
 * @author xuran
 *
 */
public interface SystemConstant {

	/**分页参数：在MAP中传递时的KEY**/
	public final static String PAGE = "page";
	/**响应数据：在MAP中传递时的KEY**/
	public final static String DATA = "data";
	/**VPN用户上网记录解析后缓存在Redis中的键**/
	public final static String CACHE_KEY = "sdn.cache.internetinfo";
	/**VPN用户地理位置信息存储在OpenTSDB中的指标名称**/
	public final static String TSDB_METRIC = "vpn.postion";
	/**地理位置分布统计 安徽地市**/
	public final static String[] ANHUI_CITY = new String[] { "合肥市", "亳州市", "淮北市", "宿州市", "阜阳市", "蚌埠市", "淮南市", "滁州市",
			"六安市", "池州市", "芜湖市", "马鞍山市", "安庆市", "铜陵市", "宣城市", "黄山市" };
}
