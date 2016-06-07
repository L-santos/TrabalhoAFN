/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.util;

import java.util.Arrays;

/**
 *
 * @author Lucas
 * 
 * Uma Transição T consite em uma sequencia de transicoes qx, s, qy (AFN),
 * inicio e fim
 */
public class AFN{
    
    public String[][] transicoes;
    public String inico;
    public String fim;
    
    /**
     *Combina dois vetores de transicoes 
     * @param transicaoA 
     */
    public void combinaEstados(AFN transicaoA) {
        String[][] alvo = this.transicoes;
        String[][] matrix = transicaoA.transicoes;
        int i = alvo.length + matrix.length;
        int j = alvo[0].length + matrix[0].length;
        String[][] novo = new String[i][];
        int k = 0;
        for (String[] matrix1 : matrix) {
            novo[k] = Arrays.copyOfRange(matrix1, 0, matrix1.length);
            k++;
        }
        
        for (String[] alvo1 : alvo) {
            novo[k] = Arrays.copyOfRange(alvo1, 0, alvo1.length);
            k++;
        }
        
        this.transicoes = novo;
    }
    
    /**
     * Retorna uma representação do AFN
     * @return 
     */
    public String toFormatedString(){
        String str = "Estados\n";
        for(String[] sg: this.transicoes){
            str += "[";
            for(String s: sg){
                str += s+" ";
            }
            str += "]\n";
        }
        return str;
    }
    
}
