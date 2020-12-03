
package ex05;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Ex05 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

	Scanner teclat = new Scanner(System.in);
        
        File archivo = new File("alumnes.xml");
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	Document document = documentBuilder.parse(archivo);
	
        document.getDocumentElement().normalize();
        
        try {
            int conta = 1;
            NodeList llistaAlumnat = document.getDocumentElement().getChildNodes();
            
            for (int i = 0; i < llistaAlumnat.getLength(); i++) {
                Node node = llistaAlumnat.item(i);
                 
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element eElement = (Element) node;
                    
                    if (node.hasChildNodes()) {
                        Element el = (Element) node;
			el.setIdAttribute("id", true);
			conta++;
                        
                    }
                    
                }
                
            }
            
            boolean stop = false;
        
            while (!stop) {
		System.out.println("0 - Sortir");
		System.out.println("1 - Veure XML:");
		System.out.println("2 - Modificar XML:");
		System.out.println("3 - Veure XML des de ID");
                int opcio = teclat.nextInt();
                
                switch (opcio) {
                    case 0:
                        stop = true;
                        break;
                    case 1:
                        try {
                            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
                            System.out.println();

                            for (int i = 0; i < llistaAlumnat.getLength(); i++) {
				Node node = llistaAlumnat.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					if (element.hasChildNodes()) {
                                            NodeList nl = node.getChildNodes();
                                            System.out.print(element.getNodeName());
                                            NamedNodeMap atributeList = element.getAttributes();
									
					for (int j = 0; j < atributeList.getLength(); j++) {				
                                            System.out.print(" " + atributeList.item(j).getNodeName() + ": " + atributeList.item(j).getTextContent());
					
                                        }
					
                                        System.out.println();
					recursiva(nl);
					
                                        }
					
                                        System.out.println();
					}
				}

			} catch (Exception e) {
                            e.printStackTrace();
			}
                        break;
                
                case 2:
                    
                   System.out.println("1 Editar Elements");
                   System.out.println("2 Editar Atributs");
                   int opcion = teclat.nextInt();
                   
                   Element nom;
                   Element cognom1;
                   Element cognom2;
                   Element notaFinal;
                   
                   int id;

                   switch(opcion) {
                       case 1:
                            System.out.println("1 - Crear Element");
                            System.out.println("2 - Modificar Element");
                            System.out.println("3 - Eliminar Element");
                            opcion = teclat.nextInt();
                            teclat.nextLine();
                            
                            switch(opcion){
                                case 1:

                                    Element element = document.getDocumentElement();

                                    Element element2 = document.createElement("alumne");
                                    element2.setAttribute("id", String.valueOf(conta));

                                    element.appendChild(element2);

                                    nom = document.createElement("nom");
                                    System.out.println("Introduiex el nom");
                                    nom.appendChild(document.createTextNode(teclat.nextLine()));
                                    element2.appendChild(nom);

                                    cognom1 = document.createElement("cognom1");
                                    System.out.println("Introduiex el primer cognom");
                                    cognom1.appendChild(document.createTextNode(teclat.nextLine()));
                                    element2.appendChild(cognom1);

                                    cognom2 = document.createElement("cognom2");
                                    System.out.println("Introduiex el segon cognom");
                                    cognom2.appendChild(document.createTextNode(teclat.nextLine()));
                                    element2.appendChild(cognom2);

                                    notaFinal = document.createElement("notaFinal");
                                    System.out.println("Introdueix la nota final");
                                    notaFinal.appendChild(document.createTextNode(teclat.nextLine()));
                                    element2.appendChild(notaFinal);

                                    conta++;
                                    
                                    break;

                                case 2:

                                    System.out.println("Introdueix el ID a modificar");

                                    id = teclat.nextInt();
                                    teclat.nextLine();

                                    Element elementId = document.getElementById(String.valueOf(id));

                                    System.out.println(elementId.getNodeName() + " id: " + elementId.getAttribute("id"));

                                    if (elementId.hasChildNodes()) {

                                        NodeList nl = elementId.getChildNodes();

                                        while (nl.getLength()!=0) {
                                            Node n = nl.item(0);
                                            elementId.removeChild(n);
                                        }
                                    }

                                    nom = document.createElement("nom");
                                    System.out.println("Introdueix el nom");
                                    nom.appendChild(document.createTextNode(teclat.nextLine()));
                                    elementId.appendChild(nom);
                                    
                                    cognom1 = document.createElement("cognom1");
                                    System.out.println("Introdueix el primer cognom");
                                    cognom1.appendChild(document.createTextNode(teclat.nextLine()));
                                    elementId.appendChild(cognom1);

                                    cognom2 = document.createElement("cognom2");
                                    System.out.println("Introdueix el segon cognom");
                                    cognom2.appendChild(document.createTextNode(teclat.nextLine()));
                                    elementId.appendChild(cognom2);

                                    notaFinal = document.createElement("notaFinal");
                                    System.out.println("Introdueix la nota final ");
                                    notaFinal.appendChild(document.createTextNode(teclat.nextLine()));
                                    elementId.appendChild(notaFinal);
                                    
                                    break;
                                
                                case 3:

                                    System.out.println("Introdueix la ID del element que vols eliminar");

                                    id = teclat.nextInt();

                                    Element idElement = document.getElementById(String.valueOf(id));

                                    if (idElement.hasChildNodes()) {

                                        NodeList nl = idElement.getChildNodes();

                                        while (nl.getLength()!=0) {
                                            Node n = nl.item(0);
                                            idElement.removeChild(n);
                                        }
                                    }

                                    idElement.getParentNode().removeChild(idElement);
                                    
                                    break;
                            } break;
                            
                       case 2:    

				System.out.println("1 Crear Atribut");
				System.out.println("2 Modificar Atribut");
				System.out.println("3 Eliminar Atribut");
				opcion = teclat.nextInt();
                                
                                Element element;

                                switch (opcion) {
                                    case 1:

                                        System.out.println("Introdueix la id del element al que vols crear l'atribut");

                                        id = teclat.nextInt();

                                        element = document.getElementById(String.valueOf(id));

                                        System.out.println("Introdueix el nom del atribut:");
                                        String nomAtribut = teclat.next();
                                        System.out.println("Introdueix el valor del atribut");
                                        String valorAtribut = teclat.next();
                                        element.setAttribute(nomAtribut, valorAtribut);
                                        
                                        break;

						
                                    case 2:

                                        System.out.println("Introdueix ID del element al que vols cambiar l'atribut");

                                        int seleccioId = teclat.nextInt();

                                        element = document.getElementById(String.valueOf(seleccioId));

                                        System.out.println("Introdueix la nova id");
                                        element.setAttribute("id", String.valueOf(teclat.nextInt()));

					break;
                                        
                                    case 3:

                                        System.out.println("Introdueix ID del element al que vols eliminar l'atribut");

                                        id = teclat.nextInt();

                                        element = document.getElementById(String.valueOf(id));

                                        element.removeAttribute("id");
                                        
                                        break;
                                }
                       }
                            

                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        Result output = new StreamResult(new File("alumnesResultat.xml"));
                        Source input = new DOMSource(document);

                        transformer.transform(input, output);

                        stop = true;
                    
                        break;

               case 3:        

                    System.out.println("Introdueix la ID:");
                    int seleccioId = teclat.nextInt();
                    Element idElement = document.getElementById(String.valueOf(seleccioId));

                    System.out.println(idElement.getNodeName() + " id: " + idElement.getAttribute("id"));

                    if (idElement.hasChildNodes()) {

                        Node node = idElement;
                        NodeList nl = node.getChildNodes();
                        recursiva(nl);
                    } 
                        
                    break;
                   
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
    
    public static void recursiva (NodeList nodelist){

	for(int i=0; i < nodelist.getLength(); i++) {
            Node node = nodelist.item(i);
			
		if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element el = (Element) node;
				
                        if (el.hasAttributes()) {
				mostrarAtributs(el);
                                
			}
		}
                
		String nodeName = node.getNodeName();
		
                if (!nodeName.equals("#text")){
                    System.out.println(nodeName + ": " + node.getTextContent());
                    
		}
		
                if (node.hasChildNodes()){
                    NodeList ncn = node.getChildNodes();
                    recursiva(ncn);
		}
	}
    }
	
	// Funcio que mostra els atributs
    public static void mostrarAtributs (Element element) {
		
        NamedNodeMap atributeList = element.getAttributes();
		
	for (int j = 0; j < atributeList.getLength(); j++) {
            System.out.print(" " + atributeList.item(j).getNodeName() + ": " + atributeList.item(j).getTextContent());
            
	}
        
        System.out.println();
    }
}