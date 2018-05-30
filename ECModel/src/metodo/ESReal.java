/**
 * 
 */
package metodo;

import java.util.Collections;
import java.util.Random;

import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

/**
 * @author wagne
 *
 */
public class ESReal implements Metodo {
	
	private Double minimo;
	private Double maximo;
	private Integer nVariaveis;
	private Problema problema;
	
	// Parameetros - ES
	 
	private Integer mu; // tamanho da população
	private Integer lambda; // numero de descendentes
	private Integer geracoes; // criterio de parada
	private Double pMutacao; //  mutacao - aplicacao ao descendente - variacao/pertubação
	
	private int mutacao;
	public Double piorResultado = 0.0;
	
	

	public ESReal(Double minimo, Double maximo, Integer nVariaveis, Problema problema, Integer mu, Integer lambda,
			Integer geracoes, Double pMutacao, int mutacao) {
		super();
		this.minimo = minimo;
		this.maximo = maximo;
		this.nVariaveis = nVariaveis;
		this.problema = problema;
		this.mu = mu;
		this.lambda = lambda;
		this.geracoes = geracoes;
		this.pMutacao = pMutacao;
		this.mutacao = mutacao;
	}





	@Override
	public Individuo executar() {
		// TODO Auto-generated method stub
		// Geração da população inicial  - aleatoria - tamanho mu
		PopulacaoDouble populacao = new PopulacaoDouble(problema,minimo,maximo,nVariaveis,mu);
		populacao.criar();
		
		// Avaliar
		populacao.avaliar();
		
		// Populacao - descendentes
		PopulacaoDouble descendentes = new PopulacaoDouble();
		
		// Criterio de parada - número de gerações
		
		for (int g = 1 ; g <= this.geracoes ; g ++ ) {
	
			// Para cada individuo, gerar lambda/mu descendentes
			for(int i = 0 ; i < populacao.getIndividuos().size(); i++) {
			
				// gerar lambda/mu descentendes
				for (int d = 0; d < lambda/mu; d++) {
			
					// Clonar individuo
					IndividuoDouble filho = (IndividuoDouble)populacao.getIndividuos().get(i).clone();
					// Aplicar mutacao
					if (mutacao == 1 ) {
						mutacaoPorVariavel_2(filho);
					}
					else if (mutacao == 2) {
						mutacaoPorVariavel(filho);	
					}
				
					
					// Avaliar
					problema.calcularFuncaoObjetivo(filho);
					
					// Inserir na nova populacao de descendentes
					descendentes.getIndividuos().add(filho);
					
				}
			}
			// ES (mu, lambda)?
			//populacao.getIndividuos().clear();
			// ES (mu + lambda)?
			populacao.getIndividuos().addAll(descendentes.getIndividuos());
			// Ordernar Mu + Lambda
			Collections.sort(populacao.getIndividuos());
			
			//Definir sobreviventes
			
			populacao.getIndividuos().subList(this.mu, populacao.getIndividuos().size()).clear();
			//Limpar descendentes
			descendentes.getIndividuos().clear();
			
//			System.out.println("G = " + g + "\t" + populacao.getMelhorIndividuo().getFuncaoObjetivo());
			
		}
		  piorResultado = populacao.getIndividuos().get(populacao.getIndividuos().size()-1).getFuncaoObjetivo();
		
		// Retornar o melhor individuo 
		
		return populacao.getMelhorIndividuo();
	}
    private void mutacaoPorVariavel(Individuo individuo) {
    	int ind1, ind2, ind3;
    	Double r;
        Random rnd = new Random();
    
        for (int i = 0; i < individuo.getCromossomos().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {
 
          		 
                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = (Double) individuo.getCromossomos().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getCromossomos().set(i, valor);

                }

            }
        }

    }
    private void mutacaoPorVariavel_2(Individuo individuo) {
    	int ind1, ind2, ind3;
    	Double r;
        Random rnd = new Random();
    
        for (int i = 0; i < individuo.getCromossomos().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {
            	
                // Mutacao Uniforme
            	int n = rnd.nextInt(individuo.getCromossomos().size());
            	int n2 = rnd.nextInt(individuo.getCromossomos().size());
         
            	
               
                Double valor = (Double) individuo.getCromossomos().get(n);
                // Multiplica por rnd
                valor *= rnd.nextDouble();
                
                Double valor2 = (Double) individuo.getCromossomos().get(n2);
                // Multiplica por rnd
                valor2 *= rnd.nextDouble();
           

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getCromossomos().set(i, valor);

                }
                
                if (valor2 >= this.minimo
                        && valor2 <= this.maximo) {
                    individuo.getCromossomos().set(i, valor2);

                }

            }
        }

    }


}
