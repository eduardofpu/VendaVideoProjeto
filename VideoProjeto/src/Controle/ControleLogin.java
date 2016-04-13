/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloLogin;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleLogin {
    
    ConectaBanco conex = new ConectaBanco();
    
    public void salvar(ModeloLogin mod){
        
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into login(nome,senha,permissao,login)values(?,?,?,?)");
             pst.setString(1,mod.getNome());
             pst.setString(2,mod.getSenha());
             
             pst.setString(3,mod.getPermissao());
             
             pst.setString(4,mod.getLogin());
             
             pst.execute();
              JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao inserio usuário!\nErro:"+ ex);
        }
        
        conex.desconecta();
        
    }
    
    
    
     public void Alterar (ModeloLogin mod){   
    conex.conexao();
    
    
        try {            
             
            PreparedStatement pst = conex.conn.prepareStatement("update login set nome=?,senha=?,permissao=?,login=? where id_login=?");
           
             pst.setString(1,mod.getNome());
             pst.setString(2,mod.getSenha());
             pst.setString(3,mod.getPermissao());
             pst.setString(4,mod.getLogin());            
             pst.setInt(5,mod.getId());
             
            pst.execute();
             
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro na alteração dos dados.\n Erro!"+ex);
       }
       conex.desconecta();
       
    }
    
    
    public void Excluir(ModeloLogin mod){
        conex.conexao();
            try {
                //JOptionPane.showMessageDialog(null,mod.getCod());
                PreparedStatement pst = conex.conn.prepareStatement("delete from login where id_login=?");
                pst.setInt(1, mod.getId());
                pst.execute(); 
                 
                 JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
            }                        
      conex.desconecta();
 }
    
    
    public ModeloLogin Primeiro(){
    ModeloLogin mod = new ModeloLogin();
    conex.conexao();
    
    conex.execultasql("select * from login");
        try {
             conex.rs.first();
             mod.setId(conex.rs.getInt("id_login"));
             mod.setNome(conex.rs.getString("nome"));
             mod.setSenha(conex.rs.getString("senha"));
             mod.setPermissao(conex.rs.getString("permissao"));
             mod.setLogin(conex.rs.getString("login"));
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        conex.desconecta();
        
        return mod;  
        
       }
    
    
    public ModeloLogin Uti(){
    ModeloLogin mod = new ModeloLogin();
    conex.conexao();
    
    conex.execultasql("select * from login");
        try {
             conex.rs.last();
             mod.setId(conex.rs.getInt("id_login"));
             mod.setNome(conex.rs.getString("nome"));
             mod.setSenha(conex.rs.getString("senha"));
             mod.setPermissao(conex.rs.getString("permissao"));
             mod.setLogin(conex.rs.getString("login"));
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        conex.desconecta();
        
        return mod;  
        
       }
    
    public ModeloLogin Ant(){
    ModeloLogin mod = new ModeloLogin();
    //conex.conexao();
    
    conex.execultasql("select * from login");
        try {
             conex.rs.previous();
             mod.setId(conex.rs.getInt("id_login"));
             mod.setNome(conex.rs.getString("nome"));
             mod.setSenha(conex.rs.getString("senha"));
             mod.setPermissao(conex.rs.getString("permissao"));
             mod.setLogin(conex.rs.getString("login"));
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        //conex.desconecta();
        
        return mod;  
        
       }
    
    public ModeloLogin Prox(){
    ModeloLogin mod = new ModeloLogin();
    //conex.conexao();
    
    conex.execultasql("select * from login");
        try {
             conex.rs.next();
             mod.setId(conex.rs.getInt("id_login"));
             mod.setNome(conex.rs.getString("nome"));
             mod.setSenha(conex.rs.getString("senha"));
             mod.setPermissao(conex.rs.getString("permissao"));
             mod.setLogin(conex.rs.getString("login"));
            
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Volte para o próximo \nErro:"+ ex);
        }
        //conex.desconecta();
        
        return mod;  
        
       }
    
    
}
