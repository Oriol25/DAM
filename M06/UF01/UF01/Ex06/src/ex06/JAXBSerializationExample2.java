package ex06;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.bind.JAXB;
 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
 
public class JAXBSerializationExample2 {
	
	private static final String COCHES_XML_FILE = "coches.xml";
 
	public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext context = JAXBContext.newInstance(Cotxes.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Cotxes cotxes = ompleCotxes();
		
		//Mostrem el document XML generat por la sortida estandard
		marshaller.marshal(cotxes, System.out);
		
		FileOutputStream fos = new FileOutputStream(COCHES_XML_FILE);
		//guardem l'objecte serializat en un document XML
		marshaller.marshal(cotxes, fos);
		fos.close();
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//Deserialitzem a partir de un document XML
		Cotxes camisasAux = (Cotxes) unmarshaller.unmarshal(new File(COCHES_XML_FILE));
		System.out.println("********* Cotxes carregat desde fitxer XML***************");
		//Mostrem l'objeto Java obtingut
		marshaller.marshal(camisasAux, System.out);
                
                Cotxes cotxes2 = JAXB.unmarshal(new FileReader("coches.xml"), Cotxes.class);
                JAXB.marshal(cotxes, System.out);
		
	}
	
	private static Cotxes ompleCotxes(){
		
		String[] Marca = {"Ctiroen", "Ford", "BMW"};
                String[] Model = {"Matiz", "Mustang", "M4"};
                String[] Matriucla = {"9865AZF", "9658GHJ", "6587GVB"};
		Cotxe[] ArrayCotxes = new Cotxe[3];
		
		for(int i=0; i<3; i++){
			ArrayCotxes[i] = new Cotxe();
                        ArrayCotxes[i].setMarca(Marca[i]);
                        ArrayCotxes[i].setModel(Model[i]);
                        ArrayCotxes[i].setMatricula(Matriucla[i]);
			ArrayCotxes[i].setId(i);			
		}
		
		Cotxes cotxes = new Cotxes();
		cotxes.setCotxes(ArrayCotxes);
		
		return cotxes;
	}
}