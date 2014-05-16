/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calingaert;

/**
 *
 * Coloquem seus nomes.
 * @author michael
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Memoria {
    int acumulador;       //guarda valor que esta carregada para uso
    int poscontador;    //guarda a possição que esta sendo lida do veotr codigo
    private int codigo[]=new int[100];
    private String nomeArq;
    
    public Memoria(){
        acumulador=0;
        poscontador=0;
        nomeArq="arquivo.txt";
    }
    
    public void getCodigo(){
        int cont=0;
        while(codigo[cont]!='\0'){
               System.out.println(codigo[cont]);
               cont++;
        }
    }
    
    public void setCodigo() throws IOException{
        int iterador, tamanhostring, controle;            //usado para for e controle de cast
        int cont=0, flag=0;             
        String guarda="";
        String um= null;
        String dois= null;
        String tres= null;
        
        try {
            FileReader reader = new FileReader(nomeArq);    //abre arquivo para leitura

            BufferedReader leitor = new BufferedReader(reader); //define inicio da leitura
            while(true){
                guarda=leitor.readLine();                       //le uma linha
                if(guarda==null){                               //acha o final do arquivo
                    break;
                }
                tamanhostring=guarda.length();                  //pega o tamanho da string para cast
                
                //controle dos espaços para colocar no codigo com cast

                um=null;
                dois=null;
                tres=null;
                controle=0;
                flag=0;
                for(iterador=0;iterador<tamanhostring;iterador++){
                    if((guarda.substring(iterador,iterador+1).equals(" "))&&(flag==0)){
                        um = guarda.substring(0,iterador);
                        iterador++;
                        controle=iterador;
                        flag=1;
                    }
                    else if((guarda.substring(iterador,iterador+1).equals(" "))&&(flag==1)){
                        System.out.println("Entro3");
                        dois = guarda.substring(controle,iterador);
                        iterador++;
                        controle=iterador;
                        tres = guarda.substring(controle,tamanhostring);
                        flag=2;
                        iterador=tamanhostring;    //corta a ultima avaliação
                    }
                    else if(flag==1){                       //ERRO tem que diferenciar de espaço e numero
                        System.out.println("Entro2");
                        dois = guarda.substring(controle,tamanhostring);
                    }
                    else if((flag==0)){                     //ERRO tem que diferenciar de espaço e numero
                        System.out.println("Entro1");
                        um = guarda.substring(controle,tamanhostring);
                    }
                }
                
                //cast colocando string lida do txt em cada posição do int codigo
                
                if(um!=null){
                    codigo[cont]=Integer.parseInt(um);
                    cont++;
                }
                if(dois!=null){
                    codigo[cont]=Integer.parseInt(dois) ;
                    cont++;
                }
                if(tres!=null){
                    codigo[cont]=Integer.parseInt(tres) ;
                    cont++;
                }
            }
            codigo[cont]='\0';//fecha a memoria
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Calingaert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int Compilador(){
        int opd1,opd2,r=0;  //posição de operadores que serão usados nas operações
        //1--add cod.maq=02
        if(codigo[poscontador]==02){
            opd1=poscontador+1;
            acumulador+=codigo[opd1];
            poscontador=opd1+1;
        }
        //2--br cod.maq=00
        else if(codigo[poscontador]==00){
            opd1=poscontador+1;
            poscontador=codigo[opd1];
            poscontador=opd1+1;
        }
        //3--brneg cod.maq=05
        else if(codigo[poscontador]==05){
            if(acumulador<0){
                opd1=poscontador+1;
                poscontador=codigo[opd1];
            }
            else{
                poscontador=poscontador+2;
            }
        }
        //4--brpos cod.maq=01
        else if(codigo[poscontador]==01){
            if(acumulador>0){
                opd1=poscontador+1;
                poscontador=codigo[opd1];
            }
            else{
                poscontador=poscontador+2;
            }
        }
        //5--brzero cod.maq=04
        else if(codigo[poscontador]==04){
            if(acumulador==00){
                opd1=poscontador+1;
                poscontador=codigo[opd1];      
            }
            else{
                poscontador=poscontador+2;
            }
        }
        //6--copy cod.maq=13
        else if(codigo[poscontador]==13){
            opd1=poscontador+1;
            opd2=poscontador+2;
            codigo[opd2]=codigo[opd1];
            poscontador=opd2+1;
        }
        //7--divide cod.maq=10
        else if(codigo[poscontador]==10){
            opd1=poscontador+1;
            acumulador=acumulador/codigo[opd1];
            poscontador=opd1+1;
        }
        //8--load cod.maq=03
        else if(codigo[poscontador]==03){
            opd1=poscontador+1;
            acumulador=codigo[opd1];
            poscontador=opd1+1;
        }
        //9--mult cod.maq=14
        else if(codigo[poscontador]==14){
            opd1=poscontador+1;
            acumulador=acumulador*codigo[opd1];
            poscontador=opd1+1;
        }
        //10--read cod.maq=12
        else if(codigo[poscontador]==12){
            opd1=poscontador+1;
            codigo[opd1]=acumulador;            //input stream--confirmar se eh acumulador
            poscontador=opd1+1;
        }
        //11--stop cod.maq=11
        else if(codigo[poscontador]==11){
            r=codigo[poscontador];
        }
        //12--store cod.maq=07
        else if(codigo[poscontador]==07){
            opd1=poscontador+1;
            codigo[opd1]=acumulador;
            poscontador=opd1+1;
        }
        //13--sub cod.maq=06
        else if(codigo[poscontador]==06){
            opd1=poscontador+1;
            acumulador=acumulador-codigo[opd1];
            poscontador=opd1+1;
        }
        //14--write cod.maq=08
        else if(codigo[poscontador]==8){
            opd1=poscontador+1;
            acumulador=codigo[opd1];                //verificar se esta certo
            poscontador=opd1+1;
        }
        return r;                             //para terminar execução
    }
    
    public boolean Controle(){
        boolean retorno;    //valor retornado para controle do final do vetor
        if(codigo[poscontador]=='\n'){
            retorno=false;
        }
        else{
            retorno=true;
        }
        
        return retorno;
    }
    
    public int GetAcumulador(){         //devolve para mostrar na tela o valor que esta na carga para uso.
        return acumulador;
    }
    
    public int GetContador(){      //devolve para mostrar na tela a posiçao que esta sendo linda do contador
        return poscontador;
    }
    
}
