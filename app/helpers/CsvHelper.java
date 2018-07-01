package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import play.Play;

public class CsvHelper {

	public static void exportAsCsv(String filePath, Map<String, Map> holdingsJson) throws FileNotFoundException {		
         
        StringBuilder oneMonthAmount = new StringBuilder();
        StringBuilder fifteenMinutesAmount = new StringBuilder();
        oneMonthAmount.append("Pairs");
        oneMonthAmount.append(',');
        oneMonthAmount.append("Days");
        oneMonthAmount.append(',');
        oneMonthAmount.append("Amount");          
        oneMonthAmount.append('\n');
        
        Map<String, String> cryptoCurrenciesReal = holdingsJson.get("cryptoCurrenciesReal");
        Map<String, Double> cryptoCurrenciesUsdAmount = holdingsJson.get("usdAmount");
 
        for (Map.Entry<String, String> crypto : cryptoCurrenciesReal.entrySet())
        {                        
        	String[] pairDaysArray=crypto.getKey().split("_");
        	
        	oneMonthAmount.append(pairDaysArray[0]);		
        	oneMonthAmount.append(',');	
        	oneMonthAmount.append(pairDaysArray[1]);		
        	oneMonthAmount.append(',');	         	
        	oneMonthAmount.append(crypto.getValue());	        
        	oneMonthAmount.append('\n');	        	          
        }
        //-------------------15 minutes---------------------------------
        fifteenMinutesAmount.append("Pairs");
        fifteenMinutesAmount.append(',');        
        fifteenMinutesAmount.append("minutes");
        fifteenMinutesAmount.append(',');
        fifteenMinutesAmount.append("usdAmount");          
        fifteenMinutesAmount.append('\n');
        
        for (Map.Entry<String, Double> usdAmountMinute : cryptoCurrenciesUsdAmount.entrySet())
        {                 
        	String[] pairMinutesArray=usdAmountMinute.getKey().split("_");
        	fifteenMinutesAmount.append(pairMinutesArray[0]);		
        	fifteenMinutesAmount.append(',');        	
        	fifteenMinutesAmount.append(pairMinutesArray[1]);		
        	fifteenMinutesAmount.append(',');
        	fifteenMinutesAmount.append(usdAmountMinute.getValue());	        
        	fifteenMinutesAmount.append('\n');	        	        	        	        
        }
        
        saveAsFile(filePath+"OneMonth.csv", oneMonthAmount.toString());
        saveAsFile(filePath+"FifteenMinutes.csv", fifteenMinutesAmount.toString());
    }	
		
	public static void saveAsFile(String filePath, String content) throws FileNotFoundException {		
        PrintWriter pw = new PrintWriter(new File(filePath));
        
        pw.write(content);
        pw.close();
    }		
}
