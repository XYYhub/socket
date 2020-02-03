package dbop;

import java.util.ArrayList;

import dao.DAO;
import entity.Entity;

public class OP {
	public static void getAllInfo() {	
		
		ArrayList<Entity> ar=new DAO().getAll();
		for(Entity ne:ar) { 	      			
			System.out.println("消息类型:"+ne.getType()+"\t设备ID:"+ne.getId()+"\t设备序列号:"+ne.getSn()+"\t电压:"+ne.getPower()+"\t设备状态:"+ne.getState()+"\t时间:"+ne.getTime());
		}

	}

}

