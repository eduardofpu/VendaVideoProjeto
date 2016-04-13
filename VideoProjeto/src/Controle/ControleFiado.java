/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloFiado;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleFiado {
    
    String nomeCliente;
    ConectaBanco conex = new ConectaBanco();
    
    
    public void salvar(ModeloFiado mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into fiado( cliente_f,endereco_f,motivo )values( ?,?,? )");
            pst.setString(1,mod.getCliente());
            pst.setString(2, mod.getEndereco());
            pst.setString(3, mod.getMotivo());           
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Dados salvos com sucesso!");
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao salvar!\nErro:"+ ex);
        }
    }
    
    
    
     public void alterar(ModeloFiado mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update  fiado set cliente_f = ?,endereco_f = ?,motivo = ? where id_fiado=?");
            pst.setString(1,mod.getCliente());
            pst.setString(2, mod.getEndereco());
            pst.setString(3, mod.getMotivo());           
            pst.setInt(4, mod.getCodigo());
            pst.execute();
              JOptionPane.showMessageDialog(null,"Dados alterados !");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Erro ao alterar");
        }
    }
     
    public void excluir(ModeloFiado mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from  fiado  where id_fiado =?");
           
            pst.setInt(1, mod.getCodigo());
            pst.execute();
             JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!");
        }
        conex.desconecta();
    }  
    
    
    
     public ModeloFiado Primeiro(){
    ModeloFiado mod = new ModeloFiado();
    conex.conexao();
    
    conex.execultasql("select * from fiado");
        try {
             conex.rs.first();
             mod.setCodigo(conex.rs.getInt("id_fiado"));
             mod.setCliente(conex.rs.getString("cliente_f"));
             mod.setEndereco(conex.rs.getString("endereco_f"));
             mod.setMotivo(conex.rs.getString("motivo"));
            
            
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        conex.desconecta();
        
        return mod;  
        
       }
    
    
    public ModeloFiado Uti(){
    ModeloFiado mod = new ModeloFiado();
    conex.conexao();
    
    conex.execultasql("select * from fiado");
        try {
             conex.rs.last();
             mod.setCodigo(conex.rs.getInt("id_fiado"));
             mod.setCliente(conex.rs.getString("cliente_f"));
             mod.setEndereco(conex.rs.getString("endereco_f"));
             mod.setMotivo(conex.rs.getString("motivo"));
            
            
        } catch (SQLException ex) {
             //JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        conex.desconecta();
        
        return mod;  
        
       }
    
    public ModeloFiado Ant(){
   ModeloFiado mod = new ModeloFiado();
    //conex.conexao();
    
    conex.execultasql("select * from fiado");
        try {
             conex.rs.previous();
           mod.setCodigo(conex.rs.getInt("id_fiado"));
             mod.setCliente(conex.rs.getString("cliente_f"));
             mod.setEndereco(conex.rs.getString("endereco_f"));
             mod.setMotivo(conex.rs.getString("motivo"));
             
            
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        //conex.desconecta();
        
        return mod;  
        
       }
    
    public ModeloFiado Prox(){
    ModeloFiado mod = new ModeloFiado();
    //conex.conexao();
    
    conex.execultasql("select * from fiado");
        try {
             conex.rs.next();
             mod.setCodigo(conex.rs.getInt("id_fiado"));
             mod.setCliente(conex.rs.getString("cliente_f"));
             mod.setEndereco(conex.rs.getString("endereco_f"));
             mod.setMotivo(conex.rs.getString("motivo"));
            
            
        } catch (SQLException ex) {
           //  JOptionPane.showMessageDialog(null, "Erro ao mostrar dados!"+ex);
        }
        //conex.desconecta();
        
        return mod;  
        
       }
    
    
    
     
    
}
