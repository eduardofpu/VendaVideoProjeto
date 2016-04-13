/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloBairro;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleBairro {
    ConectaBanco conex = new ConectaBanco();
    ConectaBanco conexPesq = new ConectaBanco();
    int codCid;
    String cidade;
   
public void Grava(ModeloBairro mod){
   conex.conexao();
  
        try {
             conex.execultasql("select * from cidade where nome_cidade='" + mod.getCidade() +"'");
             conex.rs.first();
             codCid = conex.rs.getInt("id_cidade");
             PreparedStatement pst = conex.conn.prepareStatement("insert into bairro (nome_bairro,id_cidade)values(?,?)");
             pst.setString(1,mod.getNome());
             pst.setInt(2, codCid);
             pst.execute();
             JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
        }
        conex.desconecta();            
  }
public ModeloBairro Primeiro(){
    ModeloBairro mod = new ModeloBairro();
    conex.conexao();
    conexPesq.conexao();
    conex.execultasql("select * from bairro");
        try {
             conex.rs.first();
             conexPesq.execultasql("select * from cidade where id_cidade="+ conex.rs.getInt("id_cidade"));
             conexPesq.rs.first();
             cidade = conexPesq.rs.getString("nome_cidade");
             mod.setCod(conex.rs.getInt("id_bairro"));
             mod.setNome(conex.rs.getString("nome_bairro"));
             mod.setCidade(cidade);
             
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        conex.desconecta();
        conexPesq.desconecta();
        return mod;  
        
       }

public ModeloBairro Ant(){
    ModeloBairro mod = new ModeloBairro();
    conex.conexao();
    conexPesq.conexao();
        try {
            conex.rs.previous();
            conexPesq.execultasql("select * from cidade  where id_cidade ="+conex.rs.getInt("id_cidade"));
            conexPesq.rs.first();
            cidade = conexPesq.rs.getString("nome_cidade");
             mod.setCod(conex.rs.getInt("id_bairro"));
             mod.setNome(conex.rs.getString("nome_bairro"));
             mod.setCidade(cidade);
            
          
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        return mod;            
    
  }
public ModeloBairro Prox(){
    ModeloBairro mod = new ModeloBairro();
    conex.conexao();
    conexPesq.conexao(); 
        try {
            conex.rs.next();
            conexPesq.execultasql("select * from cidade  where id_cidade ="+conex.rs.getInt("id_cidade"));
            conexPesq.rs.first();
             cidade = conexPesq.rs.getString("nome_cidade");
             mod.setCod(conex.rs.getInt("id_bairro"));
             mod.setNome(conex.rs.getString("nome_bairro"));
             mod.setCidade(cidade);                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        conexPesq.desconecta();        
        return mod;           

    }
public ModeloBairro Uti(){
    ModeloBairro mod = new ModeloBairro();
    conex.conexao();
    conexPesq.conexao(); 
    conex.execultasql("select * from bairro");
        try {
            conex.rs.last();
            conexPesq.execultasql("select * from cidade  where id_cidade ="+conex.rs.getInt("id_cidade"));
            conexPesq.rs.first();
             cidade = conexPesq.rs.getString("nome_cidade");
             mod.setCod(conex.rs.getInt("id_bairro"));
             mod.setNome(conex.rs.getString("nome_bairro"));
             mod.setCidade(cidade);                  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        conex.desconecta();
        conexPesq.desconecta();        
        return mod;           
   }
public void Alterar (ModeloBairro mod){   
    conex.conexao();
    conexPesq.conexao();
    
        try {
            conexPesq.execultasql("select * from cidade  where nome_cidade ="+ mod.getNome());
             conexPesq.rs.first();
             codCid = conex.rs.getInt("id_cidade");
             PreparedStatement pst = conex.conn.prepareStatement("update bairro set nome_bairro = ?,  id_cidade=? where id_bairro=?");
             pst.setString(1,mod.getNome());
             pst.setInt(2, codCid);
             pst.setInt(3, mod.getCod());
             pst.execute();
             
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro na alteração dos dados.\n Erro!"+ex);
       }
       conex.desconecta();
       conexPesq.desconecta();
    }
 public void Excluir(ModeloBairro mod){
        conex.conexao();
            try {
                //JOptionPane.showMessageDialog(null,mod.getCod());
                PreparedStatement pst = conex.conn.prepareStatement("delete from bairro where id_bairro=?");
                pst.setInt(1, mod.getCod());
                pst.execute(); 
                 
                 JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
            }                        
      conex.desconecta();
 }
}

