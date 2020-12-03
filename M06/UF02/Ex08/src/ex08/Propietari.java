
package ex08;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Oriol Poveda
 */

@Entity
public class Propietari implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private long id;
    
    private String nom;
    private Date naixement;
    private int edat;
    private boolean novel;
    

    public Propietari(String nom, Date naixement, int edat, boolean novel) {
        this.nom = nom;
        this.naixement = naixement;
        this.edat = edat;
        this.novel = novel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getNaixement() {
        return naixement;
    }

    public void setNaixement(Date naixement) {
        this.naixement = naixement;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public boolean isNovel() {
        return novel;
    }

    public void setNovel(boolean novel) {
        this.novel = novel;
    }

    @Override
    public String toString() {
        String s="";
        s+= "Propietari: ";
        s+= "\n\tnom: " + getNom() + "\n";
        s+= "\tnaixement: " + getNaixement() + "\n";
        s+= "\tedat: " + getEdat() + "\n";
        s+= "\tnovel: " + isNovel() + "\n";
        
        return s;
    }
    
    
    
}
