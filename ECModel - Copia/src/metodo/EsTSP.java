package metodo;

import java.util.Collections;
import java.util.Random;

import operacoes.BuscaLocalCombinatorio;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoInteger;
import solucao.PopulacaoInteger;

public class EsTSP implements Metodo{
	// Parametros - TSP
	private Problema problema;
	
	//Parametros - ES
	private Integer mu;
	private Integer lambda;
	private Integer geracoes;
	private Double pMutacao;
	private Double pBuscaLocal; // individuos/ descendentes que ser�o submetidos ao procedimento de busca local
	
		
	public EsTSP(Problema problema, Integer mu, Integer lambda, Integer geracoes, Double pMutacao, Double pBuscaLocal) {
		super();
		this.problema = problema;
		this.mu = mu;
		this.lambda = lambda;
		this.geracoes = geracoes;
		this.pMutacao = pMutacao;
		this.pBuscaLocal = pBuscaLocal;
	}


	@Override
	public Individuo executar() {
		// TODO Auto-generated method stub
		
		PopulacaoInteger populacao = new PopulacaoInteger(this.mu, this.problema);
		
		populacao.criar();
		
		// Avaliar
		populacao.avaliar();
		
		PopulacaoInteger descendentes = new  PopulacaoInteger(this.mu, this.problema);
		
		Random rnd = new Random();
		
		// Geracoes - criterio de parada
		for ( int g = 1; g<= this.geracoes; g++) {
			// Para cada individuo
			for (int i = 0 ; i < populacao.getIndividuos().size(); i++) {
			
			//Gerar descendentes - lambda / mu
				for (int d = 0 ; d < lambda / mu ; d++) {
					// Clonar individuo
					
					IndividuoInteger filho = (IndividuoInteger)populacao.getIndividuos().get(i).clone();
					
					// Mutacao
					mutacaoSWAP(filho);
					
					//Avaliar
					problema.calcularFuncaoObjetivo(filho);
					
					//Busca Local
					if ( rnd.nextDouble() <= this.pBuscaLocal ) {
						// Filho
						BuscaLocalCombinatorio bl = new BuscaLocalCombinatorio(problema);
						bl.executar(filho);
						
					}
					
					// Inserir em descendentes
					descendentes.getIndividuos().add(filho);
					
					
				}
			}
			
		// (mu, lambda) - ES
			
		// (mu+lambda) - ES
			populacao.getIndividuos().addAll(descendentes.getIndividuos());
			// Ordernar Mu + Lambda
			Collections.sort(populacao.getIndividuos());
			
			//Definir sobreviventes
			
			populacao.getIndividuos().subList(this.mu, populacao.getIndividuos().size()).clear();
			//Limpar descendentes
			descendentes.getIndividuos().clear();
			
			System.out.println("G = " + g + "\t" + populacao.getMelhorIndividuo().getFuncaoObjetivo());
		}
		
		
		
		return populacao.getMelhorIndividuo();
	}
	
	private void mutacaoSWAP (Individuo individuo) {
		Random rnd = new Random();
		
		//Verificar a troca para cada cidade - posi��o
		for (int i = 0 ; i < individuo.getCromossomos().size(); i++) {
			
			if (rnd.nextDouble() <= this.pMutacao) {
				
			//Muta��o swap -  troca entre duas cidades
			 int j;
			 do {
				 j = rnd.nextInt(this.problema.getDimensao());
			 }while (i == j);
			 
			 Collections.swap(individuo.getCromossomos(), i, j);
				
				
				
				
			}
			
			
		}
		
	}
	

}
