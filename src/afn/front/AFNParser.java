/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.front;

import afn.back.Adicao;
import afn.back.Concatenar;
import afn.util.ExpressaoAbstrata;
import afn.back.Iteracao;
import afn.back.Parenteses;
import afn.front.OperadoresEnum.Operadores;
import afn.back.Simbolo;
import afn.util.AFN;
import afn.back.Uniao;

/**
 *
 * @author Lucas
 */
public class AFNParser{
    
    /**
     *
     */
    private static final String OPERADORES = "+|*";
    
    /**
     *
     */
    public static int COUNT = 0;
    private AFN transicao;
    
    /**
     *
     */
    public AFNParser(){
        this.transicao = new AFN();
    }
    /**
     * Seta o AFN
     * @param transicao 
     */
    private void setTransicao(AFN transicao){
        this.transicao = transicao;
    }
    
    /**
     * Retorna um AFN
     * @return 
     */
    public AFN getTransicao(){
        return this.transicao;
    }
    
    /**
     * Mantem um contador sequencial para os estados [q0, q1, q2, qn]
     * @return um nome de estado qx;
     */
    public static String getCount(){
        return "q"+COUNT++;
    }

    /**
     *Recebe uma cadeia de caracteres e delega as operações necessárias
     * para as classe responsaveis.
     * @param expressao uma expressão regular valida
     * @return AFN do 'expressao'
     * Expressao Terminal -> cas o tamanho da string = 1 caractere (1 simbolo)
     * Algoritmo:
     * {1}Caso expressao terminal (expressao.length = 1), cria o AFN do caractere ex: a => q0, a, q1
     * {2}Caso o segundo caractere da string seja um operador [* / +], verifica-se qual é o operador,
     * remove ele da string e manda a string para a expressão abstrata referente ao operador.
     * {3}Caso duas letras concatena.
     */
    public AFN analisar(String expressao){
       
        if(expressao.equals("")){
            return null;
        }
        else if(expressaoTerminal(expressao)){
            setTransicao(new Simbolo(expressao).resolver(expressao, 0));
        }else{
            if(checaOperador(expressao.substring(1, 2))){
            String operador = expressao.substring(1,2);
            ExpressaoAbstrata Expressao = criarExpressao(getOperador(operador));
            //Remove o operador [*|+]
            expressao = removerChar(expressao, 1);
            setTransicao(Expressao.resolver(expressao, 1));
            }else if(expressao.startsWith("(")){
                expressao = removerChar(expressao, 0);
                setTransicao(new Parenteses().resolver(expressao,1));
            }else{
                setTransicao(new Concatenar().resolver(expressao, 1));
            }
        }
        
        return getTransicao();
    }     
    
    //Aplica um operador a uma transição
    public AFN analisar(AFN transicao, String operador){
        ExpressaoAbstrata Expressao = criarExpressao(getOperador(operador));
        setTransicao(Expressao.resolver(transicao));
        return getTransicao();
    }
            
    private boolean checaOperador(String s){
            return OPERADORES.contains(s);
    }
    
    /**
     * Percorre a lista dos operadores para obter o operador solicitado
     * @param operador
     * @return Retorna um operador 
     */
    private Operadores getOperador(String operador){
        Operadores[] values = Operadores.values();
        Operadores name = null;
        for(Operadores e: values){
            if(e.getOperador().equals(operador)){
                name = e;
                break;
            }
        }
        return name;
    }

    /**
     * Verifica se a string é uma expressão terminal
     * @param expressao
     * @return true se expressão terminal
     */
    private boolean expressaoTerminal(String expressao) {
        return expressao.length() == 1;
    }

    /**
     * Fabrica as expressoes de acordo com o nome do operador
     * @param nome
     * @return uma expressao ExpressaoAbstrata
     */
    private ExpressaoAbstrata criarExpressao(Operadores nome) {
       ExpressaoAbstrata expressao = null;
       switch(nome){
           case ADICAO:
               expressao =  new Adicao();
               break;
           case ITERACAO:
               expressao = new Iteracao();
               break;
           case UNIAO:
               expressao = new Uniao();
               break;
       }
       
       return expressao;
    }
    
    /**
     * Remove um char da posicão desejada, insere o restante no final da string
     * @param expressao String
     * @param pos posição para remover
     * @return String sem o caracter da posicao pos
     */
    public String removerChar(String expressao, int pos) {
        String nexpressao = expressao.substring(0, pos);
        int i = pos+1;
        //while(i < expressao.length()){
            nexpressao += expressao.substring(i);
            //i++;
        //}
        return nexpressao;
    }

    public String getOperadores() {
       return OPERADORES;
    }

    public void init() {
        COUNT = 0;
    }
}
