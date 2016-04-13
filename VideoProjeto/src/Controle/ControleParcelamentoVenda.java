/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloParcelamentoVenda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleParcelamentoVenda {
    
    
    ConectaBanco conex = new ConectaBanco();
    
    
    public void SalvaParcela(ModeloParcelamentoVenda mod){
        
        conex.conexao();
        try {
            //PreparedStatement pst = conex.conn.prepareStatement("insert into parcela_venda(cod_venda,valor_venda,valor_total,valor_parce,numero_parcela,datavenc) values(?,?,?,?,?,?)");
            PreparedStatement pst = conex.conn.prepareStatement("insert into parcela_venda(cod_venda,valor_venda,valor_total,valor_parce,numero_parcela,datavenc) values(?,?,?,?,?,?)");
             pst.setInt(1,mod.getCodVenda());
             pst.setFloat(2,mod.getValor_venda());
             pst.setFloat(3,mod.getValor_total());
             pst.setFloat(4,mod.getValorParcela()); 
             pst.setInt(5,mod.getNumeroParc());                                                   
             pst.setString(6,mod.getDataVenc());
            // pst.setString(7,mod.getNome());
             pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar as parcelas\nErro:"+ex);
                  
        }
        conex.desconecta();
    }
    
}
