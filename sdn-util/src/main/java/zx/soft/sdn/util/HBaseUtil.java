package zx.soft.sdn.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
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

}
