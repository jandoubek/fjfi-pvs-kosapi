package cz.fjfi.pvs.kosapi.statistic;

import java.util.Map;

import org.apache.log4j.Logger;

import cz.fjfi.pvs.kosapi.chart.PieChart3D;
import cz.fjfi.pvs.kosapi.web.KosAtomReader;

public class StatisticsWrapper
{
	protected final String coursesResource = "courses";
	protected final String coursesTag = "completion";
	protected final String teachersResource = "teachers";
	protected final String teachersTag = "division";

	protected String statisticName;
	protected knownStatistics statistic;
	protected boolean helpNeeded = true;
	
	protected Statistics statisticClass = null;
	
	protected Logger logger = Logger.getLogger(StatisticsWrapper.class);
	
	protected static enum knownStatistics { COURSES, TEACHERS, HELP };
	
	public StatisticsWrapper(String statisticName)
	{
		this.statisticName = statisticName;
		try
		{
			String kosResponse = getKosResponse(statisticName);
			statisticClass = instantiateStatistic(kosResponse);
			computeStatistic(statisticClass);
		}
		catch(Exception e)
		{
			wrongArgumentWarning("Unknown argument: " + statisticName);
		}
	}
	
	public boolean getStatisticState()
	{
		return helpNeeded;
	}
	
	protected String getKosResponse(String statisticName) throws IllegalArgumentException
	{
		statistic = knownStatistics.valueOf(statisticName.toUpperCase());
		String atomResource = null;
		switch(statistic)
		{
			case COURSES:
				atomResource = coursesResource;
				helpNeeded = false;
				break;
			case TEACHERS:
				atomResource = teachersResource;
				helpNeeded = false;
				break;
			case HELP:
				// should suppress exception logging 
				// catching 404 status code would be an awful solution
				break;
			default:
				break;
		}
		String kosResponse = null;
		try
		{
			KosAtomReader kosReader = new KosAtomReader(atomResource);
			kosResponse = kosReader.getKosResponse();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return kosResponse;
	}
	
	protected void computeStatistic(Statistics statisticClass) throws NullPointerException
	{
		Map<String, Double> examsStatisticValues = statisticClass.getStatisticValues();
        PieChart3D statisticChart = new PieChart3D(examsStatisticValues, statisticName + " chart");
        statisticChart.saveAsPNG("tmp/" + statisticName);
		statisticClass.printStatisticValues();
	}
	
	protected Statistics instantiateStatistic(String kosResponse)
	{
		Statistics statisticClass = null;
		switch(statistic)
		{
		case COURSES:
			statisticClass = (Statistics) new ExamVsCreditStatistic(kosResponse, coursesTag);
			break;
		case TEACHERS:
			statisticClass = (Statistics) new TeachersPerDivisionStatistic(kosResponse, teachersTag);
			break;
		default:
			break;
		}
		return statisticClass;
	}
	
	protected void wrongArgumentWarning(String warning)
	{
		logger.info(warning);
	}
}
