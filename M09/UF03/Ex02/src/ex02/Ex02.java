package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Ex02 {
	
    public static void main (String[] args) {
	
        Scanner teclat = new Scanner(System.in);
        
        String urlS = "www.insbaixcamp.org";
        int port = 443;
        
        URLConnection urlCon = null;
        
        try {
            URL url = new URL("https", urlS, port, "");
            
            urlCon = url.openConnection();

            BufferedReader in;
            InputStream inputStream = urlCon.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream));    
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);	
            }
            
            in.close();
            
        } catch (MalformedURLException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
