/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visão;

import Controle.ConectaBanco;
import Controle.ControleParcelamentoVenda;
import Controle.ControleVenda;
import Modelo.ModeloTabela;
import Modelo.ModeloParcelamentoVenda;
import Modelo.ModeloVenda;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Eduardo
 */
public class FrmVenda extends javax.swing.JFrame {
    DecimalFormat formato;
    ConectaBanco conn = new ConectaBanco();
    ModeloVenda mod =new ModeloVenda();
    ModeloParcelamentoVenda m = new ModeloParcelamentoVenda();
    ControleVenda control = new ControleVenda();
    ControleParcelamentoVenda c = new ControleParcelamentoVenda();
    int flag=1, codVenda;
    float preco_produto,total=0;
    float  Total=0;// para metodo somar
     

    /**
     * Creates new form FrmVenda
     */
    public FrmVenda() {
        initComponents();
       formato=new DecimalFormat("#0.00");
//        try {
//            MaskFormatter form = new MaskFormatter("##/##/####");
//            jFormattedTextFieldData.setFormatterFactory(new DefaultFormatterFactory(form));
//                        
//        } catch (ParseException ex) {
//            Logger.getLogger(FrmCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //Pega data do sistema
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date hoje = new Date();
        jFormattedTextFieldData.setText(df.format(hoje));
    }
    public void preencherTabelaCliente(String sql){
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Codigo","Nome"};
        conn.conexao();
        conn.execultasql(sql);                
            try {
                conn.rs.first();
                do {
                    //JOptionPane.showMessageDialog(rootPane,conn.rs.getInt(1));
                    dados.add(new Object[]{conn.rs.getString("id_cliente"),conn.rs.getString("nome_cliente")});
                }while(conn.rs.next());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane,"Erro ao preencher o ArrayList!\nErro:"+ex);
            }
          ModeloTabela modelo = new ModeloTabela(dados, Colunas);
          TablePesquisa.setModel(modelo);
          TablePesquisa.getColumnModel().getColumn(0).setPreferredWidth(250);
          TablePesquisa.getColumnModel().getColumn(0).setResizable(false);
          TablePesquisa.getColumnModel().getColumn(1).setPreferredWidth(200);
          TablePesquisa.getColumnModel().getColumn(1).setResizable(false);          
          TablePesquisa.getTableHeader().setReorderingAllowed(false);
          TablePesquisa.setAutoResizeMode(TablePesquisa.AUTO_RESIZE_OFF);
          TablePesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);                                   
    }
    public void preencherTabelaProduto(String sql){
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Codigo","Nome","Qtd"};
        conn.conexao();
        conn.execultasql(sql);                
            try {
                conn.rs.first();
                do {
                    //JOptionPane.showMessageDialog(rootPane,conn.rs.getInt(1));
                    dados.add(new Object[]{conn.rs.getString("id_produto"),conn.rs.getString("nome_produto"),conn.rs.getString("quantidade")});
                }while(conn.rs.next());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane,"Erro ao preencher o ArrayList!\nErro:"+ex);
            }
          ModeloTabela modelo = new ModeloTabela(dados, Colunas);
          TablePesquisa.setModel(modelo);
          TablePesquisa.getColumnModel().getColumn(0).setPreferredWidth(250);
          TablePesquisa.getColumnModel().getColumn(0).setResizable(false);
          TablePesquisa.getColumnModel().getColumn(1).setPreferredWidth(200);
          TablePesquisa.getColumnModel().getColumn(1).setResizable(false);  
          TablePesquisa.getColumnModel().getColumn(1).setPreferredWidth(200);
          TablePesquisa.getColumnModel().getColumn(1).setResizable(false);       
          TablePesquisa.getTableHeader().setReorderingAllowed(false);
          TablePesquisa.setAutoResizeMode(TablePesquisa.AUTO_RESIZE_OFF);
          TablePesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);                                   
    }
     public void preencherTabelaItensVenda(String sql){
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Descrição","Quantidade","Valor Total"};
        conn.conexao();
        conn.execultasql(sql);                
            try {
                conn.rs.first();
                do {
                    //JOptionPane.showMessageDialog(rootPane,conn.rs.getInt(1));
                    float valorProduto = conn.rs.getFloat("preco_venda");
                    int qtdVendida= conn.rs.getInt("quantidade_produto");
                    dados.add(new Object[]{conn.rs.getString("nome_produto"),conn.rs.getString("quantidade_produto"),valorProduto * qtdVendida});
                }while(conn.rs.next());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane,"Erro ao preencher o ArrayList!\nErro:"+ex);
            }
          ModeloTabela modelo = new ModeloTabela(dados, Colunas);
          TableVenda.setModel(modelo);
          TableVenda.getColumnModel().getColumn(0).setPreferredWidth(250);
          TableVenda.getColumnModel().getColumn(0).setResizable(false);
          TableVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
          TableVenda.getColumnModel().getColumn(1).setResizable(false);  
          TableVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
          TableVenda.getColumnModel().getColumn(1).setResizable(false);       
          TableVenda.getTableHeader().setReorderingAllowed(false);
          TableVenda.setAutoResizeMode(TableVenda.AUTO_RESIZE_OFF);
          TableVenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
          somaProduto();
    }
     public void somaProduto(){
       //float  Total=0;
         int qtd;//qtd=0
         float valor ;// valor=0
        conn.execultasql("select * from itens_venda_produto inner join produto on itens_venda_produto.id_produto = produto.id_produto where id_venda="+codVenda);
        try {
            conn.rs.first();
            while(conn.rs.next()){
                qtd=conn.rs.getInt("quantidade");
                valor=conn.rs.getFloat("preco_venda");
                Total=total+conn.rs.getFloat("preco_venda")*conn.rs.getInt("quantidade_produto");
              // JOptionPane.showMessageDialog(rootPane,"qtd"+qtd+"pv"+valor+"");                 
            }
            jTextFieldValorTotal.setText(String.valueOf(Total));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane,"Erro na soma do total!\nErro:"+ex);
        }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelNomeCliente = new javax.swing.JLabel();
        jTextFieldCliente = new javax.swing.JTextField();
        jLabelData = new javax.swing.JLabel();
        jFormattedTextFieldData = new javax.swing.JFormattedTextField();
        jButtonBuscaCliente = new javax.swing.JButton();
        jLabelProduto = new javax.swing.JLabel();
        jTextFieldProduto = new javax.swing.JTextField();
        jButtonIniciar = new javax.swing.JButton();
        jLabelQuantidade = new javax.swing.JLabel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jLabelValorporItem = new javax.swing.JLabel();
        jTextFieldValorporItem = new javax.swing.JTextField();
        jLabelValorTotal = new javax.swing.JLabel();
        jTextFieldValorTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePesquisa = new javax.swing.JTable();
        tabelaPesquisa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableVenda = new javax.swing.JTable();
        jButtonFinaVenda = new javax.swing.JButton();
        jButtonCancelVenda = new javax.swing.JButton();
        jButtonADD = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxPagamento = new javax.swing.JComboBox();
        jButtonPesquisar = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldDinheiro2 = new javax.swing.JTextField();
        jTextFieldTroco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldUnitario = new javax.swing.JTextField();
        jButtonDinheiro = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Vendas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Formulário de Vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNomeCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNomeCliente.setText("Nome do cliente:");

        jTextFieldCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteActionPerformed(evt);
            }
        });

        jLabelData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelData.setText("Data :");

        jFormattedTextFieldData.setBackground(new java.awt.Color(51, 51, 51));
        jFormattedTextFieldData.setEnabled(false);
        jFormattedTextFieldData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDataActionPerformed(evt);
            }
        });
        jFormattedTextFieldData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldDataFocusGained(evt);
            }
        });

        jButtonBuscaCliente.setText("Busca");
        jButtonBuscaCliente.setToolTipText("Busca");
        jButtonBuscaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscaClienteActionPerformed(evt);
            }
        });

        jLabelProduto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelProduto.setText("Produto:");

        jTextFieldProduto.setEnabled(false);
        jTextFieldProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdutoActionPerformed(evt);
            }
        });

        jButtonIniciar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonIniciar.setForeground(new java.awt.Color(255, 51, 51));
        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.setToolTipText("Gera codigo de venda");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        jLabelQuantidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelQuantidade.setText("Quantidade:");

        jTextFieldQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantidadeActionPerformed(evt);
            }
        });
        jTextFieldQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldQuantidadeFocusGained(evt);
            }
        });

        jLabelValorporItem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelValorporItem.setText("Valor por item:");

        jLabelValorTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelValorTotal.setText("Valor Total:");

        jTextFieldValorTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldValorTotal.setForeground(new java.awt.Color(51, 102, 0));

        TablePesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablePesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePesquisaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePesquisa);

        tabelaPesquisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabelaPesquisa.setForeground(new java.awt.Color(0, 102, 102));
        tabelaPesquisa.setText("Tabela de pesquisa:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 102));
        jLabel2.setText("Itens da venda:");

        TableVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TableVenda);

        jButtonFinaVenda.setText("Finalizar Venda");
        jButtonFinaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinaVendaActionPerformed(evt);
            }
        });

        jButtonCancelVenda.setText("Cancelar Venda");
        jButtonCancelVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelVendaActionPerformed(evt);
            }
        });

        jButtonADD.setText("Adicionar");
        jButtonADD.setToolTipText("Adicionar Produto");
        jButtonADD.setEnabled(false);
        jButtonADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADDActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Pagamento:");

        jComboBoxPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A vista", "Parcelado" }));
        jComboBoxPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPagamentoActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.setEnabled(false);
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(241, 241, 241)
                                .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelQuantidade)
                                        .addGap(99, 99, 99)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelValorporItem)
                                            .addComponent(jTextFieldValorporItem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonBuscaCliente)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelData)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabelProduto)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonIniciar))
                                        .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonADD, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonPesquisar)
                                .addGap(8, 8, 8)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonFinaVenda)
                                        .addGap(92, 92, 92)
                                        .addComponent(jButtonCancelVenda))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(118, 118, 118)
                                        .addComponent(jLabelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabelNomeCliente)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(tabelaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeCliente)
                    .addComponent(jLabelProduto)
                    .addComponent(jButtonIniciar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscaCliente)
                    .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelValorporItem)
                        .addComponent(jLabelData))
                    .addComponent(jLabelQuantidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonADD)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldValorporItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(tabelaPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValorTotal)
                    .addComponent(jTextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelVenda)
                    .addComponent(jButtonFinaVenda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane1.setBackground(new java.awt.Color(51, 0, 51));
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Dinheiro :");

        jTextFieldTotal.setBackground(new java.awt.Color(51, 0, 51));
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(255, 153, 0));
        jTextFieldTotal.setBorder(null);
        jTextFieldTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("Total :");

        jTextFieldDinheiro2.setBackground(new java.awt.Color(51, 0, 51));
        jTextFieldDinheiro2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldDinheiro2.setForeground(new java.awt.Color(255, 153, 0));
        jTextFieldDinheiro2.setBorder(null);
        jTextFieldDinheiro2.setCaretColor(new java.awt.Color(0, 0, 51));

        jTextFieldTroco.setBackground(new java.awt.Color(51, 0, 51));
        jTextFieldTroco.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldTroco.setForeground(new java.awt.Color(255, 153, 0));
        jTextFieldTroco.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Unitario :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Troco :");

        jTextFieldUnitario.setBackground(new java.awt.Color(51, 0, 51));
        jTextFieldUnitario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldUnitario.setForeground(new java.awt.Color(255, 153, 0));
        jTextFieldUnitario.setBorder(null);

        jButtonDinheiro.setText("Calcular");
        jButtonDinheiro.setToolTipText("Finalizar calculo");
        jButtonDinheiro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDinheiroActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(51, 0, 51));
        jButton2.setText("Simulador");
        jButton2.setToolTipText("Simular preços e parcelas");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jTextFieldDinheiro2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDinheiro2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(325, 325, 325))
        );
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextFieldTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextFieldDinheiro2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextFieldTroco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextFieldUnitario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonDinheiro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscaClienteActionPerformed
        // TODO add your handling code here:
        flag =1;
        preencherTabelaCliente("select * from clientes where nome_cliente like'%"+jTextFieldCliente.getText()+"%' ");
        
    }//GEN-LAST:event_jButtonBuscaClienteActionPerformed

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        // TODO add your handling code here:
        
           String[] options = {"Sim", "Não"};

        int resposta = JOptionPane.showOptionDialog(null, "Tem certeza que deseja Inicializar a pesquisa ?",
                "Confirmar",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        

        if (resposta == 1) {
        	
        	return;//Este é o Botao do NAO. Ira retornar para a Calculadora
        }
        
        
       
        
        if(resposta == 0){//Este é o Botao do SIM. Ira sair do seu Programa
         conn.conexao();
        try {
            PreparedStatement pst = conn.conn.prepareStatement("insert into venda(valor_venda)values(?)");
            pst.setFloat(1,0);
            pst.execute();
            conn.execultasql("select * from venda ");
            conn.rs.last();
            codVenda=conn.rs.getInt("id_venda");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(rootPane,"Erro:"+ex);
        }
        jTextFieldProduto.setEnabled(true);
        jButtonPesquisar.setEnabled(true);
        jButtonADD.setEnabled(true);
        jButtonIniciar.setEnabled(!true);
        
        
        
               }
       
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void TablePesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePesquisaMouseClicked
        // TODO add your handling code here:
        if(flag==1){
             String nome_cliente = ""+TablePesquisa.getValueAt(TablePesquisa.getSelectedRow(),1);
             jTextFieldCliente.setText(nome_cliente);
             
           
            
        }
        else{
            conn.conexao();
           String nome_produto = ""+TablePesquisa.getValueAt(TablePesquisa.getSelectedRow(),1);
           conn.execultasql("select * from produto where nome_produto='"+nome_produto+"'");
            try {
                conn.rs.first();
                preco_produto=conn.rs.getFloat("preco_venda");
                jTextFieldProduto.setText(nome_produto);
                jTextFieldValorporItem.setText(String.valueOf(preco_produto));
                jTextFieldUnitario.setText(String.valueOf(preco_produto));// Metodo para a janela voltar troco
                jTextFieldQuantidade.setText("1");
                conn.desconecta();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane,"Erro ao pesquisar o preço!");
            }
           jTextFieldProduto.setText(nome_produto);
        }
    }//GEN-LAST:event_TablePesquisaMouseClicked

    private void jTextFieldQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeFocusGained
        // TODO add your handling code here:
        float valorTotal;
        valorTotal = Float.parseFloat(jTextFieldValorporItem.getText()) *Integer.parseInt(jTextFieldQuantidade.getText());
       //  total=total+valorTotal;// não tinha essa linha
        jTextFieldValorTotal.setText(String.valueOf(valorTotal));
        // jTextFieldValorTotal.setText(String.valueOf(total));
    }//GEN-LAST:event_jTextFieldQuantidadeFocusGained

    private void jFormattedTextFieldDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataFocusGained
        // TODO add your handling code here:
         float valorTotal;
        valorTotal = Float.parseFloat(jTextFieldValorporItem.getText()) *Integer.parseInt(jTextFieldQuantidade.getText());
       // total=total+valorTotal;// não tinha essa linha
        jTextFieldValorTotal.setText(String.valueOf(valorTotal));
        //  jTextFieldValorTotal.setText(String.valueOf(total));
    }//GEN-LAST:event_jFormattedTextFieldDataFocusGained

    private void jButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADDActionPerformed
        // TODO add your handling code here:
         String[] options = {"Sim", "Não"};

        int resposta = JOptionPane.showOptionDialog(null, "Tem certeza que deseja Adicionar o produto ?",
                "Confirmar",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        

        if (resposta == 1) {
        	
        	return;//Este é o Botao do NAO. Ira retornar para a Calculadora
        }
        
        
       
        
        if(resposta == 0){//Este é o Botao do SIM. Ira sair do seu Programa
            
             int idVenda,quantidade; //quantidade=0
        conn.conexao();
        conn.execultasql("select * produto where nome_produto='"+jTextFieldProduto.getText()+"'");
        try {
        conn.rs.first();
        quantidade=conn.rs.getInt("quantidade");
        if(quantidade>=Integer.parseInt(jTextFieldQuantidade.getText())){  
        
       
        mod.setIdVenda(codVenda);
        
        mod.setQtdItem(Integer.parseInt(jTextFieldQuantidade.getText()));
        
        mod.setNomeProduto(jTextFieldProduto.getText());
        
        mod.setValorVenda(Float.parseFloat(jTextFieldValorporItem.getText()));
        
        control.adicionaItem(mod);
       
         preencherTabelaItensVenda("select * from produto  inner join itens_venda_produto on produto.id_produto = itens_venda_produto.id_produto inner join venda on venda.id_venda=itens_venda_produto.id_venda where venda.id_venda="+codVenda);
        }else{
        JOptionPane.showMessageDialog(rootPane,"A quantidade desejada não esta disponível no estoque!");
        }
        total =total+ Float.parseFloat(jTextFieldValorporItem.getText())*Integer.parseInt(jTextFieldQuantidade.getText());
        jTextFieldValorTotal.setText(String.valueOf(total));       
        jTextFieldTotal.setText(String.valueOf(total));
                
       
        
        
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(rootPane,"Erro ao pesquisar a quantidade!\nERRO:"+ex);
        }
        } 
        
        
               
        
        
        
       
    }//GEN-LAST:event_jButtonADDActionPerformed

    private void jButtonFinaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinaVendaActionPerformed
        // TODO add your handling code here:
        
       
            
            
        String[] options = {"Sim", "Não"};

        int resposta = JOptionPane.showOptionDialog(null, "Tem certeza que deseja Finalizar a venda ?",
                "Confirmar",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        

        if (resposta == 1) {
        	
        	return;//Este é o Botao do NAO. Ira retornar para a Calculadora
        }
        
        
       
        
        if(resposta == 0){//Este é o Botao do SIM. Ira sair do seu Programa
             if(jComboBoxPagamento.getSelectedItem().equals("A vista")){
            
        mod.setIdVenda(codVenda);// para pegar o codigo da venda atraves do metodo criado dem FrmParcelamento
        mod.setNomeCliente(jTextFieldCliente.getText());           
        mod.setData(jFormattedTextFieldData.getText());
        mod.setValorVenda(Float.parseFloat(jTextFieldValorTotal.getText()));
        mod.setPagamento((String)jComboBoxPagamento.getSelectedItem());
        control.FechaVenda(mod);
       //  m.setNome(jTextFieldCliente.getText());
       //  c.SalvaParcela(m);
        
        dispose();
        //new FrmVenda().setVisible(true);
        }else{
            
        mod.setIdVenda(codVenda);
        mod.setNomeCliente(jTextFieldCliente.getText());
        
        mod.setData(jFormattedTextFieldData.getText());
        mod.setValorVenda(Float.parseFloat(jTextFieldValorTotal.getText()));
        mod.setPagamento((String)jComboBoxPagamento.getSelectedItem());
        control.FechaVenda(mod);
       // m.setNome(jTextFieldCliente.getText());
        // c.SalvaParcela(m);
        
        JOptionPane.showMessageDialog(rootPane, "Venda finalizada");
        
        FrmParcelamento frm = new FrmParcelamento(codVenda);// parametro para receber o codigo da venda
        frm.setVisible(true);
        
        
        dispose();
        
            }
        
        
              }

       
    }//GEN-LAST:event_jButtonFinaVendaActionPerformed

    private void jButtonCancelVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelVendaActionPerformed
        // TODO add your handling code here:
          String[] options = {"Sim", "Não"};

        int resposta = JOptionPane.showOptionDialog(null, "Tem certeza que deseja Cancelar a venda ?",
                "Confirmar",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        

        if (resposta == 1) {
        	
        	return;//Este é o Botao do NAO. Ira retornar para a Calculadora
        }
        
        
       
        
        if(resposta == 0){//Este é o Botao do SIM. Ira sair do seu Programa
         control.CancelaVenda();
         dispose();
         new FrmVenda().setVisible(true);
               }
        JOptionPane.showMessageDialog(rootPane, "Venda cancelada");
        
              
    }//GEN-LAST:event_jButtonCancelVendaActionPerformed

    private void jTextFieldQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuantidadeActionPerformed

    private void jTextFieldClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteActionPerformed

    private void jTextFieldProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProdutoActionPerformed

    private void jComboBoxPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPagamentoActionPerformed

    private void jFormattedTextFieldDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDataActionPerformed

    private void jButtonDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDinheiroActionPerformed
        // TODO add your handling code here:
        jTextFieldDinheiro2.requestFocus();
        float x,y,t;
        x = Float.parseFloat(jTextFieldDinheiro2.getText());
        y= Float.parseFloat(jTextFieldTotal.getText());
        t= x - y;
        
     jTextFieldTroco.setText(formato.format(t));
    }//GEN-LAST:event_jButtonDinheiroActionPerformed

    private void jTextFieldTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new FrmPorcentagem().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        // TODO add your handling code here:
         flag =2;
         preencherTabelaProduto("select * from produto where nome_produto like'%"+jTextFieldProduto.getText()+"%' ");
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePesquisa;
    private javax.swing.JTable TableVenda;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonADD;
    private javax.swing.JButton jButtonBuscaCliente;
    private javax.swing.JButton jButtonCancelVenda;
    private javax.swing.JButton jButtonDinheiro;
    private javax.swing.JButton jButtonFinaVenda;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox jComboBoxPagamento;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextFieldData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelNomeCliente;
    private javax.swing.JLabel jLabelProduto;
    private javax.swing.JLabel jLabelQuantidade;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelValorporItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldDinheiro2;
    private javax.swing.JTextField jTextFieldProduto;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTroco;
    private javax.swing.JTextField jTextFieldUnitario;
    private javax.swing.JTextField jTextFieldValorTotal;
    private javax.swing.JTextField jTextFieldValorporItem;
    private javax.swing.JLabel tabelaPesquisa;
    // End of variables declaration//GEN-END:variables
}
