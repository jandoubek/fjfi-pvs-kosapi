package cz.fjfi.pvs.kosapi.statistic;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cz.fjfi.pvs.kosapi.parser.AtomContentParser;
import cz.fjfi.pvs.kosapi.parser.AtomParser;
import cz.fjfi.pvs.kosapi.parser.AtomTagParser;

public class ExamVsNonexamStatistic extends Statistics{
	
	private final String credit = "CREDIT";
	private final String creditExam = "CREDIT_EXAM";

	public ExamVsNonexamStatistic(String kosResponse) {
		super(kosResponse);
	}

	@Override
	protected Map<String, Double> computeStatistic()
	{
		AtomParser atomParser = new AtomParser(kosResponse);
		List<AtomContentParser> atomContentList = atomParser.getAllEntries();
		Iterator<AtomContentParser> atomContentIterator = atomContentList.iterator();
		Hashtable<String, Integer> completionsCount = new Hashtable<String, Integer>();
		completionsCount.put(credit, 0);
		completionsCount.put(creditExam, 0);
		while(atomContentIterator.hasNext())
		{
			AtomContentParser atomContent = atomContentIterator.next();
			AtomTagParser completionTag = atomContent.getTagByName("completion");
			if(completionTag == null)
			{
				continue;
			}
			String completion = completionTag.getTagContent();
			completionsCount.put(completion, completionsCount.get(completion) + 1);
		}
		Map<String, Double> results= new Hashtable<String, Double>();
		results.put(credit, (double)completionsCount.get(credit));
		results.put(creditExam, (double)completionsCount.get(creditExam));
		return results;
	}

}
