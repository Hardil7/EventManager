package webscrape;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://www.computerworld.com/article/3313417/tech-event-calendar-shows-conferences-and-it-expos-updated.html").get();
      
            // With the document fetched, we use JSoup's title() method to fetch the title
            System.out.printf("Title: %s\n", doc.title());
      
            // Get the list of Events
            Elements events = doc.select("tr");
            
            ArrayList<String> event = new ArrayList<>();
            ArrayList<String> description = new ArrayList<>();
			List<Date> startDate = new ArrayList<Date>();
            List<Date> endDate = new ArrayList<Date>();
        
			for(Element eve : events){
                if(eve.select("a").attr("title") != ""){   
                    event.add(eve.select("a").attr("title").toString());
                    description.add(eve.select("td").get(0).text());
                    try{
                        startDate.add(new SimpleDateFormat("yyyy-MM-dd").parse(eve.select("td").get(1).text()));  
                    }
                    catch(ParseException exception){
                        System.out.println("Unable to save the start date " +eve.select("td").get(1).text() );
                    }
                    try{
                        endDate.add(new SimpleDateFormat("yyyy-MM-dd").parse(eve.select("td").get(2).text()));  
                    }
                    catch(ParseException exception){
                        System.out.println("Unable to save th end date " +eve.select("td").get(2).text() );
                    }
                }
            }     
		} catch (Exception e) {
			System.out.printf("Error Stack:" + e.getMessage());
		}
    }
}
