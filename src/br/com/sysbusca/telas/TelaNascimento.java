/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysbusca.telas;

import java.sql.*;
import net.proteanit.sql.DbUtils;
import br.com.sysbusca.dal.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author hugov
 */
public class TelaNascimento extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaNascimento() {

        initComponents();
        conexao = ModuloConexao.conector();
    }

   private void consultar(){
       String sql = "select * from tbnascimento where nome like ? and mae like ?";
       
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscarNome.getText() + "%"); 
            pst.setString(2, txtBuscarMae.getText() + "%"); 
            rs = pst.executeQuery();
            
            // usar a biblioteca rs2 xml para preencher a tabela
            tblNascimento.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
     private void adicionar(){
        String sql = "insert into tbnascimento(termo, folha, livro, nome, mae, dataNasc) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtNome.getText());
            pst.setString(5, txtMae.getText());
            pst.setString(6, txtDataNasc.getText());
            txtBuscarNome.setText(txtNome.getText());
            
            
            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty())
                || (txtLivro.getText().isEmpty()) || (txtNome.getText().isEmpty())
                || (txtMae.getText().isEmpty()|| (txtDataNasc.getText().isEmpty()))
                    ) {
                 JOptionPane.showMessageDialog(null, "Preencha os Dados!!");
            }else{
            
            /*|| (txtLivro.getText().isEmpty()) || (txtNome.getText().isEmpty())
                    || (txtPai.getText().isEmpty())|| (txtMae.getText().isEmpty()) 
                    || (txtDataNasc.getText().isEmpty())*/
            
            limparCampos();
            
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Registro salvo com Sucesso!");
            }
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void alterar(){
        String sql = "update tbnascimento set termo = ?, folha  = ?, livro  = ?, nome  = ?, mae  = ?, dataNasc  = ?  where id = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtNome.getText());
            pst.setString(5, txtMae.getText());
            pst.setString(6, txtDataNasc.getText());
            pst.setString(7, txtId.getText());
            btnInserir.setEnabled(true);
            txtBuscarNome.setText(txtNome.getText());
            
            
           if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty())
                || (txtLivro.getText().isEmpty()) || (txtNome.getText().isEmpty())
                || (txtMae.getText().isEmpty()|| (txtDataNasc.getText().isEmpty()))) {
                 JOptionPane.showMessageDialog(null, "Preencha os Dados!!");
            }else{
            limparCampos();          
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Registro Editado com Sucesso!");
            }
            } 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void remover(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            String sql = "delete from tbnascimento where termo = ?";
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            btnInserir.setEnabled(true);
            
           
            
            int apagado = pst.executeUpdate();
             if(apagado > 0){
                JOptionPane.showMessageDialog(null, "Registro Apagado com Sucesso!");
            }   limparCampos();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void setar_campos(){
        int setar = tblNascimento.getSelectedRow();
        txtId.setText(tblNascimento.getModel().getValueAt(setar, 0).toString());
        txtTermo.setText(tblNascimento.getModel().getValueAt(setar, 1).toString());
        txtFolha.setText(tblNascimento.getModel().getValueAt(setar, 2).toString());
        txtLivro.setText(tblNascimento.getModel().getValueAt(setar, 3).toString());
        txtNome.setText(tblNascimento.getModel().getValueAt(setar, 4).toString());
        txtMae.setText(tblNascimento.getModel().getValueAt(setar, 5).toString());
        
        
        txtDataNasc.setText(tblNascimento.getModel().getValueAt(setar, 6).toString());
        btnInserir.setEnabled(false);
    }
    
    private void limparCampos() {
        txtTermo.setText(null);
        txtFolha.setText(null);
        txtLivro.setText(null);
        txtNome.setText(null);
        txtMae.setText(null);
        txtDataNasc.setText(null);
        txtId.setText(null);
        txtBuscarNome.setText(null);
        txtBuscarMae.setText(null);
        btnInserir.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        lblMae = new javax.swing.JLabel();
        btnInserir = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        lblBuscaNome = new javax.swing.JLabel();
        txtBuscarNome = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNascimento = new javax.swing.JTable();
        lblDataNasc = new javax.swing.JLabel();
        txtDataNasc = new javax.swing.JTextField();
        lblTermo = new javax.swing.JLabel();
        txtTermo = new javax.swing.JTextField();
        lblLivro = new javax.swing.JLabel();
        txtLivro = new javax.swing.JTextField();
        txtFolha = new javax.swing.JTextField();
        lblFolha = new javax.swing.JLabel();
        txtMae = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        txtId = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        lblTermo1 = new javax.swing.JLabel();
        lblTermo2 = new javax.swing.JLabel();
        lblTermo3 = new javax.swing.JLabel();
        lblTermo4 = new javax.swing.JLabel();
        lblTermo5 = new javax.swing.JLabel();
        lblTermo6 = new javax.swing.JLabel();
        txtBuscarMae = new javax.swing.JTextField();
        lblBuscaMae = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro / consulta Nascimento");
        setPreferredSize(new java.awt.Dimension(640, 480));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        lblNome.setText("Nome");

        lblMae.setText("Mãe");

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/inserir_1.png"))); // NOI18N
        btnInserir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/deletar.png"))); // NOI18N
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        lblBuscaNome.setText("Nome");

        txtBuscarNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarNomeKeyReleased(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/buscar pequeno.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblNascimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TERMO", "LIVRO", "FOLHA", "NOME", "MAE", "DATA"
            }
        ));
        tblNascimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNascimentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNascimento);

        lblDataNasc.setText("Data Nascimento");

        lblTermo.setText("Termo");

        lblLivro.setText("Livro");

        lblFolha.setText("Folha");

        txtMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaeActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        lblTermo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo1.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo1.setText("*");

        lblTermo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo2.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo2.setText("*");

        lblTermo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo3.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo3.setText("*");

        lblTermo4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo4.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo4.setText("*");

        lblTermo5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo5.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo5.setText("*");

        lblTermo6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo6.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo6.setText("*");

        txtBuscarMae.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMaeKeyReleased(evt);
            }
        });

        lblBuscaMae.setText("Mãe");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Consultar");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cadastrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblDataNasc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTermo6, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTermo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTermo1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTermo2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMae, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTermo3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMae)
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTermo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLivro)
                                .addGap(2, 2, 2)
                                .addComponent(lblTermo4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(lblFolha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTermo5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 29, Short.MAX_VALUE)
                        .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(130, 130, 130))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBuscaMae)
                                .addGap(25, 25, 25)
                                .addComponent(txtBuscarMae, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBuscaNome)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscaNome)
                            .addComponent(txtBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscaMae)
                            .addComponent(txtBuscarMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTermo)
                    .addComponent(txtTermo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFolha)
                    .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLivro)
                    .addComponent(lblTermo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTermo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTermo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMae)
                    .addComponent(lblTermo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataNasc)
                        .addComponent(lblTermo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeletar)
                    .addComponent(btnInserir)
                    .addComponent(btnEditar)
                    .addComponent(btnLimpar))
                .addGap(29, 29, 29))
        );

        setBounds(0, 0, 740, 511);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        adicionar();
        consultar();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        remover();
        consultar();

    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNomeKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarNomeKeyReleased

    private void tblNascimentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNascimentoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblNascimentoMouseClicked

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

        txtId.setVisible(false);
        consultar();
    }//GEN-LAST:event_formComponentShown

    private void txtMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaeActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar();
        consultar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtBuscarMaeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMaeKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarMaeKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBuscaMae;
    private javax.swing.JLabel lblBuscaNome;
    private javax.swing.JLabel lblDataNasc;
    private javax.swing.JLabel lblFolha;
    private javax.swing.JLabel lblLivro;
    private javax.swing.JLabel lblMae;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTermo;
    private javax.swing.JLabel lblTermo1;
    private javax.swing.JLabel lblTermo2;
    private javax.swing.JLabel lblTermo3;
    private javax.swing.JLabel lblTermo4;
    private javax.swing.JLabel lblTermo5;
    private javax.swing.JLabel lblTermo6;
    private javax.swing.JTable tblNascimento;
    private javax.swing.JTextField txtBuscarMae;
    private javax.swing.JTextField txtBuscarNome;
    private javax.swing.JTextField txtDataNasc;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtMae;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTermo;
    // End of variables declaration//GEN-END:variables
}
