    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloFornecedor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleFornecedor {
   
    ModeloFornecedor mod = new ModeloFornecedor();
    ConectaBanco conn = new ConectaBanco();
    ConectaBanco connAux = new ConectaBanco();   
    int codBairro;
   
   
    public void Salvar(ModeloFornecedor mod){
        AchaBairro(mod.getBairro());
        conn.conexao();
       
        try {
            
           
            PreparedStatement pst = conn.conn.prepareStatement("insert into fornecedores (nome_fornecedor,endereco,id_bairro,cnpj_fornecedor )values(?,?,?,?)");
            pst.setString(1,mod.getNome());
            pst.setString(2,mod.getEndereco());
            pst.setInt(3, codBairro);
            pst.setString(4,mod.getCNPJ());
            pst.execute();                                               
             JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro na inserção do fornecedor!\nErro:"+ ex);
        }
        conn.desconecta();
    }
    public void Excluir(ModeloFornecedor mod){
        conn.conexao();
        try {
            PreparedStatement pst = conn.conn.prepareStatement("delete from fornecedores where id_fornecedor=?");
            pst.setInt(1,mod.getId());
            pst.execute();
             JOptionPane.showMessageDialog(null,"Dados excluidos com sucesso!");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro na exclusão do fornecedor!\nErro:"+ ex);
        }
        conn.desconecta();
    }
    public void Alterar(ModeloFornecedor mod){
        AchaBairro(mod.getBairro());
        conn.conexao();
        try {
            PreparedStatement pst = conn.conn.prepareStatement("update fornecedores set nome_fornecedor=?,endereco=?,id_bairro=?,cnpj_fornecedor=? where id_fornecedor=?");
            pst.setString(1,mod.getNome());
            pst.setString(2,mod.getEndereco());
            pst.setInt(3, codBairro);
            pst.setString(4,mod.getCNPJ());
            pst.setInt(5, mod.getId());
            pst.execute();                       
            
            JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar o fornecedor !\nErro:"+ ex);
        }
    }
    public void AchaBairro(String bairro){
        conn.conexao();
        
        try {
            conn.execultasql("select * from bairro where nome_bairro='"+bairro+"'");
            conn.rs.first();
            codBairro = conn.rs.getInt("id_bairro");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar o codigo do bairro!\nErro:"+ ex);
        }
        conn.desconecta();
    }
    public ModeloFornecedor Primeiro(){
        conn.conexao();
       
        
        try {
               conn.execultasql("select * from fornecedores inner join bairro on fornecedores.id_bairro=bairro.id_bairro\n"+
                 "inner join cidade on bairro.id_cidade = cidade.id_cidade\n"+
                 "inner join estados on cidade.id_estado = estados.id_estado");
           
            conn.rs.first();
            
            
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));             
            mod.setBairro(conn.rs.getString("nome_bairro"));              
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));             
            mod.setNomeCidade(conn.rs.getString("nome_cidade"));              
            mod.setSigla_estado(conn.rs.getString("sigla_estado"));
            
           
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        
        return mod;
        
        
    }
    public ModeloFornecedor Ultimo(){
  conn.conexao();
  
       
        try {
              conn.execultasql("select * from fornecedores inner join bairro on fornecedores.id_bairro=bairro.id_bairro\n"+
                 "inner join cidade on bairro.id_cidade = cidade.id_cidade\n"+
                 "inner join estados on cidade.id_estado = estados.id_estado");
             
            conn.rs.last();
           
            
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));             
            mod.setBairro(conn.rs.getString("nome_bairro"));            
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));            
            mod.setNomeCidade(conn.rs.getString("nome_cidade"));              
            mod.setSigla_estado(conn.rs.getString("sigla_estado"));
            
            
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        
        return mod;
    }
     public ModeloFornecedor Anterior(){
       // conn.conexao();
         
       
        try {
             
           conn.rs.previous();                            
             
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));           
            mod.setEndereco(conn.rs.getString("endereco"));                           
            mod.setBairro(conn.rs.getString("nome_bairro"));                  
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));                   
            mod.setNomeCidade(conn.rs.getString("nome_cidade"));                     
            mod.setSigla_estado(conn.rs.getString("sigla_estado"));                      
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        
        return mod; 
     }
     public ModeloFornecedor Proximo(){
       // conn.conexao();
         
        try {
             
            conn.rs.next();                                 
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));            
            mod.setBairro(conn.rs.getString("nome_bairro"));             
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));             
            mod.setNomeCidade(conn.rs.getString("nome_cidade"));           
            mod.setSigla_estado(conn.rs.getString("sigla_estado"));
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        
        return mod; 
     }
}
