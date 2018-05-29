/**
 * 
 */
package operacoes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import problema.Problema;
import solucao.Individuo;

/**
 * @author wagne
 *
 */
public class BuscaLocalCombinatorio {

	
	Problema problema;

	public BuscaLocalCombinatorio(Problema problema) {
		super();
		this.problema = problema;
	}
	
	
	public void executar ( Individuo individuo) {
		// Movimentos/operações
		
		ArrayList<Integer> operacoes = new ArrayList<>(Arrays.asList(1,2,4));
		Collections.shuffle(operacoes);
		
		for (Integer i : operacoes) {
			switch(i) {
			case 1:
				buscaLocalRemoverUV(individuo);
				break;
			case 2:
				buscaLocalRemoverUXaposV(individuo);
				break;
			case 4:
				buscaLocalSwap(individuo);
				break;
			case 5:
			//	buscaLocalSwapUX(individuo);
				break;
			
			}
		}
		
		
		
		
	}
	
	// Trocar U e V
	public void buscaLocalSwap(Individuo individuo) {
		// Custo atual
		Double foAtual = individuo.getFuncaoObjetivo();
		for (int u = 0 ; u < individuo.getCromossomos().size()-1; u++) {
			for (int v = u + 1 ; v < individuo.getCromossomos().size(); v++) {
			// Operar Swap (u;v)
				Collections.swap(individuo.getCromossomos(), u, v);
				// Calcular a diferença
				
				problema.calcularFuncaoObjetivo(individuo);
				
				// Verificar se existe melhora
				if (individuo.getFuncaoObjetivo() < foAtual) {
					// Encerrar first improvement
					return;
				}else { // não existe melhora
					// Desfazer a troca
					Collections.swap(individuo.getCromossomos(), u, v);
					// Retorna o valor da F0
					individuo.setFuncaoObjetivo(foAtual);
				}
			}
			
		}
		
	}
	// Remover U e inserir após V
	
	public void buscaLocalRemoverUV(Individuo individuo) {
		Double foAtual = individuo.getFuncaoObjetivo();
		for (int u = 0; u < individuo.getCromossomos().size()-1; u++) {
			for (int v = u +1 ; v < individuo.getCromossomos().size(); v++) {
				Integer valorU = (Integer) individuo.getCromossomos().get(u);
				// Remover U
				individuo.getCromossomos().remove(u);
				// Inserir após V
				individuo.getCromossomos().add(v,valorU);
				
				// Calcular o custo
				problema.calcularFuncaoObjetivo(individuo);
				
				// Se existe melhora
				if (individuo.getFuncaoObjetivo() < foAtual) {
					return;
							
				} else {
					// Desfazer a troca
					//Remover de V
					individuo.getCromossomos().remove(v);
					// Inserir novamente em U
					individuo.getCromossomos().add(u,valorU);
					
					//Valor atual da FO
					individuo.setFuncaoObjetivo(foAtual);
				}
				
			}
		}
		
	}
	// remover u e x e inserir u e x após v;
	public void buscaLocalRemoverUXaposV(Individuo individuo) {
		Double foAtual = individuo.getFuncaoObjetivo();
		for (int u = 0 ; u < individuo.getCromossomos().size()-2 ; u++) {
			int x = u + 1;
			for (int v = x + 1; v < individuo.getCromossomos().size(); v++) {
				Integer valorU = (Integer) individuo.getCromossomos().get(u);
				Integer valorX = (Integer) individuo.getCromossomos().get(x);
				Integer valorV = (Integer) individuo.getCromossomos().get(v);
				
				// Remover U e X
			
				individuo.getCromossomos().remove(x);
				individuo.getCromossomos().remove(u);
				
				// Inserir U e X após V
				int posV = individuo.getCromossomos().indexOf(valorV);
				individuo.getCromossomos().add(posV +1, valorU);
				individuo.getCromossomos().add(posV +2, valorX);
		
				
				
				// Calcular F0
				problema.calcularFuncaoObjetivo(individuo);
				
				// Se existe melhora
				if (individuo.getFuncaoObjetivo() < foAtual) {
					return;
							
				} else {
					// Desfazer a troca
					//Remover de V
					individuo.getCromossomos().remove(valorU);
					individuo.getCromossomos().remove(valorX);
					// Inserir novamente em U
					individuo.getCromossomos().add(u,valorU);
					individuo.getCromossomos().add(x,valorX);
					
					//Valor atual da FO
					individuo.setFuncaoObjetivo(foAtual);
				}
				
			}
		}

		
	}
	//remover u e x e inserir x e u após v; (posições invertidas)
	public void buscaLocalRemoverXUaposV(Individuo individuo) {
		Double foAtual = individuo.getFuncaoObjetivo();
		for (int u = 0; u < individuo.getCromossomos().size()-1; u++) {
			for (int v = u +2 ; v < individuo.getCromossomos().size(); v++) {
				 
				Integer valorV = (Integer) individuo.getCromossomos().get(v);
				
				int x = u+1;
				Collections.swap(individuo.getCromossomos(), u, x);
				individuo.getCromossomos().remove(v);
				individuo.getCromossomos().add(u,valorV);
				 
				
				// Calcular o custo
				problema.calcularFuncaoObjetivo(individuo);
				
				// Se existe melhora
				if (individuo.getFuncaoObjetivo() < foAtual) {
					return;
							
				} else {
					//Remover de V
					individuo.getCromossomos().remove(u);
					// Desfazer a troca
					Collections.swap(individuo.getCromossomos(), u, x);
			
					// Inserir novamente em U
					individuo.getCromossomos().add(v,valorV);
					
					//Valor atual da FO
					individuo.setFuncaoObjetivo(foAtual);
				}
				
			}
		}
		
	}
	// Trocar U e X com V
	public void buscaLocalSwapUX(Individuo individuo) {
		// Custo atual
		Double foAtual = individuo.getFuncaoObjetivo();
		for (int u = 0 ; u < individuo.getCromossomos().size()-1; u++) {
			for (int v = u + 2 ; v < individuo.getCromossomos().size(); v++) {
			// Operar Swap (u;v)
				Collections.swap(individuo.getCromossomos(), v, u);
				int x = u+1;
				 
				Collections.swap(individuo.getCromossomos(),x, v);
				// Calcular a diferença
				
				problema.calcularFuncaoObjetivo(individuo);
				
				// Verificar se existe melhora
				if (individuo.getFuncaoObjetivo() < foAtual) {
					// Encerrar first improvement
					
					return;
				}else { // não existe melhora
					// Desfazer a troca
					Collections.swap(individuo.getCromossomos(), v, u);		 
					 
					Collections.swap(individuo.getCromossomos(),x, u);
					// Retorna o valor da F0
					individuo.setFuncaoObjetivo(foAtual);
				}
			}
			
		}
		
	}
}
