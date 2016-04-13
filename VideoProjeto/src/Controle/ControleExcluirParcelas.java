/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloExcluirVendas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleExcluirParcelas {
    
    ConectaBanco conex = new ConectaBanco();
    ModeloExcluirVendas mod = new ModeloExcluirVendas();
    
    
     public void Excluir1(ModeloExcluirVendas mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from parcela_venda where id_parc_venda=?"); 
            
       
           
           
            pst.setInt(1,mod.getCodParcela());
            pst.execute();
             JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
            JOptionPane.showMessageDialog(null, "Erro ao excluir os Dados!");
        }
        
        conex.desconecta();
    }
    
    /* public void ExcluirItens(ModeloExcluirVendas mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from itens_venda_produto where id_venda=?");           
            pst.setInt(1,mod.getCodParcela());
            pst.execute();
            // JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
        }
        
        conex.desconecta();
    }*/
    public ModeloExcluirVendas Primeiro(){
        
        conex.conexao();
        //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
       // conex.execultasql("select * from parcela_venda inner join venda on parcela_venda.id_parc_venda=venda.id_venda");
          conex.execultasql("select * from parcela_venda ");
        try {
            conex.rs.first();
            mod.setCodParcela(conex.rs.getInt("id_parc_venda"));
            mod.setCodV(conex.rs.getInt("cod_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
            mod.setValorT(conex.rs.getFloat("valor_total"));
            mod.setNumP(conex.rs.getInt("numero_parcela"));
            mod.setValorP(conex.rs.getFloat("valor_parce"));
            mod.setData(conex.rs.getString("datavenc"));
            mod.setEstado(conex.rs.getString("estado"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            conex.desconecta();
        return mod;
    }
    
     public ModeloExcluirVendas Ultimo(){
        
        conex.conexao();
       // conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
        // conex.execultasql("select * from parcela_venda inner join venda on parcela_venda.id_parc_venda=venda.id_venda");
           conex.execultasql("select * from parcela_venda ");
        try {
            conex.rs.last();
           mod.setCodParcela(conex.rs.getInt("id_parc_venda"));
            mod.setCodV(conex.rs.getInt("cod_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
            mod.setValorT(conex.rs.getFloat("valor_total"));
            mod.setNumP(conex.rs.getInt("numero_parcela"));
            mod.setValorP(conex.rs.getFloat("valor_parce"));
            mod.setData(conex.rs.getString("datavenc"));
            mod.setEstado(conex.rs.getString("estado"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
            conex.desconecta();
        return mod;
    }
    
     
      public ModeloExcluirVendas Proximo(){
        
       
       //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
         //conex.execultasql("select * from parcela_venda inner join venda on parcela_venda.id_parc_venda=venda.id_venda");
           conex.execultasql("select * from parcela_venda ");
        try {
            conex.rs.next();
            mod.setCodParcela(conex.rs.getInt("id_parc_venda"));
            mod.setCodV(conex.rs.getInt("cod_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
           mod.setValorT(conex.rs.getFloat("valor_total"));
            mod.setNumP(conex.rs.getInt("numero_parcela"));
            mod.setValorP(conex.rs.getFloat("valor_parce"));
            mod.setData(conex.rs.getString("datavenc"));
            mod.setEstado(conex.rs.getString("estado"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
          
        return mod;
    }
    
    
       public ModeloExcluirVendas Anterior(){
        
       
        //conex.execultasql("select * from venda inner join clientes on venda.id_cliente=clientes.id_cliente");
        // conex.execultasql("select * from parcela_venda inner join venda on parcela_venda.id_parc_venda=venda.id_venda");
          conex.execultasql("select * from parcela_venda ");
        try {
            conex.rs.previous();
           mod.setCodParcela(conex.rs.getInt("id_parc_venda"));
            mod.setCodV(conex.rs.getInt("cod_venda"));
            mod.setValorV(conex.rs.getFloat("valor_venda"));
           mod.setValorT(conex.rs.getFloat("valor_total"));
            mod.setNumP(conex.rs.getInt("numero_parcela"));
            mod.setValorP(conex.rs.getFloat("valor_parce"));
            mod.setData(conex.rs.getString("datavenc"));
            mod.setEstado(conex.rs.getString("estado"));
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao buscar o primeiro !"+ ex);
        }
        
          
        return mod;
    }


    
}
