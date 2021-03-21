/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author poved
 */
public class Ex03 {

    @SuppressWarnings("rawtypes")
    public static void main (String[] args) {
        
        String urlS = args[0];
        int cap = Integer.parseInt(args[1]);
        String patro = args[2];
		        
	try {
            String cadena;
            URL url = new URL(urlS);
            URLConnection connexio = url.openConnection();
			
            System.out.println("===============================================================");
            System.out.println("ADREÇA, DARA I CONTINGUT");
            System.out.println("Adreça [getURL]: " + connexio.getURL());
			
            Date data = new Date(connexio.getLastModified());
            System.out.println("Data última modificació [getLastModified()]: " + data);
            System.out.println("Tipus de Contingut [getContentType()]" + connexio.getContentType());
			
            System.out.println("===============================================================");
            System.out.println("TOTS ELS CAMPS DE CAPÇALERA AMB getHeaderFields(): ");
		
            //Fem servir una estructura Map per a recuperar capçaleres
            Map campsCapcalera = connexio.getHeaderFields();
            Iterator it = campsCapcalera.entrySet().iterator();
			
            while (it.hasNext()) {
		Map.Entry map = (Map.Entry) it.next();
		System.out.println(map.getKey() + ":" + map.getValue());
            }
			
            System.out.println("===============================================================");
            System.out.println(cap + " camps de Capçalera");
            
            for (int i = 0; i < cap; i++) {
                System.out.println("getHeaderField("+ i +")=> " + connexio.getHeaderField(i));
            }
            
            System.out.println("===============================================================");
			
            System.out.println("Contingut de [url.getFile()]: " + url.getFile());
            BufferedReader pagina = new BufferedReader(new InputStreamReader(url.openStream()));
			
            while ((cadena = pagina.readLine()) != null) {		
                if(cadena.contains(patro.subSequence(0, patro.length()-1))) {
                   System.out.println(cadena);
                }
            }
			
        } catch (MalformedURLException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
