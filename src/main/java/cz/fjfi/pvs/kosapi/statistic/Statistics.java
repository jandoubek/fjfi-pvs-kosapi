package cz.fjfi.pvs.kosapi.statistic;

import java.util.Hashtable;
import java.util.Map;

/**
 * 
 * @author ondra
 * abstract class for all computation classes
 */
public abstract class Statistics {
	
	protected String kosResponse;
	protected Map<String, Double> statisticValues = new Hashtable<String, Double>();
	
	public Statistics(String kosResponse)
	{
		this.kosResponse = kosResponse;
		statisticValues = computeStatistic();
	}
	
	public Map<String, Double> getStatisticValues()
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
	
	protected abstract Map<String, Double> computeStatistic();
}
