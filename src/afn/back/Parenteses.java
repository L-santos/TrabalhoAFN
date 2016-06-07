/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.back;

import afn.util.ExpressaoAbstrata;
import afn.util.AFN;

/**
 *
 * @author Lucas
 */
public class Parenteses extends ExpressaoAbstrata{

    @Override
    public AFN resolver(String expressao, int i){
        int indice_fim = expressao.indexOf(")");
        //Remove o parentese remanescente
        expressao = parser.removerChar(expressao, indice_fim);
        //Pega o operador
        String operador = getOperador(expressao, indice_fim);
        //Pega a expressao após o operador
        String prox_expressao = getPosParenteses(expressao, indice_fim);
        
        if(parser.getOperadores().contains(operador)){
            if(!operador.equals("|")){
                //Guarda a expressao entre parentes para ser resolvida
                String temp = expressao.substring(0, indice_fim);
                //expressao entre parenteses + operador
                transicao = parser.analisar(parser.analisar(temp), operador);
                //resolve o conteudo pós parenteses
                //if(prox_expressao.equals(""))
                transicaoB = parser.analisar(prox_expressao);
                if(transicaoB != null){
                    transicao = new  Concatenar().resolver(transicao, transicaoB);
                }
            }else{
                String temp = expressao.substring(0, indice_fim);
                transicaoA = parser.analisar(temp);
                transicaoB = parser.analisar(prox_expressao);
                transicao = new Uniao().resolver(transicaoA, transicaoB);
            }
        } else {
            transicao = parser.analisar(expressao);
        }
        //criarAFN();
        return transicao;
    }
    
    @Override
    protected void criarAFN() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String getOperador(String expressao, int index){
        String operador;
        try {
            //Pega o operador 
             operador = expressao.substring(index, index+1);
        } catch (StringIndexOutOfBoundsException e) {
            operador = " ";
        }
        
        return operador;
    }

    private String getPosParenteses(String expressao, int indice_fim) {
        String nova_expressao;
        try {
            nova_expressao = expressao.substring(indice_fim+1);
        } catch (StringIndexOutOfBoundsException e) {
            nova_expressao = " ";
        }
        return nova_expressao;
    }
}
