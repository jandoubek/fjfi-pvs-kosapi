package cz.fjfi.pvs.kosapi.statistic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import cz.fjfi.pvs.kosapi.parser.AtomContentParser;
import cz.fjfi.pvs.kosapi.parser.AtomParser;
import cz.fjfi.pvs.kosapi.parser.AtomTagParser;

/**
 * 
 * @author ondra
 * abstract class for all computation classes
 */
public abstract class Statistics {
	
	protected String kosResponse;
	protected Map<String, Double> statisticValues = new Hashtable<String, Double>();
        
    protected Logger logger = Logger.getLogger(Statistics.class);
	
	public Statistics(String kosResponse, String requiredTag)
	{
		this.kosResponse = kosResponse;
		statisticValues = computeStatistic(requiredTag);
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
			logger.info(key + ": " + value);
		}
	}
	
	protected abstract Map<String, Double> computeStatistic(String requiredTag);
	
	protected List<AtomTagParser> getKosTagsByName(String tagName)
	{
		AtomParser atomParser = new AtomParser(kosResponse);
		List<AtomContentParser> atomContentList = atomParser.getAllEntries();
		Iterator<AtomContentParser> atomContentIterator = atomContentList.iterator();
		List<AtomTagParser> tagList = new ArrayList<AtomTagParser>();
		while(atomContentIterator.hasNext())
		{
			AtomContentParser atomContent = atomContentIterator.next();
			AtomTagParser completionTag = atomContent.getTagByName(tagName);
			if(completionTag != null)
			{
				tagList.add(completionTag);
			}
		}
		return tagList;
	}
}
