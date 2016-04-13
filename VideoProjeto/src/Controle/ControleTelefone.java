/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloTelefone;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleTelefone {  
    ConectaBanco connex = new ConectaBanco();
    ModeloTelefone mod = new ModeloTelefone();
    
   
   
public void Inserir(ModeloTelefone mod){
   connex.conexao();
  
        try {
             
             PreparedStatement pst = connex.conn.prepareStatement("insert into telefone (numero_tel)values(?)");
             pst.setString(1,mod.getTel());            
             pst.execute();
             JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao inserir!"+ex);
        }
        connex.desconecta();            
  }
public ModeloTelefone Primeiro(){    
    connex.conexao();    
    connex.execultasql("select * from telefone");
        try {
             connex.rs.first();                                   
             mod.setCod(connex.rs.getInt("id_telefone"));
             mod.setTel(connex.rs.getString("numero_tel"));   
             
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        connex.desconecta();       
        return mod;  
        
       }
public ModeloTelefone Uti(){  
    connex.conexao();   
    connex.execultasql("select * from telefone");
    
        try {
             connex.rs.last();   
             
             mod.setCod(connex.rs.getInt("id_telefone"));
             mod.setTel(connex.rs.getString("numero_tel"));  
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        connex.desconecta();           
        return mod;           
   }

public ModeloTelefone Ant(){
    connex.conexao();   
    connex.execultasql("select * from telefone where id-telefone=numero_tel");
  
        try {
             
             
             connex.rs.previous();
             
             mod.setCod(connex.rs.getInt("id_telefone"));
             mod.setTel(connex.rs.getString("numero_tel"));   
             
        } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        connex.desconecta();       
        return mod;  
        
    
  }
public ModeloTelefone Prox(){  
    connex.conexao();   
    connex.execultasql("select * from telefone where id-telefone=numero_tel");
    
        try {
             
             connex.rs.next();
             mod.setCod(connex.rs.getInt("id_telefone"));
             mod.setTel(connex.rs.getString("numero_tel"));   
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        connex.desconecta();       
        return mod;  
        

    }

public void Alterar (ModeloTelefone mod){   
    connex.conexao();
    PreparedStatement pst;
    
        try {                       
         
             pst = connex.conn.prepareStatement("update telefone set numero_tel = ?   where id_telefone=?");
             pst.setString(1,mod.getTel());
             pst.setInt(2, mod.getCod());            
             pst.execute();
             
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro na alteração dos dados.\n Erro!"+ex);
}
       connex.desconecta();
       
    }
 public void Excluir(ModeloTelefone mod){
        connex.conexao();
        PreparedStatement pst;
            try {
                 pst = connex.conn.prepareStatement("delete from telefone where id_telefone=?");
                 pst.setInt(1,mod.getCod());
                 pst.execute();           
                 JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
            }      
           connex.desconecta();
          
  }
}

    

