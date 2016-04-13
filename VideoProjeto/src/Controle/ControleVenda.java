/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloVenda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleVenda {
    
    int codProd, codCliente;
    ConectaBanco conexao = new ConectaBanco();
    
    
    
    
    public void adicionaItem(ModeloVenda mod){
        achaCodProduto(mod.getNomeProduto());
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("insert into itens_venda_produto(id_venda,id_produto,quantidade_produto,nome_produto,valor_venda)values(?,?,?,?,?)");
            pst.setInt(1,mod.getIdVenda());
            pst.setInt(2,codProd);
            pst.setInt(3,mod.getQtdItem());
            
            
            pst.setString(4,mod.getNomeProduto());
            pst.setFloat(5, mod.getValorVenda());
            pst.execute();
            
            
            
            //Baixa de estoque
             int quant=0,resul=0;
             conexao.execultasql("select * produto where nome_produto='"+mod.getNomeProduto()+"'");
             conexao.rs.first();
             quant =conexao.rs.getInt("quantidade");
             resul = quant-mod.getQtdItem();
             pst=conexao.conn.prepareStatement("update produto set quantidade=? where nome_produto=?");
             pst.setInt(1, resul);
             pst.setString(2,mod.getNomeProduto());
             pst.execute();
              //JOptionPane.showMessageDialog(null,"Produto adicionado!");
            conexao.desconecta();
        } catch (SQLException ex) {
            conexao.desconecta();
           // JOptionPane.showMessageDialog(null,"Erro ao realizar a venda:" +ex);
        }
        
        
    }
public void achaCodProduto(String nome){
    conexao.conexao();
    conexao.execultasql("select * from produto where nome_produto='"+nome+"'");
        try {
            conexao.rs.first();
            codProd=conexao.rs.getInt("id_produto");
            conexao.desconecta();
        } catch (SQLException ex) {
             conexao.desconecta();
            JOptionPane.showMessageDialog(null,"Erro:" +ex);
        }
    
  }  
public void FechaVenda(ModeloVenda mod){
    AchaCliente(mod.getNomeCliente());
    conexao.conexao();
        try {
            PreparedStatement pst =conexao.conn.prepareStatement("update venda set data_venda=?,valor_venda=?,id_cliente=?,tipo_pagamento=? where id_venda=?");
            pst.setString(1,mod.getData());
            pst.setFloat(2, mod.getValorVenda());
            pst.setInt(3, codCliente);
            pst.setString(4,mod.getPagamento());// para o tipo de pagamento
            pst.setInt(5, mod.getIdVenda());
            
            pst.execute();
            //JOptionPane.showMessageDialog(null,"Venda finalizada:");
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao fechar a venda:" +ex);
        }
    conexao.desconecta();
    
    
   }
public void AchaCliente(String nome){
   conexao.conexao();
        try {
           conexao.execultasql("select * from clientes   where nome_cliente='"+nome+"'");
           conexao.rs.first();
           codCliente=conexao.rs.getInt("id_cliente");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao achar o cliente:" +ex);
        }
    conexao.desconecta();
  }
public void CancelaVenda(){
     conexao.conexao();
      PreparedStatement pst;
      
     conexao.execultasql("select * from venda inner join itens_venda_produto on venda.id_venda=itens_venda_produto.id_venda inner join produto on itens_venda_produto.id_produto=produto.id_produto where valor_venda=0");
        try {
            conexao.rs.first();
            do{
                int qtdProd=conexao.rs.getInt("quantidade");
                int qtdVend=conexao.rs.getInt("quantidade_produto");
                int soma= qtdProd+qtdVend;
                 pst=conexao.conn.prepareStatement("update produto set quantidade=?where id_produto=?");
                 pst.setInt(1, soma);
                 pst.setInt(2,conexao.rs.getInt("id_produto"));
                 pst.execute();
                 pst=conexao.conn.prepareStatement("delete from itens_venda_produto where id_venda=?");
                 pst.setInt(1, conexao.rs.getInt("id_venda"));
                 pst.execute();
            }while(conexao.rs.next());                
       
                 pst= conexao.conn.prepareStatement("delete from venda where valor_venda=?");
                 pst.setInt(1,0);
                 pst.execute();
          
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null,"Erro ao cancelar:" +ex);
        }
                conexao.desconecta();
    
  }
}
