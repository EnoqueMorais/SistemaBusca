/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysbusca.telas;
import br.com.sysbusca.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author hugov
 */
public class TelaObito extends javax.swing.JInternalFrame {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    /**
     * Creates new form TelaUsuario
     */
    public TelaObito() {
        
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void consultar(){
       String sql = "select * from tbobito where nome like ? and mae like ?";
       
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscarNome.getText() + "%"); 
            pst.setString(2, txtBuscarMae.getText() + "%"); 
            rs = pst.executeQuery();
            
            // usar a biblioteca rs2 xml para preencher a tabela
            tblObito.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void adicionar(){
        String sql = "insert into tbobito (termo, folha, livro , nome, mae, dataObito) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtNome.getText());
            pst.setString(5, txtMae.getText());
            pst.setString(6, txtDataObito.getText());
            txtBuscarNome.setText(txtNome.getText());
            
            
            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty())
                    || (txtLivro.getText().isEmpty()) || (txtNome.getText().isEmpty()) 
                    || (txtMae.getText().isEmpty()) || (txtDataObito.getText().isEmpty()) ) {
                 JOptionPane.showMessageDialog(null, "Preencha os Dados!!");
            }else{
            
            
            
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
        String sql = "update tbobito set termo  = ?, folha  = ?, livro  = ?, nome  = ?, mae  = ?, dataObito = ?  where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtNome.getText());
            pst.setString(5, txtMae.getText());
            pst.setString(6, txtDataObito.getText());
            pst.setString(7, txtId.getText());
            btnInserir.setEnabled(true);
            txtBuscarNome.setText(txtNome.getText());
            
            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty())
                    || (txtLivro.getText().isEmpty()) || (txtNome.getText().isEmpty()) 
                    || (txtMae.getText().isEmpty()) || (txtDataObito.getText().isEmpty()) ) {
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
            String sql = "delete from tbobito where id=?";
            try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
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
        int setar = tblObito.getSelectedRow();
        txtId.setText(tblObito.getModel().getValueAt(setar, 0).toString());
        txtTermo.setText(tblObito.getModel().getValueAt(setar, 1).toString());
        txtFolha.setText(tblObito.getModel().getValueAt(setar, 2).toString());
        txtLivro.setText(tblObito.getModel().getValueAt(setar, 3).toString());
        txtNome.setText(tblObito.getModel().getValueAt(setar, 4).toString());
        txtMae.setText(tblObito.getModel().getValueAt(setar, 5).toString());
        txtDataObito.setText(tblObito.getModel().getValueAt(setar, 6).toString());
        btnInserir.setEnabled(false);
    }
    
    private void limparCampos() {
        txtTermo.setText(null);
        txtFolha.setText(null);
        txtLivro.setText(null);
        txtNome.setText(null);
        txtMae.setText(null);
        txtDataObito.setText(null);
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

        lblMae = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtMae = new javax.swing.JTextField();
        btnInserir = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        lblBuscarNome = new javax.swing.JLabel();
        txtBuscarNome = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtBuscarMae = new javax.swing.JTextField();
        lblBuscarMae = new javax.swing.JLabel();
        btnDeletar1 = new javax.swing.JButton();
        lblTermo3 = new javax.swing.JLabel();
        lblTermo4 = new javax.swing.JLabel();
        lblTermo5 = new javax.swing.JLabel();
        lblTermo6 = new javax.swing.JLabel();
        lblTermo7 = new javax.swing.JLabel();
        lblTermo8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObito = new javax.swing.JTable();
        lblTermo = new javax.swing.JLabel();
        txtTermo = new javax.swing.JTextField();
        lblLivro = new javax.swing.JLabel();
        txtLivro = new javax.swing.JTextField();
        txtFolha = new javax.swing.JTextField();
        lblFolha = new javax.swing.JLabel();
        lblDataObito = new javax.swing.JLabel();
        txtDataObito = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro / consulta Obito");
        setToolTipText("");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lblMae.setText("Mãe");

        txtMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaeActionPerformed(evt);
            }
        });

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

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        lblBuscarNome.setText("Nome");

        txtBuscarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarNomeActionPerformed(evt);
            }
        });
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

        txtBuscarMae.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMaeKeyReleased(evt);
            }
        });

        lblBuscarMae.setText("Mãe");

        btnDeletar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/limpar.png"))); // NOI18N
        btnDeletar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletar1ActionPerformed(evt);
            }
        });

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

        lblTermo7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo7.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo7.setText("*");

        lblTermo8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo8.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo8.setText("*");

        txtId.setEnabled(false);

        lblNome.setText("Nome");

        tblObito.setModel(new javax.swing.table.DefaultTableModel(
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
        tblObito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblObitoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblObito);

        lblTermo.setText("Termo");

        lblLivro.setText("Livro");

        lblFolha.setText("Folha");

        lblDataObito.setText("Data Obito");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBuscarNome)
                            .addComponent(lblBuscarMae, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarMae))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addGap(168, 168, 168))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDeletar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblDataObito)
                                    .addGap(10, 10, 10)
                                    .addComponent(lblTermo8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDataObito, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNome)
                                        .addComponent(lblMae)
                                        .addComponent(lblTermo))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTermo4)
                                            .addComponent(lblTermo3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblTermo5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtTermo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblLivro)
                                            .addGap(10, 10, 10)
                                            .addComponent(lblTermo6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(62, 62, 62)
                                            .addComponent(lblFolha)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblTermo7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtMae, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscarNome)
                            .addComponent(txtBuscarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscarMae)
                            .addComponent(txtBuscarMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTermo)
                    .addComponent(txtTermo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFolha)
                    .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLivro)
                    .addComponent(lblTermo3, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(lblTermo6)
                    .addComponent(lblTermo7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMae)
                    .addComponent(txtMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataObito)
                    .addComponent(txtDataObito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo8)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnInserir)
                    .addComponent(btnEditar)
                    .addComponent(btnDeletar)
                    .addComponent(btnDeletar1))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaeActionPerformed

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        adicionar();
        consultar();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        remover();
        consultar();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar();
        consultar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtBuscarNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNomeKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarNomeKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarMaeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMaeKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarMaeKeyReleased

    private void btnDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletar1ActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnDeletar1ActionPerformed

    private void tblObitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObitoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblObitoMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        txtId.setVisible(false);
        consultar();
    }//GEN-LAST:event_formComponentShown

    private void txtBuscarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarNomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnDeletar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBuscarMae;
    private javax.swing.JLabel lblBuscarNome;
    private javax.swing.JLabel lblDataObito;
    private javax.swing.JLabel lblFolha;
    private javax.swing.JLabel lblLivro;
    private javax.swing.JLabel lblMae;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTermo;
    private javax.swing.JLabel lblTermo3;
    private javax.swing.JLabel lblTermo4;
    private javax.swing.JLabel lblTermo5;
    private javax.swing.JLabel lblTermo6;
    private javax.swing.JLabel lblTermo7;
    private javax.swing.JLabel lblTermo8;
    private javax.swing.JTable tblObito;
    private javax.swing.JTextField txtBuscarMae;
    private javax.swing.JTextField txtBuscarNome;
    private javax.swing.JTextField txtDataObito;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtMae;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTermo;
    // End of variables declaration//GEN-END:variables
}
