/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.front;

/**
 *
 * @author Lucas
 */
public class OperadoresEnum {
    public enum Operadores{
        ADICAO ("+"),
        ITERACAO ("*"),
        UNIAO ("|");
        
        private final String operador;

        private Operadores(String operador) {
            this.operador = operador;   
        }
        
        public String getOperador(){
            return this.operador;
        }
        
    }
}
