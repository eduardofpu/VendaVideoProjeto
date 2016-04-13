/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloProduto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleProduto {
    ModeloProduto mod = new ModeloProduto();
    ConectaBanco conexao = new ConectaBanco();
    ConectaBanco conexaoFornecedor= new ConectaBanco();
    String  nomeFornecedor; 
    int codFornecedor;      
    public void inserirProduto(ModeloProduto mod){
        buscaCodigo(mod.getFornecedorProduto());
        conexao.conexao();       
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("insert into produto (nome_produto,preco_venda,preco_compra,quantidade,id_fornecedor)values(?,?,?,?,?)");
            pst.setString(1,mod.getNomeProduto());
            pst.setFloat(2,mod.getPrecoVenda());
            pst.setFloat(3,mod.getPrecoCompra());
            pst.setInt(4,mod.getQtdProduto());
            pst.setInt(5, codFornecedor);
            pst.execute();
             JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao inserir o produto!\nERRO:"+ ex);
        }
       conexao.desconecta();
    }
     public void excluirProduto(ModeloProduto mod){
         conexao.conexao();
        try {
            PreparedStatement pst= conexao.conn.prepareStatement("delete from produto where id_produto=?");
            pst.setInt(1,mod.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Produto excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir o produto!\nERRO:"+ ex);
        }
         conexao.desconecta();
     }
     public void Alterar(ModeloProduto mod){
         buscaCodigo(mod.getFornecedorProduto());
         conexao.conexao();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("update produto set nome_produto=?,preco_venda=?,preco_compra=?,quantidade=?,id_fornecedor=? where id_produto=? ");
            pst.setString(1,mod.getNomeProduto());
            pst.setFloat(2,mod.getPrecoVenda());
            pst.setFloat(3,mod.getPrecoCompra());
            pst.setInt(4,mod.getQtdProduto());
            pst.setInt(5, codFornecedor);
            pst.setInt(6, mod.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Produto alterado com sucesso!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Erro ao alterar o produto!\nERRO:"+ ex);
        }
        conexao.desconecta();
     } 
     public ModeloProduto buscaProduto(ModeloProduto modelo){
         conexao.conexao();
         conexao.execultasql("select * from produto where nome_produto like '%"+modelo.getPesquisa()+"%' ");
        try {
            conexao.rs.first();
            buscaNomeFornecedor(conexao.rs.getInt("id_fornecedor"));
            mod.setIdProduto(conexao.rs.getInt("id_produto"));
            mod.setNomeProduto(conexao.rs.getString("nome_produto"));
            mod.setPrecoCompra(conexao.rs.getFloat("preco_compra"));
            mod.setPrecoVenda(conexao.rs.getFloat("preco_venda"));
            mod.setQtdProduto(conexao.rs.getInt("quantidade"));
            mod.setFornecedorProduto(nomeFornecedor);
        } catch (SQLException ex) {
            Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao.desconecta();
        
        return mod;
     }
     public void buscaNomeFornecedor(int cod){
         conexaoFornecedor.conexao();
         conexaoFornecedor.execultasql("select * from fornecedores where id_fornecedor='"+cod+"'");
        try {
            conexaoFornecedor.rs.first();
            nomeFornecedor = conexaoFornecedor.rs.getString("nome_fornecedor");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Erro ao buscar o codigo !\nERRO:"+ ex);
        }
        conexaoFornecedor.desconecta();
     } 
     public void buscaCodigo(String nome){
         conexao.conexao();
         conexao.execultasql("select * from fornecedores where nome_fornecedor='"+nome+"'");
        try {
            conexao.rs.first();
            codFornecedor=conexao.rs.getInt("id_fornecedor");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar o codigo !\nERRO:"+ ex);
        }
         conexao.desconecta();
     } 
}
    
