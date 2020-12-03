/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex08;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Oriol
 */

@Entity
public class Vehicles implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private long id;
    
    private String model;
    private Date fabricacio;
    private int cv;
    private boolean manual;
    
    public Vehicles (String model, Date fabricacio, int cv, boolean manual) {
        this.model = model;
        this.fabricacio = fabricacio;
        this.cv = cv;
        this.manual = manual;        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getFabricacio() {
        return fabricacio;
    }

    public void setFabricacio(Date fabricacio) {
        this.fabricacio = fabricacio;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    @Override
    public String toString() {
        String s="";
        s+= "Propietari: ";
        s+= "\n\tModel: " + getModel() + "\n";
        s+= "\tData de fabricacio: " + getFabricacio() + "\n";
        s+= "\tCV: " + getCv() + "\n";
        s+= "\tNovel: " + isManual() + "\n";
        
        return s;
    }

    
    
}
