/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

 
 

/**
 *
 * @author fernando
 */
public class Problema {
		  public void calcularFuncaoObjetivo(Individuo individuo) {
		    int numberOfVariables = individuo.getVariaveis().size()  ;
 

		    double result = 0.0;
		    double a = 10.0;
		    double w = 2 * Math.PI;

		    for (int i = 0; i < numberOfVariables; i++) {
		      result += (individuo.getVariaveis().get(i) * individuo.getVariaveis().get(i)) - a * Math.cos(w * individuo.getVariaveis().get(i));
		    
		    }
		 
		    result  = result +  (a * numberOfVariables);
		  
		   
		    individuo.setFuncaoObjetivo(result);
		  }  
	
	
	
//    public void calcularFuncaoObjetivo(Individuo individuo) {
//        
//        Double soma = 0.0;
//                
//        for( Double var : individuo.getVariaveis() ) {
//            soma += Math.pow(var, 2);
//        }
//        
//        individuo.setFuncaoObjetivo(soma);
//        
//    }
    
}
