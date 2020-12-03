/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex06;

/**
 *
 * @author Oriol Poveda
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cotxes {
    
    private Cotxe[] cotxes;
	
	public Cotxe[] getCotxes() {
		return cotxes;
	}
	public void setCotxes(Cotxe[] cotxes) {
		this.cotxes = cotxes;
	}
    
}
