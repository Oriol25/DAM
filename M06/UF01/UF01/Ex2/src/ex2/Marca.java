/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;

import java.io.Serializable;

/**
 *
 * @author Oriol Poveda
 */
public class Marca implements Serializable {
    
    private String marca;
    private String model;
    private int any;
    private String matricula;
    
    public Marca (String marca, String model, int any, String matricula){
        this.marca = marca;
        this.model = model;
        this.any = any;
        this.matricula = matricula;
        
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
    
}
