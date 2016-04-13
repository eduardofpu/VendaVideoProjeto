/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleValidacao {
    
    ConectaBanco conn=new ConectaBanco();
    
    int valida;
    
    public void valida(String senha){
        conn.conexao();
        
        conn.execultasql("select * from vencimento");
        try {
            conn.rs.last(); 
            valida=Integer.parseInt(conn.rs.getString("datavenc"));
            
            int operacao=(valida+132)/4;
            
            int senhaValidacao=Integer.parseInt(senha);
            
            if(operacao==senhaValidacao){
                
                int dia,mes,ano;
                
                String AcertaMes,AcertaDia,ProxSenha;
                
                
                
                
                
                SimpleDateFormat df= new  SimpleDateFormat("dd/MM/yyyy");
                Date hoje=new Date();
                String data=df.format(hoje);
                
                
                char[]senhaChar=data.toCharArray();
                
                dia=Integer.parseInt(""+senhaChar[0]+senhaChar[1]);
                mes=Integer.parseInt(""+senhaChar[3]+senhaChar[4]);
                ano=Integer.parseInt(""+senhaChar[6]+senhaChar[7]+senhaChar[8]+senhaChar[9]);
                
                
                if(mes<12){
                   mes++; 
                   if(mes<10){
                      AcertaMes="0"+mes;  
                   }
                   else{
                    AcertaMes=""+mes;
                }
                  
                }else{
                    mes=1;
                    ano++;
                    AcertaMes="0"+mes;  
                }
                //JOptionPane.showMessageDialog(null, "Validou");
                
                if(dia<10){
                    AcertaDia="0"+dia;
                }else{
                    AcertaDia=""+dia;
                }
                
               ProxSenha=AcertaDia+AcertaMes+ano;
               
                PreparedStatement pst= conn.conn.prepareStatement("insert into vencimento(datavenc)values(?)");
                pst.setString(1, ProxSenha);
                pst.execute();  
              // JOptionPane.showMessageDialog(null, ""+ProxSenha);
               
               
            }else{
                JOptionPane.showMessageDialog(null, "Senha Errada");
            }
          
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao validar!\nERRO"+ex);
        }
        
         conn.desconecta();
        
    }
}
