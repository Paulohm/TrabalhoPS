/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calingaert;

/**
 *
 * @author michael
 */

import java.io.IOException;

public class Calingaert {

    /**
     * @param args the command line arguments
     */

        
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    
        Memoria var=new Memoria();
        var.setCodigo();
        var.getCodigo();
        while(var.Controle()){
            var.Compilador();
            var.getCodigo();
            //parte de impreçao dos controles
        }
    }
}
