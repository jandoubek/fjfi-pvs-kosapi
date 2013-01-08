package cz.fjfi.pvs.kosapi;

import cz.fjfi.pvs.kosapi.statistic.StatisticsWrapper;
import java.net.URL;

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
        boolean helpNeeded = false;
        if(args.length > 0)
        {
        	for(String inputArg: args)
        	{
        		StatisticsWrapper statistic = new StatisticsWrapper(inputArg);
        		helpNeeded = statistic.getStatisticState();
        	}
        }
        else
        {
        	helpNeeded = true;
        	wrongArgumentWarning("No input arguments");
        }
        if(helpNeeded)
        {
        	help();
        }
    }
    
    private static void wrongArgumentWarning(String warning)
    {
    	loadLoggerProperties();
    	logger.info(warning);
    }
    
    private static void help()
    {
    	loadLoggerProperties();
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
