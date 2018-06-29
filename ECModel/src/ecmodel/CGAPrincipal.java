package ecmodel;

import metodo.CompactGA;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.Individuo;

public class CGAPrincipal {

	public static void main(String[] args) {
		Double minimo = -5.12;
		Double maximo = 5.12;
		
		Integer n = 100; // tamanho da população
		Integer l = 100; // quantidade de variaveis
		Integer precisao = 10;
		
		Integer gmax = 100000;
		
		Problema problema = new ProblemaRastrigin(l);
		
		CompactGA cGA = new CompactGA(problema,minimo,maximo,precisao,n,l,gmax);
		Individuo resultado = cGA.executar();
		System.out.println(resultado);
		
	}

}
