package ecmodel;

import metodo.EsTSP;
import problema.Problema;
import problema.ProblemaTSP;
import solucao.Individuo;

public class EsTSPMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Problema problema = new ProblemaTSP("C:\\Users\\wagne\\OneDrive\\Wagner - UFOP\\Nono Período\\Computação Evolucionária\\Nova pasta\\instances\\tsplib\\berlin52.tsp");
		
		//Parametros - ES
		Integer mu = 20;
		Integer lambda = 100;
		Integer geracoes = 1000;
		Double pMutacao = 0.01;
		Double pBuscaLocal = 0.8;
		EsTSP esTsp = new EsTSP(problema, mu, lambda, geracoes, pMutacao, pBuscaLocal);
		Individuo melhor = esTsp.executar();
		
		System.out.println(melhor);
	}

}
