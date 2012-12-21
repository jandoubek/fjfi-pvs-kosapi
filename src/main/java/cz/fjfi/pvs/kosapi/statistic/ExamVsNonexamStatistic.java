package cz.fjfi.pvs.kosapi.statistic;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import cz.fjfi.pvs.kosapi.parser.AtomContentParser;
import cz.fjfi.pvs.kosapi.parser.AtomParser;
import cz.fjfi.pvs.kosapi.parser.AtomTagParser;

public class ExamVsNonexamStatistic extends Statistics{
	
	private final String credit = "CREDIT";
	private final String credit_exam = "CREDIT_EXAM";

	public ExamVsNonexamStatistic(String kosResponse) {
		super(kosResponse);
	}

	@Override
	protected Hashtable<String, Double> computeStatistic()
	{
		AtomParser atomParser = new AtomParser(kosResponse);
		List<AtomContentParser> atomContentList = atomParser.getAllEntries();
		Iterator<AtomContentParser> atomContentIterator = atomContentList.iterator();
		Hashtable<String, Integer> completionsCount = new Hashtable<String, Integer>();
		completionsCount.put(credit, 0);
		completionsCount.put(credit_exam, 0);
		while(atomContentIterator.hasNext())
		{
			AtomContentParser atomContent = atomContentIterator.next();
			AtomTagParser completionTag = atomContent.getTagByName("completion");
			if(completionTag == null)
				continue;
			String completion = completionTag.getTagContent();
			completionsCount.put(completion, completionsCount.get(completion) + 1);
		}
		Hashtable<String, Double> results= new Hashtable<String, Double>();
		results.put(credit, (double)completionsCount.get(credit));
		results.put(credit_exam, (double)completionsCount.get(credit_exam));
		return results;
	}

}
