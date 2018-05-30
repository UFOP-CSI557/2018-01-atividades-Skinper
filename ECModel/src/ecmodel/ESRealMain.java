package ecmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import metodo.DEReal;
import metodo.ESReal;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import problema.ProblemaSchwefel;
import solucao.Individuo;

public class ESRealMain {

	
public static void main(String[] args) {
	
	Double minimo = -500.0;
	Double maximo = 500.0;
	Integer nVariaveis = 50;
	Problema problema = new ProblemaSchwefel(nVariaveis);
	
	int mutacao = 0;
	
	// Parâmetros - ES
	Integer mu = 100; // Tamanho da populacao
	Integer lambda = 10000; // numero de descendentes
	Integer geracoes = 300; // criterio de parada
	Double pMutacao = 0.0; // mutacao - aplicacao ao descendente - variacao/pertubacao
	
	// Parametros - DE
	 double F = 0.1;
     double Cr = 0.9;
	
	   int repeticoes = 30;
       
       // Casos de teste
       //1- ESReal1  , 2- ESReal2
       ArrayList<String> nomes = new ArrayList<>(Arrays.asList("ESREAL1", "ESREAL2"));
       for (int i = 1; i <= repeticoes; i++) {
           ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1,2));
           Collections.shuffle(casos);

           for (int c = 1; c <= casos.size(); c++) {

         

               Double result = 0.0;
               long startTime = System.currentTimeMillis();

               int teste = casos.get(c - 1);

               switch (teste) {

                   case 1: // Mutação Uniforme
                	  mu = 100; // Tamanho da populacao
                	  lambda  = 5000; // numero de descendentes a
                	   pMutacao = 0.7; // mutacao - aplicacao ao descendente - variacao/pertubacao
                		  F = 0.1;
                		  Cr = 0.9;
                	   mutacao = 1;
                       break;

                   case 2: // Mutação por Variaveis           
                		 mu = 100; // Tamanho da populacao
                		lambda = 10000; // numero de descendentes
                	   pMutacao = 0.2; // mutacao - aplicacao ao descendente - variacao/pertubacao
                	   F = 1.0;
             		  	Cr = 0.2;
                	   mutacao = 2;
                       break;
 

               }
        
               ESReal esReal  = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao , mutacao,F,Cr);
                   result = esReal.executar().getFuncaoObjetivo();
//               }

               long endTime = System.currentTimeMillis();
               long totalTime = endTime - startTime;

               System.out.println(  i + ";" + result +   ";" +   totalTime);
               System.out.flush();

           }

       }
 
 
	
	
}
}
