package metodo;

import java.util.ArrayList;
import java.util.Random;

import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoBinario;
import solucao.IndividuoDouble;

public class CompactGA implements Metodo {
	
	private Problema problema;
	
	private Double minimo;
	private Double maximo;
	private Integer precisao;
	
	private Integer n; // Tamanho da população
	private Integer l; // Tamanho do cromossomo
	
	Integer gmax;
	
	

	 


	public CompactGA(Problema problema, Double minimo, Double maximo, Integer precisao, Integer n, Integer l,
			Integer gmax) {
	 
		this.problema = problema;
		this.minimo = minimo;
		this.maximo = maximo;
		this.precisao = precisao;
		this.n = n;
		this.l = l;
		this.gmax = gmax;
	}


	@Override
	public Individuo executar() {
		
		// Vetor de probabilidades
		ArrayList<Double> p = new ArrayList<>();
		// Inicializar vetor
		Random rnd = new Random();
		for (int i = 0 ; i < this.l ; i ++) {
			p.add(0.5);
		}
		IndividuoDouble best = new IndividuoDouble(minimo,maximo,this.l);
		IndividuoDouble resultado = new IndividuoDouble(minimo, maximo, this.l);;
		best.setFuncaoObjetivo(Double.MAX_VALUE);
		
		int g =1 ; 
		boolean processa;
	//	for (int g = 1; g <= this.gmax; g++) {
			do {
			// Gerar a e b
			IndividuoBinario indA = this.gerarIndividuo(p);
			IndividuoBinario indB = this.gerarIndividuo(p);
			
			IndividuoDouble indDblA = indA.decodificar(this.minimo, this.maximo,this.precisao);
			IndividuoDouble indDblB = indB.decodificar(this.minimo, this.maximo,this.precisao);
			
			problema.calcularFuncaoObjetivo(indDblA);
			problema.calcularFuncaoObjetivo(indDblB);
			
			indA.setFuncaoObjetivo(indDblA.getFuncaoObjetivo());
			indB.setFuncaoObjetivo(indDblB.getFuncaoObjetivo());
			
			IndividuoBinario winner,loser;
			
			// Compete(a,b)
			if (indA.getFuncaoObjetivo() < indB.getFuncaoObjetivo()) {
				winner = indA;
				loser = indB;
			}
			else {
				winner = indB;
				loser = indA;
			}
			
			// Atualizar o vetor de probabilidades
			
			for (int i = 0; i < this.l; i ++) {
				if (winner.getCromossomos().get(i) != loser.getCromossomos().get(i)) {
					if ( winner.getCromossomos().get(i).equals(1)) {
						p.set(i, p.get(i) + 1.0/this.n);
						
					} else {
						p.set(i, p.get(i) - 1.0/this.n);
					}
				}
			}
			
//			if (winner.getFuncaoObjetivo() < best.getFuncaoObjetivo()) {
//				best.setFuncaoObjetivo(winner.getFuncaoObjetivo());
//			}
			
			
			resultado.setCromossomos(p);
			problema.calcularFuncaoObjetivo(resultado);
			
			
			if (resultado.getFuncaoObjetivo() < best.getFuncaoObjetivo()) {
				best = (IndividuoDouble) resultado.clone();
			}
			
			System.out.print("G = " + g + "\t");
			System.out.print(winner.getFuncaoObjetivo() + "\t");
			System.out.print(best.getFuncaoObjetivo() + "\t");
			System.out.println(resultado.getFuncaoObjetivo() + "\t");
			
		g++;
		processa = false;
		
		for (int i = 0 ; i < this.l; i++) {
			if (p.get(i) > 0.0 && p.get(i) < 1.0) {
				processa = true;
				break;
			}
		}
		if (g > this.gmax) {
			break;
		}
			
		} while (processa);
		
		//resultado.setCromossomos(p);
	//	problema.calcularFuncaoObjetivo(resultado);
		
			System.out.println(p);
		return best;
		

	}

	
	private IndividuoBinario gerarIndividuo(ArrayList<Double> p) {
		IndividuoBinario ind = new IndividuoBinario(this.l);
		Random rnd = new Random();
		
		for (int i = 0 ; i < this.l ; i++) {
			if (rnd.nextDouble() <= p.get(i)) {
				ind.getCromossomos().add(1);
				
			} else {
				ind.getCromossomos().add(0);
			}
		}
		return ind;
	}
	
}
