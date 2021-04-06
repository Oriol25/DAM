/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex08;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Ex08 {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        Ex08 http = new Ex08();
                
        Scanner teclat = new Scanner(System.in);
                
        int opcio = 0;
                
        do {
            System.out.println("Testing 1 - Send Http GET request");
            System.out.println("Testing 2 - Send Http POST request");
            System.out.print("Opcio: ");
            opcio = teclat.nextInt();
            if (opcio == 1) {
                http.sendGet();
                System.out.println("");
            } else if (opcio == 2) {
                http.sendPost();
                System.out.println("");
            }
                   
        } while (opcio == 1 || opcio == 2);
		
        

    }

    // HTTP GET request
    private void sendGet() throws Exception {

        //FLUX PER A ENTRADA ESTÀNDARD
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String url = "";
        
        System.out.println("\nURL? (https://www.google.com/)");
        url = br.readLine();     
        
	try {
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            //print result
            System.out.println(response.toString());
        
        } catch (Exception e) {
            System.out.println("Error con el URL " + url);
        }

    }
	
    // HTTP POST request
    private void sendPost() throws Exception {

	String url;
        
        //FLUX PER A ENTRADA ESTÀNDARD
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("\nURL (http://www.insbaixcamp.cat/moodle/my)");
        url = br.readLine();
        
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	//add reuqest header
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "ca-es");
		
	//Query string
	String urlParameters = "categoryid=7";
		
	// Send post request
	con.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	wr.writeBytes(urlParameters);
	wr.flush();
	wr.close();

        int responseCode = con.getResponseCode();
	System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + urlParameters);
	System.out.println("Response Code : " + responseCode);

	BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
	}
	
        in.close();
		
	//print result
        System.out.println(response.toString());

    }
}
