package cz.fjfi.pvs.kosapi.chart;

import java.util.Hashtable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart3D extends Chart {
	
	private boolean darkerSize = true;
	
	private DefaultPieDataset dataset = new DefaultPieDataset();
	
	public PieChart3D(Hashtable<String, Double> data, String title)
	{
		createDataset(data);
		chart = ChartFactory.createPieChart3D(title, dataset, true, true, true);
		setChartProperties();
	}
	
	private void createDataset(Hashtable<String, Double> data)
	{
		for(String key: data.keySet())
		{
			Double value = data.get(key);
			dataset.setValue(key, value);
		}
	}

	@Override
	protected void setChartProperties() 
	{
		PiePlot3D p = (PiePlot3D) chart.getPlot();
		p.setForegroundAlpha(foregroundAlpha);
		p.setBackgroundAlpha(backgroundAlpha);
		p.setDarkerSides(darkerSize);

		chart.setBackgroundPaint(backgroundPaint);
		chart.setAntiAlias(antiAlias);
		chart.setTextAntiAlias(textAntiAlias);
		chart.setBorderVisible(borderVisible);
	}

}
