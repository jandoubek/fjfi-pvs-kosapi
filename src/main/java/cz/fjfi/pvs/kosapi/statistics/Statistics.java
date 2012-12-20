package cz.fjfi.pvs.kosapi.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author ondra
 * abstract class for all computation classes
 */
public abstract class Statistics {
	
	protected String kosResponse;
	protected List<Double> statisticValues = new ArrayList<Double>();
	
	public Statistics(String kosResponse)
	{
		this.kosResponse = kosResponse;
		statisticValues = computeStatistic();
	}
	
	public List<Double> getStatisticValues()
	{
		return statisticValues;
	}
	
	public void printStatisticValues()
	{
		Iterator<Double> valuesIterator = statisticValues.iterator();
		while(valuesIterator.hasNext())
		{
			Double value = valuesIterator.next();
			System.out.println(value);
		}
	}
	
	protected abstract List<Double> computeStatistic();
}
