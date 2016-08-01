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
	public final static String[] ANHUI_CITY = new String[] { "合肥", "亳州", "淮北", "宿州", "阜阳", "蚌埠", "淮南", "滁州", "六安", "池州",
			"芜湖", "马鞍山", "安庆", "铜陵", "宣城", "黄山" };
}
