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
        int r;
        Memoria var=new Memoria();  //cria objeto
        
        var.setCodigo();
        var.getCodigo();
        /*while(var.Controle()){
            r=var.Compilador();
            var.getCodigo();
            if(r==11){
                break;
            }
            //parte de impreçao dos controles
        }*/
    }
}
