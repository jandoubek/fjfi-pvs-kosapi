package cz.fjfi.pvs.kosapi.statistic;

import java.util.Hashtable;

/**
 * 
 * @author ondra
 * abstract class for all computation classes
 */
public abstract class Statistics {
	
	protected String kosResponse;
	protected Hashtable<String, Double> statisticValues = new Hashtable<String, Double>();
	
	public Statistics(String kosResponse)
	{
		this.kosResponse = kosResponse;
		statisticValues = computeStatistic();
	}
	
	public Hashtable<String, Double> getStatisticValues()
	{
		return statisticValues;
	}
	
	public void printStatisticValues()
	{
		for(String key: statisticValues.keySet())
		{
			Double value = statisticValues.get(key);
			System.out.println(key + ": " + value);
		}
	}
	
	protected abstract Hashtable<String, Double> computeStatistic();
}
