package template.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BarGraph extends JPanel
{
	private static final long serialVersionUID = -1222464096132903386L;
	
	private static final int INIT_SIZE = 400;
	
	private List<Integer> rawData;
	
	private List<Float> normalizedData;
	
	private float high;
	
	private float barScaleX = 0f;
	private float barScaleY = 0f;
	
	public BarGraph(String title, List<Integer> data)
	{
		this.setData(data);
		
		JFrame f = new JFrame(title);
		
		this.setMinimumSize(new Dimension(INIT_SIZE, INIT_SIZE));
		this.setPreferredSize(this.getMinimumSize());
		
		this.setBackground(Color.WHITE);
		
		f.add(this);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		barScaleX = (float)(this.getWidth())/normalizedData.size();
		barScaleY = (float)(this.getHeight())/high;
		
		for(int i = 0; i < normalizedData.size(); i++)
		{
			float current = normalizedData.get(i);
			
			g.setColor(Color.MAGENTA);
			g.fillRect((int)Math.ceil(i*barScaleX), (int)(this.getHeight()-(current*barScaleY)), (int)Math.ceil(barScaleX), (int)(current*barScaleY));
			
			g.setColor(Color.BLACK);
			g.drawRect((int)Math.ceil(i*barScaleX), (int)(this.getHeight()-(current*barScaleY)), (int)Math.ceil(barScaleX), (int)(current*barScaleY));
		}
	}
	
	public void setData(List<Integer> data)
	{
		if(!data.equals(rawData))
		{
			this.normalizedData = normalize(data);
			
			high = this.getNormalizedHigh(this.normalizedData);
		}
	}
	
	private List<Float> normalize(List<Integer> data)
	{
		List<Float> normalized = new ArrayList<Float>(data.size());
		
		int low = getLow(data);
		float mean = getMean(data);
		//float range = getRange(data);
		
		for(int i = 0; i < data.size(); i++)
		{
			normalized.add(i, Math.abs((data.get(i)-low)/mean));
		}
		
		return normalized;
	}
	
	private float getMean(List<Integer> data)
	{
		float mean = 0;
		
		for(int i : data)
			mean += i;
		
		mean /= data.size();
		
		return mean;
	}
	
	private float getRange(List<Integer> data)
	{
		float low = data.get(0);
		float high = data.get(0);
		
		for(int i : data)
		{
			if(i < low)
				low = i;
			if(i > high)
				high = i;
		}
		
		return high-low;
	}
	
	private int getLow(List<Integer> data)
	{
		int low = data.get(0);
		
		for(int i : data)
		{
			if(i < low)
				low = i;
		}
		
		return low;
	}
	
	private float getNormalizedHigh(List<Float> data)
	{
		float high = data.get(0);
		
		for(float i : data)
		{
			if(i > high)
				high = i;
		}
		
		return high;
	}

}
