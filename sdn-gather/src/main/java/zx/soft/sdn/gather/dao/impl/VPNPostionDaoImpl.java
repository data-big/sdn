package zx.soft.sdn.gather.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import zx.soft.sdn.gather.dao.VPNPostionDao;
import zx.soft.sdn.model.VPNPostion;
import zx.soft.sdn.util.DateUtil;
import zx.soft.sdn.util.HBaseUtil;
import zx.soft.sdn.util.JDBCUtil;

/**
 * VPN用户地理位置持久层实现
 * 
 * @author xuran
 *
 */
public class VPNPostionDaoImpl implements VPNPostionDao {

	@Override
	public String getAddressInBS(String sac, String lac) {
		JDBCUtil jdbc = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//根据SAC和LAC在基站库中查询详细地址
		try {
			jdbc = JDBCUtil.getInstance();
			connection = jdbc.getBSConnection();
			preparedStatement = connection
					.prepareStatement("SELECT address FROM baseStation WHERE sac=? AND lac=? ORDER BY id LIMIT 0,1");
			preparedStatement.setString(1, sac);
			preparedStatement.setString(2, lac);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Exception";
		} finally {
			jdbc.close(resultSet, preparedStatement, connection);
			resultSet = null;
			preparedStatement = null;
			connection = null;
			jdbc = null;
		}
		//如果SAC+LAC组合查询无数据，则选择CELL+LAC组合查询。****特宽业务要求****
		try {
			jdbc = JDBCUtil.getInstance();
			connection = jdbc.getBSConnection();
			preparedStatement = connection
					.prepareStatement("SELECT address FROM baseStation WHERE cell=? AND lac=? ORDER BY id LIMIT 0,1");
			preparedStatement.setString(1, sac);
			preparedStatement.setString(2, lac);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception";
		} finally {
			jdbc.close(resultSet, preparedStatement, connection);
			resultSet = null;
			preparedStatement = null;
			connection = null;
			jdbc = null;
		}
		return null;

	}

	@Override
	public boolean insertVPNPostion(VPNPostion vpnPostion) {
		String address = null;
		HBaseUtil hbase = null;
		String rowKey = null;
		try {
			//查询详细地址
			address = getAddressInBS(vpnPostion.getSac(), vpnPostion.getLac());
			//判断是否查询成功
			if ("Exception".equals(address))
				return false;
			//创建HBase交互对象
			hbase = HBaseUtil.getInstance();
			//生成RowKey
			rowKey = vpnPostion.getRealNumber()
					+ DateUtil.yyyyMMddHHmmss.format(DateUtil.simpleFormat.parse(vpnPostion.getTime()));
			//写入HBase
			hbase.put("sdn", rowKey, "vpnpostion", "realNumber", vpnPostion.getRealNumber());
			hbase.put("sdn", rowKey, "vpnpostion", "bizIP", vpnPostion.getBizIP());
			hbase.put("sdn", rowKey, "vpnpostion", "sac", vpnPostion.getSac());
			hbase.put("sdn", rowKey, "vpnpostion", "lac", vpnPostion.getLac());
			hbase.put("sdn", rowKey, "vpnpostion", "address", address);
			hbase.put("sdn", rowKey, "vpnpostion", "flow", vpnPostion.getFlow());
			hbase.put("sdn", rowKey, "vpnpostion", "time", vpnPostion.getTime());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			address = null;
			hbase = null;
			rowKey = null;
		}

	}

}
