/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloCidade;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleCidade {
    ConectaBanco connCidade = new ConectaBanco();
    public void inserirCidade(ModeloCidade mod){
        connCidade.conexao();
        try {
            PreparedStatement pst = connCidade.conn.prepareStatement("insert into cidade(nome_cidade,id_estado)values(?,?)");
            pst.setString(1, mod.getNome());
            pst.setInt(2, mod.getCod_estado());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados armazenados com sucesso!");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na inserção dos dados.\n Erro!"+ex);
        }
        connCidade.desconecta();
        
        
    }
    public void ExcluiCidade(ModeloCidade mod){
        connCidade.conexao();    
            try {
                PreparedStatement pst = connCidade.conn.prepareStatement("delete from cidade where id_cidade=?");
                pst.setInt(1, mod.getCod());
                pst.execute();           
                 JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
            }      
           connCidade.desconecta();
   }
    public void AlteraCidade(ModeloCidade mod){
        connCidade.conexao();   
        //JOptionPane.showMessageDialog(null,mod.getCod_estado());
        try {
            PreparedStatement pst = connCidade.conn.prepareStatement("update cidade set nome_cidade = ?,  id_estado=? where id_cidade=?");
            pst.setString(1, mod.getNome());
            pst.setInt(2, mod.getCod_estado());
            pst.setInt(3, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro na alteração dos dados.\n Erro!"+ex);
        }
    }
    
}
