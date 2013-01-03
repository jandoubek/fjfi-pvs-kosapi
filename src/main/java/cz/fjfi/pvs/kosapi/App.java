package cz.fjfi.pvs.kosapi;

import cz.fjfi.pvs.kosapi.chart.PieChart3D;
import cz.fjfi.pvs.kosapi.statistic.ExamVsCreditStatistic;
import cz.fjfi.pvs.kosapi.statistic.TeachersPerDivisionStatistic;
import cz.fjfi.pvs.kosapi.web.KosAtomReader;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;


public class App
{
    static Logger logger = Logger.getLogger(App.class);
    
    enum expectedArgs { courses, teachers, help };
    
    public static void main( String[] args )
    {   
        loadLoggerProperties();
        logger.info("Starting application");
        if(args.length > 0)
        {
        	for(String inputArg: args)
        	{
        		expectedArgs arg = expectedArgs.valueOf(inputArg);
        		switch(arg)
        		{
        		case courses:
        			try 
        	        {
        	            KosAtomReader coursesReader = new KosAtomReader("courses");
        	            String coursesResponse = coursesReader.getKosResponse();
        	            ExamVsCreditStatistic examsVsNonexams = new ExamVsCreditStatistic(coursesResponse, "completion");
        	            examsVsNonexams.printStatisticValues();

        	            Map<String, Double> examsStatisticValues = examsVsNonexams.getStatisticValues();
        	            PieChart3D examsChart = new PieChart3D(examsStatisticValues, "Courses chart");
        	            examsChart.saveAsPNG("tmp/examsVsNonexams");
        	        }
        			catch (Exception e) 
        	        {
        	            logger.error(e.getMessage(), e);
        	        }
        			break;
        		case teachers:
        			try 
        	        {
        	            KosAtomReader teachersReader = new KosAtomReader("teachers");
        	            String teachersResponse = teachersReader.getKosResponse();
        	            TeachersPerDivisionStatistic teachersPerDivision = new TeachersPerDivisionStatistic(teachersResponse, "division");
        	            teachersPerDivision.printStatisticValues();
        	            
        	            Map<String, Double> teachersStatisticValues = teachersPerDivision.getStatisticValues();
        	            PieChart3D teachersChart = new PieChart3D(teachersStatisticValues, "Teachers Per Division chart");
        	            teachersChart.saveAsPNG("tmp/teachersPerDivision");
        	        } 
        	        catch (Exception e) 
        	        {
        	            logger.error(e.getMessage(), e);
        	        }
        			break;
        		case help:
        			help("Help");
        			break;
        		default:
        			help("Wrong argument");
        			break;
        		}
        	}
        }
        else
        {
        	help("No input arguments");
        }
    }
    
    private static void help(String errorMessage)
    {
    	loadLoggerProperties();
    	logger.info(errorMessage);
    	logger.info("Usage: java App.java <args>");
    	logger.info("Possible args:");
    	logger.info("\tcourses: prints statistic of courses completed by exams or by credit");
    	logger.info("\tteachers: prints number of teacher per division");
    	logger.info("\thelp: prints this help");
    }
    
    private static void loadLoggerProperties () {
        URL log4Jresource = App.class.getResource("logger.properties");
        PropertyConfigurator.configure(log4Jresource);
    }
}
