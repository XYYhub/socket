package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutil.DBUtil;
import entity.Entity;

public class DAO {
	
	//查找所有数据
	public ArrayList<Entity> getAll(){
		ArrayList<Entity> ar = new ArrayList<Entity>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select type,id,sn,power,state,time from tcp";
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("SQL语句预编译失败");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL语句存放失败");
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				Entity ent = new Entity();
				ent.setType(rs.getString("type"));
				ent.setId(rs.getString("id"));
				ent.setSn(rs.getString("sn"));
				ent.setPower(rs.getString("power"));
				ent.setState(rs.getString("state"));
				ent.setTime(rs.getString("time"));
				ar.add(ent);
			}
		} catch (SQLException e) {
			System.out.println("存放数据至ar失败");
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs释放失败");
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ps释放失败");
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn释放失败");
					e.printStackTrace();
				}
			}
		}
		
		return ar;
	}
	
	
	//查找最新十个数据
		public ArrayList<Entity> getTen(){
			ArrayList<Entity> ar = new ArrayList<Entity>();
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "select * from tcp Order By time Desc limit 10";
			try {
				ps = conn.prepareStatement(sql);
			} catch (SQLException e) {
				System.out.println("SQL语句预编译失败");
				e.printStackTrace();
			}
			try {
				rs = ps.executeQuery();
			} catch (SQLException e) {
				System.out.println("SQL语句存放失败");
				e.printStackTrace();
			}
			
			try {
				while(rs.next()) {
					Entity ent = new Entity();
					ent.setType(rs.getString("type"));
					ent.setId(rs.getString("id"));
					ent.setSn(rs.getString("sn"));
					ent.setPower(rs.getString("power"));
					ent.setState(rs.getString("state"));
					ent.setTime(rs.getString("time"));
					ar.add(ent);
				}
			} catch (SQLException e) {
				System.out.println("存放数据至ar失败");
				e.printStackTrace();
			}
			
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						System.out.println("rs释放失败");
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println("ps释放失败");
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						System.out.println("conn释放失败");
						e.printStackTrace();
					}
				}
			}
			
			return ar;
		}
	
		//查找最新一个数据
	@SuppressWarnings("null")
	public static Entity getLatest(String id){
		Entity ent = new Entity();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select state from tcp where id = ? Order By time Desc limit 1";
		try {
			ps.setString(1, id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("SQL语句预编译失败");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL语句存放失败");
			e.printStackTrace();
		}
		
		try {
			ent.setType(rs.getString("type"));
			ent.setId(rs.getString("id"));
			ent.setSn(rs.getString("sn"));
			ent.setPower(rs.getString("power"));
			ent.setState(rs.getString("state"));
			ent.setTime(rs.getString("time"));
			
		} catch (SQLException e) {
			System.out.println("提取失败");
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs释放失败");
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ps释放失败");
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn释放失败");
					e.printStackTrace();
				}
			}
		}
		
		return ent;
	}
	
	// 修改
	public void update(Entity ent) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "UPDATE tcp SET state= ? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ent.getState());
			int a = ps.executeUpdate();
			if (a > 0) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


}
