/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Eduardo
 */
public class ConectaBanco {
 
    public Statement stm;
    
    public ResultSet rs;
    
    private String driver = "org.postgresql.Driver"; 
    
    private String  caminho = "jdbc:postgresql://localhost:5432/sistemavideoaula" ;     
            
    private String usuario = "postgres";
    
    private String senha = "5432";
    
    public Connection conn;
    
    public void conexao () {
          System.setProperty("jdbc.Drivers", driver);
          try {
           conn = DriverManager.getConnection(caminho, usuario, senha);
              //JOptionPane.showMessageDialog(null, "Conectado com sucesso!"); 
              
              
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,  "Erro de conexão!\n Erro:" + ex.getMessage());     
            
        }
    }
    public void execultasql (String SQL){
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);            
            rs = stm.executeQuery(SQL); 
        
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null,  "Erro no execultasql!\n Erro:" + ex.getMessage());
        }
        
    }
    public void desconecta() {
        try {
            conn.close();
            // JOptionPane.showMessageDialog(null," Desconectado com sucesso!");
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\n Erro:" + ex.getMessage());
        }
    }
}
    

   
 
    
    
    
    
    