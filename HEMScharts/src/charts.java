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
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        
        ChartFactory.setChartTheme(mChartTheme);
        CategoryDataset mDataset = GetDataset();
        JFreeChart mChart = ChartFactory.createLineChart(
                "电压波动折线图",//图名字
                "时间",//横坐标
                "电压",//纵坐标
                mDataset,//数据集
                PlotOrientation.VERTICAL,
                true, // 显示图例
                true, // 采用标准生成器
                false);// 是否生成超链接

        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
        mPlot.setOutlinePaint(Color.RED);//边界线
       

        ChartFrame mChartFrame = new ChartFrame("电压波动折线图", mChart);
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
			//System.out.println("消息类型:"+ne.getType()+"\t设备ID:"+ne.getId()+"\t设备序列号:"+ne.getSn()+"\t电压:"+ne.getPower()+"\t设备状态:"+ne.getState()+"\t时间:"+ne.getTime());
			mDataset.addValue(Double.valueOf(ne.getPower()), "设备"+ne.getId(), ne.getTime().split(" ")[1]);
		}
        
        return mDataset;
    }
}
