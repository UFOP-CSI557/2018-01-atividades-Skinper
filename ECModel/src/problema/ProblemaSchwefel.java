package problema;

import solucao.Individuo;

public class ProblemaSchwefel implements Problema {
	  private Integer nVariaveis;
	  
	public ProblemaSchwefel(Integer nVariaveis) {
		this.nVariaveis = nVariaveis;
	}

	@Override
	public void calcularFuncaoObjetivo(Individuo individuo) {
    Double value = 0.0;
 
       
        for(int i = 0; i < individuo.getCromossomos().size(); i++) {
            Double xi = (Double) individuo.getCromossomos().get(i);
            Double minus = xi * Math.sin( Math.sqrt( Math.abs(xi)));
          value +=minus;
                               
        }
        value = 418.9829 * this.nVariaveis - value;
        
        individuo.setFuncaoObjetivo(value);

	}

	@Override
	public int getDimensao() {
		// TODO Auto-generated method stub
		   return this.nVariaveis;
	}

}
