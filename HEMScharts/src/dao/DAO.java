package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutil.DBUtil;
import entity.Entity;

public class DAO {
	
	//������������
	public ArrayList<Entity> getAll(){
		ArrayList<Entity> ar = new ArrayList<Entity>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select type,id,sn,power,state,time from tcp";
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("SQL���Ԥ����ʧ��");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL�����ʧ��");
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
			System.out.println("���������arʧ��");
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ps�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
		}
		
		return ar;
	}
	
	
	//��������ʮ������
		public ArrayList<Entity> getTen(){
			ArrayList<Entity> ar = new ArrayList<Entity>();
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "select * from tcp Order By time Desc limit 10";
			try {
				ps = conn.prepareStatement(sql);
			} catch (SQLException e) {
				System.out.println("SQL���Ԥ����ʧ��");
				e.printStackTrace();
			}
			try {
				rs = ps.executeQuery();
			} catch (SQLException e) {
				System.out.println("SQL�����ʧ��");
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
				System.out.println("���������arʧ��");
				e.printStackTrace();
			}
			
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						System.out.println("rs�ͷ�ʧ��");
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println("ps�ͷ�ʧ��");
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						System.out.println("conn�ͷ�ʧ��");
						e.printStackTrace();
					}
				}
			}
			
			return ar;
		}
	
		//��������һ������
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
			System.out.println("SQL���Ԥ����ʧ��");
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL�����ʧ��");
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
			System.out.println("��ȡʧ��");
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("rs�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("ps�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("conn�ͷ�ʧ��");
					e.printStackTrace();
				}
			}
		}
		
		return ent;
	}
	
	// �޸�
	public void update(Entity ent) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "UPDATE tcp SET state= ? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ent.getState());
			int a = ps.executeUpdate();
			if (a > 0) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�
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
