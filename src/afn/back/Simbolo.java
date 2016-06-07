/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afn.back;

import afn.util.ExpressaoAbstrata;
import afn.util.AFN;
import afn.front.AFNParser;

/**
 *
 * @author Lucas
 */
public class Simbolo extends ExpressaoAbstrata{
    
    String token = "";
    public Simbolo(String token){
        this.token = token;
    }
    
    @Override
    public AFN resolver(String expressao, int i) {
        criarAFN();
        return getTransicao();
    }

    @Override
    public AFN getTransicao() {
        return this.transicao;
    }

    @Override
    protected void criarAFN() {
        transicao = new AFN();
        transicao.inico = AFNParser.getCount();
        transicao.fim = AFNParser.getCount();
        String[][] novaTransicao = new String[][]{{transicao.inico,""+token,transicao.fim}};
        transicao.transicoes = novaTransicao;
        //return R;
    }

    
}
