/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloCliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/**
 *
 * @author Eduardo
 */
public class ControleCliente {
    
    ConectaBanco conex = new ConectaBanco();    
    ModeloCliente modCli = new ModeloCliente();
    int codBairro,codCidade,codTel;
    String Bairro,Cidade,Telefone;
    
    public void Inserir(ModeloCliente mod){
        conex.conexao();
        buscaCod(mod.getIdBairro(),mod.getIdCidade(),mod.getTelefone());
        try {
             //OptionPane.showMessageDialog(null,codBairro+","+codCidade+","+codTel);
             PreparedStatement pst = conex.conn.prepareStatement ("insert into clientes(nome_cliente,endereco_cliente,rg_cliente,cpf_cliente,"
                     + "id_bairro)values(?,?,?,?,?)");
             pst.setString(1,mod.getNome());
             pst.setString(2,mod.getEndereco());
             pst.setString(3,mod.getRg());
             pst.setString(4,mod.getCpf());
             buscaCod(mod.getIdBairro(),mod.getIdCidade(),mod.getTelefone());
             pst.setInt(5, codBairro);
             //pst.setInt(6, codCidade); 
             //Atualiza a tabela itens_ cli_tel
             pst.execute();
             conex.execultasql("select * from telefone where numero_tel='"+mod.getTelefone()+"'");
             conex.rs.first();
             codTel=conex.rs.getInt("id_telefone");
             conex.execultasql("select * from clientes where nome_cliente='"+mod.getNome()+"'");
             conex.rs.first();
             int codCli = conex.rs.getInt("id_cliente");
             pst = conex.conn.prepareStatement("insert into itens_cli_tel (id_cliente,id_tel)values(?,?)");
             buscaCod(mod.getIdBairro(),mod.getIdCidade(),"");
            // JOptionPane.showMessageDialog(null,mod.getTelefone());
             pst.setInt(1, codCli);
             pst.setInt(2, codTel);
             pst.execute();                                    
             JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro na inserção dos dados!\n Erro:"+ex);   
        }
          conex.desconecta();        
                                        
    }
   public void Excluir(ModeloCliente mod){
       conex.conexao();
        
        try {
            //Excluir dados da tabela itens_cli_tel
             PreparedStatement pst = conex.conn.prepareStatement("delete from itens_cli_tel where id_cliente=?");                            
             pst.setInt(1,mod.getId());
             pst.execute();
             //Exclui dados da tabela clientes            
             pst = conex.conn.prepareStatement("delete from clientes where id_cliente=?");
             buscaCod(mod.getIdBairro(),mod.getIdCidade(),"");
             pst.setInt(1,mod.getId());
             pst.execute();                                    
             JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!");            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro na exclusão!\n Erro:"+ex);   
        }
   }  
   public void Alterar(ModeloCliente mod){
      
       conex.conexao();
      
        try {
             //altera a tabela itens_cli_tel
             conex.execultasql("select * from telefone where numero_tel='"+mod.getTelefone()+"'");
             conex.rs.first();
             codTel=conex.rs.getInt("id_telefone");
             PreparedStatement pst = conex.conn.prepareStatement("update itens_cli_tel set id_tel=?where id_cliente=? ");
             pst.setInt(1,codTel);
             pst.setInt(2,mod.getId());
             pst.execute();
              //altera a tabela cliente                                              
       
             pst = conex.conn.prepareStatement("update clientes set nome_cliente=?,endereco_cliente=?,rg_cliente=?,"
                    + "cpf_cliente=?, id_bairro=? where id_cliente=?");
             pst.setString(1,mod.getNome());
             pst.setString(2,mod.getEndereco());
             pst.setString(3,mod.getRg());
             pst.setString(4,mod.getCpf());
             buscaCod(mod.getIdBairro(),mod.getIdCidade(),"");
             pst.setInt(5, codBairro);
             pst.setInt(6, codCidade);            
             pst.execute();
              
                    JOptionPane.showMessageDialog(null,"Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar!\n Erro:"+ex);
        }
                                              
             
       conex.desconecta();
   }
   public ModeloCliente primeiro(){
       conex.conexao();
       
        try {
            
            conex.execultasql("select * from clientes inner join itens_cli_tel on clientes.id_cliente = itens_cli_tel.id_cliente\n" +
"inner join telefone on itens_cli_tel.id_tel=telefone.id_telefone\n" +
"inner join bairro on clientes.id_bairro=bairro.id_bairro inner join cidade on bairro. id_cidade=cidade.id_cidade");
            conex.rs.first();
            
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidade"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        conex.desconecta();
        return modCli;
       
   }
   public ModeloCliente ultimo(){
       conex.conexao();
       
        try {
           
            conex.execultasql("select * from clientes inner join itens_cli_tel on clientes.id_cliente = itens_cli_tel.id_cliente\n" +
"inner join telefone on itens_cli_tel.id_tel=telefone.id_telefone\n" +
"inner join bairro on clientes.id_bairro=bairro.id_bairro inner join cidade on bairro. id_cidade=cidade.id_cidade");
            conex.rs.last();
            
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidade"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        conex.desconecta();
        return modCli;
       
   }
   public ModeloCliente ant(){
        try {
            conex.rs.previous();
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidade"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
       
       
        return modCli;
       
   }
   public ModeloCliente prox(){
       try {
            conex.rs.next();
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidade"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        return modCli;
       
   }
   public void buscaCod(String Bairro,String Cidade,String tel){
            conex.conexao();
        try {
            conex.execultasql("select * from bairro where nome_bairro='"+Bairro+"'");
            conex.rs.first();
            codBairro = conex.rs.getInt("id_bairro");
            conex.execultasql("select * from cidade where nome_cidade='"+Cidade+"'");
            conex.rs.first();
            codCidade = conex.rs.getInt("id_cidade");
            conex.execultasql("select * from telefone where numero_tel='"+tel+"'");
            conex.rs.first();
            codTel = conex.rs.getInt("id_telefone");           
                         
            
        } catch (SQLException ex) {
          // JOptionPane.showMessageDialog(null, "Erro ao selecionar os códigos!\nErro:"+ ex);
        }
       
      //conex.desconecta();
       
   }
   public void buscaNomes(int codBairro, int codCid){
       //conex.execultasql("select * from bairro where id_bairro=" + codBairro);
       
        try {
            conex.execultasql("select * from bairro where id_bairro=" + codBairro);
            conex.rs.first();
            Bairro  = conex.rs.getString("nome_bairro");
            conex.execultasql("select * from cidade where id_cidade=" + codCid);
            conex.rs.first();
            Cidade = conex.rs.getString("nome_cidades");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
   }
}