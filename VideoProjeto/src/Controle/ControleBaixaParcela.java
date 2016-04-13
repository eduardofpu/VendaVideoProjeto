/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Modelo.ModeloBaixaParcela;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class ControleBaixaParcela {
   ConectaBanco conex = new ConectaBanco();// serve para trazer os codigos da parcela
   
   
   
    public ModeloBaixaParcela BuscaParcela(ModeloBaixaParcela mod){
        conex.conexao();
        
        
       try {
           conex.execultasql("select * from parcela_venda where id_parc_venda = "+mod.getCodParc());
           
           conex.rs.first();
           mod.setCodParc(conex.rs.getInt("id_parc_venda"));
           mod.setCodVenda(conex.rs.getInt("cod_venda"));
           mod.setDataVenc(conex.rs.getString("datavenc"));
           mod.setValor(conex.rs.getFloat("valor_parce"));
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Erro ao buscar parcela!\nErro:" +ex);
       }
        
        conex.desconecta();
       return mod;
        
        
    }
    
    
    // função para baixa de parcela
    
    public void BaixarParcela(ModeloBaixaParcela mod){
        conex.conexao();
        conex.execultasql("select * from parcela_venda where id_parc_venda="+mod.getCodParc()+" and estado='PG'");
       try {
           if(conex.rs.first()){
                JOptionPane.showMessageDialog(null,"Esta parcela já esta paga!");
       }else{
            
       try {
           PreparedStatement pst = conex.conn.prepareStatement("Update parcela_venda set estado = ? where id_parc_venda=?");
           pst.setString(1,"PG");
           pst.setInt(2,mod.getCodParc());
           
           pst.execute();
           
            JOptionPane.showMessageDialog(null,"Parcela baixada com sucesso!");
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Erro ao dar Baixa na Parcela!\nErro:"+ ex);
       }
        
        conex.desconecta();
        
     }// termina o else
           
           
          } catch (SQLException ex) {
           Logger.getLogger(ControleBaixaParcela.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}