package zx.soft.sdn.gather.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import zx.soft.sdn.gather.dao.VPNUserDao;
import zx.soft.sdn.model.VPNUser;
import zx.soft.sdn.util.IDUtil;
import zx.soft.sdn.util.JDBCUtil;

/**
 * VPN用户信息持久层接口实现
 * 
 * @author xuran
 *
 */
public class VPNUserDaoImpl implements VPNUserDao {

	@Override
	public boolean batchUpdateInsert(VPNUser vpnUser) {
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
					"UPDATE vpn_user SET invalid=1 WHERE id IN (SELECT id FROM (SELECT id FROM vpn_user WHERE realNumber=? AND invalid=0) AS T)");
			preparedStatement.setString(1, vpnUser.getRealNumber());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO vpn_user(id,realNumber,iccid,sponsorNumber,sponsorName,sponsorIDType,sponsorIDNumber,userNumber,userName,userIDType,userIDNumber,registerDate,cancelDate,registerAgent,modifyDate,imageOne,imageTwo,imageThree,invalid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, IDUtil.generateUniqueID());
			preparedStatement.setString(2, vpnUser.getRealNumber());
			preparedStatement.setString(3, vpnUser.getIccid());
			preparedStatement.setString(4, vpnUser.getSponsorNumber());
			preparedStatement.setString(5, vpnUser.getSponsorName());
			preparedStatement.setString(6,
					(null != vpnUser.getSponsorIDType()) ? vpnUser.getSponsorIDType().toString() : null);
			preparedStatement.setString(7, vpnUser.getSponsorIDNumber());
			preparedStatement.setString(8, vpnUser.getUserNumber());
			preparedStatement.setString(9, vpnUser.getUserName());
			preparedStatement.setString(10,
					(null != vpnUser.getUserIDType()) ? vpnUser.getUserIDType().toString() : null);
			preparedStatement.setString(11, vpnUser.getUserIDNumber());
			preparedStatement.setString(12, vpnUser.getRegisterDate());
			preparedStatement.setString(13, vpnUser.getCancelDate());
			preparedStatement.setString(14, vpnUser.getRegisterAgent());
			preparedStatement.setString(15, vpnUser.getModifyDate());
			preparedStatement.setString(16, vpnUser.getImageOne());
			preparedStatement.setString(17, vpnUser.getImageTwo());
			preparedStatement.setString(18, vpnUser.getImageThree());
			preparedStatement.setInt(19, 0);
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
