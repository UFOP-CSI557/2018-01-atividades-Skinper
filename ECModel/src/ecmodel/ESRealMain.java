package ecmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import metodo.DEReal;
import metodo.ESReal;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.Individuo;

public class ESRealMain {

	
public static void main(String[] args) {
	
	Double minimo = -5.12;
	Double maximo = 5.12;
	Integer nVariaveis = 100;
	Problema problema = new ProblemaRastrigin(nVariaveis);
	
	int mutacao = 0;
	
	// Parâmetros - ES
	Integer mu = 20; // Tamanho da populacao
	Integer lambda = 100; // numero de descendentes
	Integer geracoes = 300; // criterio de parada
	Double pMutacao = 0.0; // mutacao - aplicacao ao descendente - variacao/pertubacao
	
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

                   case 1:
                	   mu = 20; // Tamanho da populacao
                	   lambda = 100; // numero de descendentes
                	   pMutacao = 0.02; // mutacao - aplicacao ao descendente - variacao/pertubacao
                	   mutacao = 1;
                       break;

                   case 2:
                	   mu = 20; // Tamanho da populacao
                	   lambda = 300 ; // numero de descendentes
                	   pMutacao = 0.02; // mutacao - aplicacao ao descendente - variacao/pertubacao
                	   mutacao = 2;
                       break;
 

               }
        
               ESReal esReal  = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao , mutacao);
                   result = esReal.executar().getFuncaoObjetivo();
//               }

               long endTime = System.currentTimeMillis();
               long totalTime = endTime - startTime;

               System.out.println(nomes.get(teste - 1) + ";" + i + ";" + result + ";" + "Pior" + ";"  + esReal.piorResultado + ";" + "tempo" + ";" +   totalTime);
               System.out.flush();

           }

       }
 
 
	
	
}
}
