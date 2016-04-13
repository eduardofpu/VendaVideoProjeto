/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloBairro;
import Modelo.ModeloBaixaParcela;
import Modelo.ModeloExcluirVendas;
import Modelo.ModeloParcelamentoVenda;
import Modelo.ModeloVenda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleExcluirVendas {
    
    
    ConectaBanco conex = new ConectaBanco();
    ModeloExcluirVendas mod = new ModeloExcluirVendas();
   // ModeloVenda mod = new ModeloVenda();
    
    
    public void Excluir(ModeloExcluirVendas mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from venda where id_venda=?");           
            pst.setInt(1,mod.getCodV());
            pst.execute();
             JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        }
        
        conex.desconecta();
    }
    
     public void ExcluirItens(ModeloExcluirVendas mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from itens_venda_produto where id_venda=?");           
            pst.setInt(1,mod.getCodV());
            pst.execute();
            // JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
        }
        
        conex.desconecta();
    }
    public ModeloExcluirVendas Primeiro(){
        
        conex.conexao();
        conex.execultasql("select * from venda ");
       // conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
       // conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente inner join itens_venda_produto on venda.id_venda=itens_venda_produto.id_venda");
        try {
            conex.rs.first();
            mod.setCodV(conex.rs.getInt("id_venda"));
            mod.setData(conex.rs.getString("data_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
            mod.setCodCli(conex.rs.getInt("id_cliente"));
            mod.setTipoP(conex.rs.getString("tipo_pagamento"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            conex.desconecta();
        return mod;
    }
    
     public ModeloExcluirVendas Ultimo(){
        
        conex.conexao();
        conex.execultasql("select * from venda ");
       // conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
        //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente inner join itens_venda_produto on venda.id_venda=itens_venda_produto.id_venda");
        try {
            conex.rs.last();
            mod.setCodV(conex.rs.getInt("id_venda"));
            mod.setData(conex.rs.getString("data_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
            mod.setCodCli(conex.rs.getInt("id_cliente"));
            mod.setTipoP(conex.rs.getString("tipo_pagamento"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            conex.desconecta();
        return mod;
    }
    
     
      public ModeloExcluirVendas Proximo(){
        
       conex.execultasql("select * from venda");
      // conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
        //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente inner join itens_venda_produto on venda.id_venda=itens_venda_produto.id_venda");
        try {
            conex.rs.next();
            mod.setCodV(conex.rs.getInt("id_venda"));
            mod.setData(conex.rs.getString("data_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
            mod.setCodCli(conex.rs.getInt("id_cliente"));
            mod.setTipoP(conex.rs.getString("tipo_pagamento"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
          
        return mod;
    }
    
    
       public ModeloExcluirVendas Anterior(){
        
       conex.execultasql("select * from venda ");
        //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
        //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente inner join itens_venda_produto on venda.id_venda=itens_venda_produto.id_venda");
        try {
            conex.rs.previous();
            mod.setCodV(conex.rs.getInt("id_venda"));
            mod.setData(conex.rs.getString("data_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
            mod.setCodCli(conex.rs.getInt("id_cliente"));
            mod.setTipoP(conex.rs.getString("tipo_pagamento"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
          
        return mod;
    }
}
