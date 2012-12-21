package cz.fjfi.pvs.kosapi.chart;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.Size2D;

public abstract class Chart {
	protected JFreeChart chart;
	
	protected float foregroundAlpha = 0.7f;
	protected float backgroundAlpha = 0.2f;
	protected Color backgroundPaint = Color.white;
	protected boolean antiAlias = true;
	protected boolean textAntiAlias = true;
	protected boolean borderVisible = false;
	
	protected abstract void setChartProperties();
	
	public void saveAsPNG(String outputFile)
	{
		saveAsPNG(outputFile, new Size2D(800, 600));
	}
	
	public void saveAsPNG(String outputFile, Size2D size)
	{
		outputFile += ".png";
		try
		{
			ChartUtilities.saveChartAsPNG(new File(outputFile), chart, (int)size.width, (int)size.height);
			System.out.println("Chart saved as " + outputFile);
		}
		catch(IOException saveException)
		{
			System.err.println("Nelze vytvořit výstupní soubor.");
		}
	}
}
