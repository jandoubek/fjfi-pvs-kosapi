package cz.fjfi.pvs.kosapi;

import cz.fjfi.pvs.kosapi.chart.PieChart3D;
import cz.fjfi.pvs.kosapi.statistic.ExamVsNonexamStatistic;
import cz.fjfi.pvs.kosapi.web.KosAtomReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;


public class App
{
    static Logger logger = Logger.getLogger(App.class);
    
    public static void main( String[] args )
    {   
        loadLoggerProperties();
        logger.info("Starting application");
        try {
            KosAtomReader coursesReader = new KosAtomReader("courses");
            String coursesResponse = coursesReader.getKosResponse();
            ExamVsNonexamStatistic examsVsNonexams = new ExamVsNonexamStatistic(coursesResponse);
            examsVsNonexams.printStatisticValues();

            Map<String, Double> statisticValues = examsVsNonexams.getStatisticValues();
            PieChart3D pieChart = new PieChart3D(statisticValues, "Courses chart");
            pieChart.saveAsPNG("tmp/chart");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    public static void loadLoggerProperties () {
        URL log4Jresource = App.class.getResource("logger.properties");
        PropertyConfigurator.configure(log4Jresource);
    }
}
