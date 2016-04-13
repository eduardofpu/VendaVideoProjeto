/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloVenda;
import java.sql.SQLException;

/**
 *
 * @author Delfino
 */
public class ControleBotoesItensVendaProduto {
 
    ConectaBanco conex = new ConectaBanco();
    ModeloVenda mod = new ModeloVenda();
    
     public ModeloVenda Primeiro(){
        
        conex.conexao();
       
               
        conex.execultasql("select * from itens_venda_produto ");
        try {
            
            conex.rs.first();
            
            mod.setIdVenda(conex.rs.getInt("id_venda"));
            mod.setIdProduto(conex.rs.getInt("id_produto"));
            mod.setQtdItem(conex.rs.getInt("quantidade_produto"));
            mod.setNomeProduto(conex.rs.getString("nome_produto"));
            mod.setValorVenda(conex.rs.getInt("valor_venda"));
           
            
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            conex.desconecta();
        return mod;
    }
    
    
    public ModeloVenda Ultimo(){
        
        conex.conexao();
       
               
        conex.execultasql("select * from itens_venda_produto ");
        try {
            
            conex.rs.last();
            
            mod.setIdVenda(conex.rs.getInt("id_venda"));
            mod.setIdProduto(conex.rs.getInt("id_produto"));
            mod.setQtdItem(conex.rs.getInt("quantidade_produto"));
            mod.setNomeProduto(conex.rs.getString("nome_produto"));
            mod.setValorVenda(conex.rs.getInt("valor_venda"));
           
            
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            conex.desconecta();
        return mod;
    } 
    
  
     public ModeloVenda Proximo(){
        
        
       
               
        conex.execultasql("select * from itens_venda_produto ");
        try {
            
            conex.rs.next();
            
            mod.setIdVenda(conex.rs.getInt("id_venda"));
            mod.setIdProduto(conex.rs.getInt("id_produto"));
            mod.setQtdItem(conex.rs.getInt("quantidade_produto"));
            mod.setNomeProduto(conex.rs.getString("nome_produto"));
            mod.setValorVenda(conex.rs.getInt("valor_venda"));
           
            
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            
        return mod;
    } 
     
     
    public ModeloVenda Anterior(){
        
        
       
               
        conex.execultasql("select * from itens_venda_produto ");
        try {
            
            conex.rs.previous();
            
            mod.setIdVenda(conex.rs.getInt("id_venda"));
            mod.setIdProduto(conex.rs.getInt("id_produto"));
            mod.setQtdItem(conex.rs.getInt("quantidade_produto"));
            mod.setNomeProduto(conex.rs.getString("nome_produto"));
            mod.setValorVenda(conex.rs.getInt("valor_venda"));
           
            
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
           
        return mod;
    }  
     
   
     
}
