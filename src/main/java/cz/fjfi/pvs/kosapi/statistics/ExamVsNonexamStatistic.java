package cz.fjfi.pvs.kosapi.statistics;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import cz.fjfi.pvs.kosapi.parser.AtomContentParser;
import cz.fjfi.pvs.kosapi.parser.AtomParser;
import cz.fjfi.pvs.kosapi.parser.AtomTagParser;

public class ExamVsNonexamStatistic extends Statistics{

	public ExamVsNonexamStatistic(String kosResponse) {
		super(kosResponse);
	}

	@Override
	protected List<Double> computeStatistic()
	{
		AtomParser atomParser = new AtomParser(kosResponse);
		List<AtomContentParser> atomContentList = atomParser.getAllEntries();
		Iterator<AtomContentParser> atomContentIterator = atomContentList.iterator();
		Hashtable<String, Integer> completionsCount = new Hashtable<String, Integer>();
		completionsCount.put("CREDIT", 0);
		completionsCount.put("CREDIT_EXAM", 0);
		while(atomContentIterator.hasNext())
		{
			AtomContentParser atomContent = atomContentIterator.next();
			AtomTagParser completionTag = atomContent.getTagByName("completion");
			if(completionTag == null)
				continue;
			String completion = completionTag.getTagContent();
			completionsCount.put(completion, completionsCount.get(completion) + 1);
		}
		List<Double> results = new ArrayList<Double>();
		results.add((double)completionsCount.get("CREDIT"));
		results.add((double)completionsCount.get("CREDIT_EXAM"));
		return results;
	}

}
