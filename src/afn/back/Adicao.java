/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.back;

import afn.util.ExpressaoAbstrata;

/**
 *
 * @author Lucas
 * 
 * ADIÇÃO
 * 
 */
public class Adicao extends ExpressaoAbstrata{
    
    @Override
    protected void criarAFN() {
        String[][] novaTransicao = new String[][]{
            {transicao.inico, "e", transicaoA.inico},
            {transicaoA.fim, "e", transicao.fim},
            {transicaoA.fim, "e", transicaoA.inico}
        };
        
        this.transicao.transicoes = novaTransicao;
        transicao.combinaEstados(transicaoA);
        if(transicaoB != null){
            this.transicao = new Concatenar().resolver(transicao, transicaoB);
        }
    }
}
