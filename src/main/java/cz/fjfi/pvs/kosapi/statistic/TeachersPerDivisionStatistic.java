package cz.fjfi.pvs.kosapi.statistic;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cz.fjfi.pvs.kosapi.parser.AtomTagParser;

public class TeachersPerDivisionStatistic extends Statistics {

	public TeachersPerDivisionStatistic(String kosResponse, String requiredTag)
	{
		super(kosResponse, requiredTag);
	}

	@Override
	protected Map<String, Double> computeStatistic(String requiredTag) {
		List<AtomTagParser> divisionsList = getKosTagsByName(requiredTag);
		Map<String, Double> teachersCount = new Hashtable<String, Double>();
		Iterator<AtomTagParser> divisionsIterator = divisionsList.iterator();
		while(divisionsIterator.hasNext())
		{
			AtomTagParser divisionTag = divisionsIterator.next();
			String division = divisionTag.getTagContent();
			if(teachersCount.containsKey(division))
			{
				teachersCount.put(division, teachersCount.get(division) + 1);
			}
			else
			{
				teachersCount.put(division, 1.0);
			}
		}
		return teachersCount;
	}

}
