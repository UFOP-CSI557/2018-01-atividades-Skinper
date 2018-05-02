/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author fernando
 */
public class AGTestes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

         Problema problemaReal = new  Problema();


        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;

        int repeticoes = 30;
        Integer geracoes = 300;
        // Parametros nao modificados
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;

        // Casos de teste
        //1- Real1  , 2- Real2
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("REAL1", "REAL2"));
        for (int i = 1; i <= repeticoes; i++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1,2));
            Collections.shuffle(casos);

            for (int c = 1; c <= casos.size(); c++) {

                Integer tamanho = 50;
                agreal.AlgoritmoGenetico agReal;

                Double result = 0.0;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c - 1);

                switch (teste) {

                    case 1:
                    	 tamanho = 100;
                         pCrossover = 0.004;
                         pMutacao = 0.1;
                        break;

                    case 2:
                        tamanho = 50;
                        pCrossover = 0.01;
                        pMutacao = 0.7;
                        break;
  

                }

//                
                    agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaReal, minimo, maximo, nVariaveis);
                    result = agReal.executar();
//                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + ";" + i + ";" + result + ";" + "Pior" + ";"  + agReal.piorResultado + ";" + "tempo" + ";" +   totalTime);
                System.out.flush();

            }

        }

    }

}
