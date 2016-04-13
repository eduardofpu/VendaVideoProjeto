/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

/**
 *
 * @author Eduardo
 */
public class Mensagem {

    public Mensagem(String mensagem) { // metodo construtor para passar a mensagem Obrigado e volte sempre .
        this.mensagem = mensagem;
    }
    
   
    
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
