/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import metodo.DEReal;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class DERealPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Double minimo = -5.12;
        Double maximo = 5.12;
        int gmax = 300; // Numero de gerações
        int D = 100; // Dimensões
        Problema problema = new ProblemaRastrigin(D);
        
       
        int Np = 30;
        
        double F = 0.001;
        double Cr = 0.9;
        
        //
        int repeticoes = 30;
        
        // Casos de teste
        //1- DEReal1  , 2- DEReal2
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("DEREAL1", "DEREAL2"));
        for (int i = 1; i <= repeticoes; i++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1,2));
            Collections.shuffle(casos);

            for (int c = 1; c <= casos.size(); c++) {

          

                Double result = 0.0;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c - 1);

                switch (teste) {

                    case 1:
                    	Np = 60; // tamanho da população
                    	F = 0.9; // Peso
                    	Cr = 0.2; // Crossover
                        break;

                    case 2:
                    	Np = 60;
                    	F = 0.001;
                        Cr = 0.9;
                        break;
  

                }

                	DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
                  //  agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaReal, minimo, maximo, nVariaveis);
                    result = deReal.executar().getFuncaoObjetivo();
//                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + ";" + i + ";" + result + ";" + "Pior" + ";"  + deReal.piorResultado + ";" + "tempo" + ";" +   totalTime);
                System.out.flush();

            }

        }
        
        
        
//        
//        DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
//        
//        Individuo resultado = deReal.executar();
//        System.out.println(resultado);
        
        
    }
    
}