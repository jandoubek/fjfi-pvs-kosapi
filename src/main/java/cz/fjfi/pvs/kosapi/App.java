package cz.fjfi.pvs.kosapi;

import cz.fjfi.pvs.kosapi.chart.PieChart3D;
import cz.fjfi.pvs.kosapi.statistic.ExamVsNonexamStatistic;
import cz.fjfi.pvs.kosapi.web.KosAtomReader;
import java.io.IOException;
import java.util.Map;


public class App
{
    public static void main( String[] args ) throws IOException
    {        
    	KosAtomReader coursesReader = new KosAtomReader("courses");
    	String coursesResponse = coursesReader.getKosResponse();
    	ExamVsNonexamStatistic examsVsNonexams = new ExamVsNonexamStatistic(coursesResponse);
    	examsVsNonexams.printStatisticValues();
    	
    	Map<String, Double> statisticValues = examsVsNonexams.getStatisticValues();
    	PieChart3D pieChart = new PieChart3D(statisticValues, "Courses chart");
    	pieChart.saveAsPNG("tmp/chart");
    }
}
