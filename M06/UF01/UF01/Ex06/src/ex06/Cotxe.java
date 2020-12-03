/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex06;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oriol Pove
 */

//@XmlRootElement
@XmlRootElement(name = "Cotxe")

//@XmlAccessorType(XmlAccessType.FIELD) // This line was added
public class Cotxe {
        private int id;
        private String Marca;
        private String Model;
        private String Matricula;
        
       
    @XmlAttribute(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @XmlAttribute(name="marca")
    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    @XmlAttribute(name="model")
    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    @XmlAttribute(name="matricula")
    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

}
