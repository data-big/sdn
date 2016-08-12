package zx.soft.sdn.etl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import zx.soft.sdn.etl.dao.VPNCardDao;
import zx.soft.sdn.model.VPNCard;
import zx.soft.sdn.util.IDUtil;
import zx.soft.sdn.util.JDBCUtil;

/**
 * VPN卡信息持久层接口实现
 * 
 * @author xuran
 *
 */
public class VPNCardDaoImpl implements VPNCardDao {

	@Override
	public boolean batchUpdateInsert(VPNCard vpnCard) {
		JDBCUtil jdbc = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			jdbc = JDBCUtil.getInstance();
			connection = jdbc.getSDNConnection();
			//设置手动控制事务
			connection.setAutoCommit(false);
			/*MYSQL采用InnoDB引擎模式时，如果操作表中设置了索引，多线程批量更新该表，并且更新条件为索引条件时，会引发索引行级锁和表级锁的死锁问题。
			解决该问题除了替换索引条件为主键条件外，还需要降低事务的隔离级别为TRANSACTION_READ_COMMITTED*/
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			preparedStatement = connection.prepareStatement(
					"UPDATE vpn_card SET invalid=1 WHERE id IN (SELECT id FROM (SELECT id FROM vpn_card WHERE realNumber=? AND invalid=0) AS T)");
			preparedStatement.setString(1, vpnCard.getRealNumber());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO vpn_card(id,realNumber,bizIP,stopIP,specialIP,offsetBizIP,offsetStopIP,offsetSpecialIP,insertDate,invalid) VALUES(?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, IDUtil.generateUniqueID());
			preparedStatement.setString(2, vpnCard.getRealNumber());
			preparedStatement.setString(3, vpnCard.getBizIP());
			preparedStatement.setString(4, vpnCard.getStopIP());
			preparedStatement.setString(5, vpnCard.getSpecialIP());
			preparedStatement.setString(6, vpnCard.getOffsetBizIP());
			preparedStatement.setString(7, vpnCard.getOffsetStopIP());
			preparedStatement.setString(8, vpnCard.getOffsetSpecialIP());
			preparedStatement.setString(9, vpnCard.getInsertDate());
			preparedStatement.setInt(10, 0);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			//提交事务
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//数据回滚
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return false;
		} finally {
			jdbc.close(null, preparedStatement, connection);
			preparedStatement = null;
			connection = null;
			jdbc = null;
		}

	}

}
