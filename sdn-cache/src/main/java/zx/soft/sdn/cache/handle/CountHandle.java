package zx.soft.sdn.cache.handle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sdn.model.ScatterCount;
import zx.soft.sdn.model.TypeCount;
import zx.soft.sdn.util.ExceptionUtil;
import zx.soft.sdn.util.HiveUtil;
import zx.soft.sdn.util.JsonUtil;
import zx.soft.sdn.util.RedisUtil;

/**
 * 统计缓存
 * 
 * @author xuran
 *
 */
public class CountHandle {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(CountHandle.class);

	/**地理位置分布统计 安徽地市**/
	public final static String[] ANHUI_CITY = new String[] { "合肥市", "亳州市", "淮北市", "宿州市", "阜阳市", "蚌埠市", "淮南市", "滁州市",
			"六安市", "池州市", "芜湖市", "马鞍山市", "安庆市", "铜陵市", "宣城市", "黄山市" };

	/**
	 * 根据时间区间统计安徽地区上网用户分布
	 * @param timeType 时间类型
	 * @param start yyyy-MM-dd HH:mm:ss
	 * @param end yyyy-MM-dd HH:mm:ss
	 * @return 统计结果
	 */
	public static void countPartByAnHui(String timeType, String start, String end) {
		HiveUtil hiveUtil = HiveUtil.getInstance();
		Integer max = null;
		Integer min = null;
		List<TypeCount> data = new ArrayList<TypeCount>();
		for (String city : ANHUI_CITY) {
			Connection connection = hiveUtil.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			if (null != connection) {
				try {
					preparedStatement = connection.prepareStatement(
							"select count(id) from vpnpostion where address like ? and time > ? and time < ?");
					preparedStatement.setString(1, "%" + city + "%");
					preparedStatement.setString(2, start);
					preparedStatement.setString(3, end);
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						TypeCount typeCount = new TypeCount();
						typeCount.setName(city);
						int value = resultSet.getInt(1);
						typeCount.setValue(value);
						data.add(typeCount);
						//求最大值和最小值
						if (null == max)
							max = value;
						if (null == min)
							min = value;
						if (value > max)
							max = value;
						if (value < min)
							min = value;
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
				} finally {
					hiveUtil.close(resultSet, preparedStatement, connection);
				}
			}
		}
		ScatterCount scatterCount = new ScatterCount();
		scatterCount.setMax(max);
		scatterCount.setMin(min);
		scatterCount.setData(data);
		switch (timeType) {
		case "hour":
			RedisUtil.getInstance().set("sdn.user.part.hour", JsonUtil.parseString(scatterCount));
			break;
		case "yesterday":
			RedisUtil.getInstance().set("sdn.user.part.yesterday", JsonUtil.parseString(scatterCount));
			break;
		case "week":
			RedisUtil.getInstance().set("sdn.user.part.week", JsonUtil.parseString(scatterCount));
			break;
		case "month":
			RedisUtil.getInstance().set("sdn.user.part.month", JsonUtil.parseString(scatterCount));
			break;
		}
	}
}
