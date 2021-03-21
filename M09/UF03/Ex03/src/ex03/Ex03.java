
package ex03;

import java.net.*;
import java.io.*;
import java.util.*;


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
                System.out.println(cadena);
            }
			
        } catch (MalformedURLException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
