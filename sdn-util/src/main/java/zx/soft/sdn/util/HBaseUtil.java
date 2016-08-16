package zx.soft.sdn.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HBase工具类
 * 
 * @author xuran
 *
 */
public class HBaseUtil {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(HBaseUtil.class);

	/**
	 * 私有实例
	 */
	private static HBaseUtil instance;

	/**
	 * HBase配置信息
	 */
	private static Configuration configuration;

	/**
	 *构造方法
	 */
	private HBaseUtil() {
		Properties config = ConfigUtil.getProps("hbase.properties");
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", config.getProperty("hbase.cluster.host"));
		configuration.setInt("hbase.zookeeper.property.clientPort",
				Integer.valueOf(config.getProperty("hbase.cluster.port")));
	}

	/**
	 * 获取HBaseUtil单例对象
	 * @return HBase工具类单例对象
	 */
	public static HBaseUtil getInstance() {
		if (null == instance) {
			synchronized (HBaseUtil.class) {
				if (null == instance)
					instance = new HBaseUtil();
			}
		}
		return instance;
	}

	/**
	 * 添加数据
	 * @param tableName 表名
	 * @param rowKey 行键
	 * @param family 列簇
	 * @param qualifier 列名
	 * @param value 值
	 * @return 成功或失败
	 */
	public boolean put(String tableName, String rowKey, String family, String qualifier, String value) {
		try {
			Table table = null;
			Put put = null;
			Connection connection = null;
			try {
				connection = ConnectionFactory.createConnection(configuration);
				table = connection.getTable(null == tableName ? null : TableName.valueOf(tableName));
				put = new Put(null == rowKey ? null : Bytes.toBytes(rowKey));
				Cell cell = new KeyValue(null == rowKey ? null : Bytes.toBytes(rowKey),
						null == family ? null : Bytes.toBytes(family),
						null == qualifier ? null : Bytes.toBytes(qualifier),
						null == value ? null : Bytes.toBytes(value));
				put.add(cell);
				table.put(put);
				return true;
			} finally {
				put = null;
				if (null != table) {
					table.close();
					table = null;
				}
				if (null != connection) {
					connection.close();
					connection = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		}
		return false;
	}

	/**
	 * 根据行键范围查询
	 * @param tableName 表名
	 * @param family 列簇
	 * @param qualifiers [] 列名数组
	 * @param startKey 开始行键
	 * @param endKey 结束行键
	 * @return List集合
	 */
	public List<Map<String, Object>> scan(String tableName, String family, String[] qualifiers, String startKey,
			String endKey) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		Table table = null;
		Scan scan = null;
		try {
			connection = ConnectionFactory.createConnection(configuration);
			table = connection.getTable(null == tableName ? null : TableName.valueOf(tableName));
			scan = new Scan();
			scan.setStartRow(Bytes.toBytes(startKey));
			scan.setStopRow(Bytes.toBytes(endKey));
			ResultScanner resultScanner = table.getScanner(scan);
			for (Result result : resultScanner) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (String qualifier : qualifiers) {
					map.put(qualifier,
							Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier))));
				}
				mapList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
		} finally {
			scan = null;
			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
				} finally {
					table = null;
				}
			}
			if (null != connection) {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Exception : {}", ExceptionUtil.exceptionToString(e));
				} finally {
					connection = null;
				}
			}
		}
		return mapList;
	}

}
