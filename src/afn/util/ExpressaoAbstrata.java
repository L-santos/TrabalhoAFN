/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.util;

import afn.front.AFNParser;


/**
 *
 * @author Lucas
 * Difine as operacoes de conversão:
 * 
 * 
 */
public abstract class ExpressaoAbstrata {
    
    public AFN transicaoA;
    public AFN transicaoB;
    public AFN transicao;
    public AFNParser parser;
    
    public ExpressaoAbstrata(){
        transicao = new AFN();
        parser = new AFNParser();
   }
    
   public AFN getTransicao(){
       return this.transicao;
   }
    /**
     * Faz o pré processameento necessário para a criação do AFN.
     * Cada operação determina o seu pré processamento.
     * @param expressao String a ser processada.
     * @param i index do inicio da string. Divide a expressão regular em duas subexpressões
     * @return retorna as transicoes criadas
     */
    public AFN resolver(String expressao, int i){
        String substringA=expressao.substring(0, i);
        String substringB=expressao.substring(i, expressao.length());
        transicao.inico = AFNParser.getCount();
        transicaoA = parser.analisar(substringA);
        transicao.fim = AFNParser.getCount();
        transicaoB = parser.analisar(substringB);
        criarAFN();
        return transicao;
    }
    
    public AFN resolver(AFN transicao){
        this.transicao.inico = AFNParser.getCount();
        this.transicao.fim = AFNParser.getCount();
        this.transicaoA = transicao;
        criarAFN();
        return this.transicao;
    }

    /**
     *Cria o AFN utilizando as regras do algorittmo de Thompsom, salva o resultado na propriedade transição.
     */
    protected abstract void criarAFN();

}
