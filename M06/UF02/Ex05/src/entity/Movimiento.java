package entity;
// Generated 31-ene-2021 21:21:17 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Movimiento generated by hbm2java
 */
public class Movimiento  implements java.io.Serializable {

     private Integer idMov;
     private int filaOrigen;
     private int columnaOrigen;
     private int filaPos;
     private int columnaPos;
     private Partida partida;

	
    public Movimiento(Partida partida, int filaOrigen, int columnaOrigen, int filaPos, int columnaPos) {
        this.partida = partida;
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.filaPos = filaPos;
        this.columnaPos = columnaPos;
    }
   
    @Id
    @GeneratedValue
    @Column(name="id_mov")
    public Integer getIdMov() {
        return this.idMov;
    }
    
    public void setIdMov(Integer idMov) {
        this.idMov = idMov;
    }
    
    @Column(name="filaOrigen")
    public int getFilaOrigen() {
        return this.filaOrigen;
    }
    
    public void setFilaOrigen(int filaOrigen) {
        this.filaOrigen = filaOrigen;
    }
    
    @Column(name="columnaOrigen")
    public int getColumnaOrigen() {
        return this.columnaOrigen;
    }
    
    public void setColumnaOrigen(int columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }
    
    @Column(name="filaPos")
    public int getFilaPos() {
        return this.filaPos;
    }
    
    public void setFilaPos(int filaPos) {
        this.filaPos = filaPos;
    }
    
    @Column(name="ColumnaPos")
    public int getColumnaPos() {
        return this.columnaPos;
    }
    
    public void setColumnaPos(int columnaPos) {
        this.columnaPos = columnaPos;
    }
    
    
    public Partida getPartida() {
        return this.partida;
    }
    
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}


