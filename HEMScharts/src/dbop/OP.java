package dbop;

import java.util.ArrayList;

import dao.DAO;
import entity.Entity;

public class OP {
	public static void getAllInfo() {	
		
		ArrayList<Entity> ar=new DAO().getAll();
		for(Entity ne:ar) { 	      			
			System.out.println("��Ϣ����:"+ne.getType()+"\t�豸ID:"+ne.getId()+"\t�豸���к�:"+ne.getSn()+"\t��ѹ:"+ne.getPower()+"\t�豸״̬:"+ne.getState()+"\tʱ��:"+ne.getTime());
		}

	}

}

