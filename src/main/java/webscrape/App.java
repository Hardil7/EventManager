package webscrape;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
      
            // Get the list of repositories
            Elements repositories = doc.select("tr");
            
          // In case of any IO errors, we want the messages written to the console

        

       	// Convenience class for writing character files
			FileWriter writer;
			writer = new FileWriter("document.txt", false);
            String event[] = new String[repositories.size()];
            String description[] = new String[repositories.size()];
			Date startDate[] = new Date[repositories.size()];
            // Writes text to a character-output stream
            int i =0 ;
			for(Element repo : repositories){
                event[i++] =  repo.select("a").attr("title").toString();
                String td = repo.select("td").text();
                System.out.println(td + "\n\n");
                /*try{
                    startDate[i] = new SimpleDateFormat("dd/MM/yyyy").parse(repo.select("td").get(2).data());  
                }
                catch(ParseException exception){
                    System.out.println("Unable to save the date " +repo.select("td").get(2).data() );
                }
                */
            }
            for(int j = 0 ; i<event.length;i++){
                System.out.println("\n\nEvent " + j);
                System.out.println("Title: " + event[i-1]);
               /* System.out.println("" + description[i-1]);
                System.out.println("Date: "+ startDate[i-1].toString());*/

            } 

            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.close();
            
		} catch (IOException e) {
			
		}
    }
}
