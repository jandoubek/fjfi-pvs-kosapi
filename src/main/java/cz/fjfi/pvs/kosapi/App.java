package cz.fjfi.pvs.kosapi;

import cz.fjfi.pvs.kosapi.statistics.ExamVsNonexamStatistic;
import cz.fjfi.pvs.kosapi.web.KosAtomReader;
import java.io.IOException;
import java.util.Properties;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
        /*
        KosAtomReader kosReader = new KosAtomReader("divisions");
        String response = kosReader.getKosResponse();
        System.out.println(response);
        */
        
    	KosAtomReader coursesReader = new KosAtomReader("courses");
    	String coursesResponse = coursesReader.getKosResponse();
    	ExamVsNonexamStatistic examsVsNonexams = new ExamVsNonexamStatistic(coursesResponse);
    	examsVsNonexams.printStatisticValues();
    }
    
    public static Properties loadProperties(String properties) throws IOException{
        String fullPropertiesName = properties + ".properties";
        Properties settings = new Properties();
        settings.load(App.class.getResourceAsStream(fullPropertiesName));
        return settings;
    }
}
