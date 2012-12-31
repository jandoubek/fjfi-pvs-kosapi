package cz.fjfi.pvs.kosapi.statistic;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cz.fjfi.pvs.kosapi.parser.AtomTagParser;

public class ExamVsNonexamStatistic extends Statistics{
	
	private final String credit = "CREDIT";
	private final String creditExam = "CREDIT_EXAM";

	public ExamVsNonexamStatistic(String kosResponse, String requiredTag)
	{
		super(kosResponse, requiredTag);
	}

	@Override
	protected Map<String, Double> computeStatistic(String requiredTag)
	{		
		List<AtomTagParser> completionsList = getKosTagsByName(requiredTag);
		Map<String, Integer> completionsCount = new Hashtable<String, Integer>();
		completionsCount.put(credit, 0);
		completionsCount.put(creditExam, 0);
		Iterator<AtomTagParser> completionsIterator = completionsList.iterator();
		while(completionsIterator.hasNext())
		{
			AtomTagParser completionTag = completionsIterator.next();
			String completion = completionTag.getTagContent();
			completionsCount.put(completion, completionsCount.get(completion) + 1);
		}
		Map<String, Double> results = new Hashtable<String, Double>();
		results.put(credit, (double)completionsCount.get(credit));
		results.put(creditExam, (double)completionsCount.get(creditExam));
		return results;
	}

}
