/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleDataVenc {
    ConectaBanco conn = new ConectaBanco();
    public void salvarData(String data){
        conn.conexao();
        try {
            PreparedStatement pst =conn.conn.prepareStatement("insert into vencimento(datavenc)values(?)");
                    pst.setString(1, data);
                    pst.execute();
                     JOptionPane.showMessageDialog(null,"Data cadastrada com sucessso");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro da inserção da data!\n Erro:"+ ex);
        }
               
    }
    
}
