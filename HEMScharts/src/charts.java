import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.DAO;
import entity.Entity;

public class charts {
    @SuppressWarnings("unused")
	public static void main(String[] args) {
        
    	StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
        
        ChartFactory.setChartTheme(mChartTheme);
        CategoryDataset mDataset = GetDataset();
        JFreeChart mChart = ChartFactory.createLineChart(
                "��ѹ��������ͼ",//ͼ����
                "ʱ��",//������
                "��ѹ",//������
                mDataset,//���ݼ�
                PlotOrientation.VERTICAL,
                true, // ��ʾͼ��
                true, // ���ñ�׼������
                false);// �Ƿ����ɳ�����

        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);//�����ײ�������
        mPlot.setOutlinePaint(Color.RED);//�߽���
       

        ChartFrame mChartFrame = new ChartFrame("��ѹ��������ͼ", mChart);
        mChartFrame.pack();
        mChartFrame.setVisible(true);
    	
    	
        while(true){
        	
        	mPlot.setDataset(GetDataset());
        	
        	
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

    }
    
    
    
    public static CategoryDataset GetDataset()
    {
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        
        ArrayList<Entity> ar=new DAO().getTen();
        int index = ar.size()-1;
		for(Entity ne; index>=0 ;index--) {
			ne = ar.get(index);
			//System.out.println("��Ϣ����:"+ne.getType()+"\t�豸ID:"+ne.getId()+"\t�豸���к�:"+ne.getSn()+"\t��ѹ:"+ne.getPower()+"\t�豸״̬:"+ne.getState()+"\tʱ��:"+ne.getTime());
			mDataset.addValue(Double.valueOf(ne.getPower()), "�豸"+ne.getId(), ne.getTime().split(" ")[1]);
		}
        
        return mDataset;
    }
}
