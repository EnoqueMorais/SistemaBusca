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
 * @author EnOqUe
 */
public class TelaEscritura extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaEscritura() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
     private void consultar(){
       String sql = "select * from tbescritura where outorgante like ? and outorgado like ?";
       
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscarOutorgante.getText() + "%"); 
            pst.setString(2, txtBuscarOutorgado.getText() + "%"); 
            rs = pst.executeQuery();
            
            // usar a biblioteca rs2 xml para preencher a tabela
            tblProcuracao.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
     private void adicionar(){
        String sql = "insert into tbescritura(termo, folha, livro, outorgante, outorgado) values (?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtOutorgante.getText());
            pst.setString(5, txtOutorgado.getText());
            txtBuscarOutorgante.setText(txtOutorgante.getText());
            
            
            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty())
                || (txtLivro.getText().isEmpty()) || (txtOutorgante.getText().isEmpty())
                || (txtOutorgado.getText().isEmpty())
                    ) {
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
        String sql = "update tbescritura set termo = ?, folha  = ?, livro  = ?, outorgante  = ?, outorgado  = ?  where id = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtOutorgante.getText());
            pst.setString(5, txtOutorgado.getText());
            pst.setString(6, txtId.getText());
            btnInserir.setEnabled(true);
            txtBuscarOutorgante.setText(txtOutorgante.getText());

            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty())
                || (txtLivro.getText().isEmpty()) || (txtOutorgante.getText().isEmpty())
                || (txtOutorgado.getText().isEmpty())
                    ) {
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
            String sql = "delete from escritura where id = ?";
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
        int setar = tblProcuracao.getSelectedRow();
        txtId.setText(tblProcuracao.getModel().getValueAt(setar, 0).toString());
        txtTermo.setText(tblProcuracao.getModel().getValueAt(setar,1).toString());
        txtFolha.setText(tblProcuracao.getModel().getValueAt(setar, 2).toString());
        txtLivro.setText(tblProcuracao.getModel().getValueAt(setar, 3).toString());
        txtOutorgante.setText(tblProcuracao.getModel().getValueAt(setar, 4).toString());
        txtOutorgado.setText(tblProcuracao.getModel().getValueAt(setar, 5).toString());
        btnInserir.setEnabled(false);
    }
    
    private void limparCampos() {
        txtTermo.setText(null);
        txtFolha.setText(null);
        txtLivro.setText(null);
        txtOutorgante.setText(null);
        txtOutorgado.setText(null);
        txtId.setText(null);
        txtBuscarOutorgante.setText(null);
        txtBuscarOutorgado.setText(null);
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

        txtLivro = new javax.swing.JTextField();
        txtFolha = new javax.swing.JTextField();
        lblFolha = new javax.swing.JLabel();
        txtOutorgado = new javax.swing.JTextField();
        txtOutorgante = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        txtId = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        lblTermo4 = new javax.swing.JLabel();
        lblOutorgante = new javax.swing.JLabel();
        lblTermo5 = new javax.swing.JLabel();
        lblTermo1 = new javax.swing.JLabel();
        txtBuscarOutorgante = new javax.swing.JTextField();
        lblOutorgado = new javax.swing.JLabel();
        lblTermo2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnInserir = new javax.swing.JButton();
        txtBuscarOutorgado = new javax.swing.JTextField();
        lblTermo3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProcuracao = new javax.swing.JTable();
        btnDeletar = new javax.swing.JButton();
        lblBuscaOutorgante = new javax.swing.JLabel();
        lblBuscarOutorgado = new javax.swing.JLabel();
        lblTermo = new javax.swing.JLabel();
        txtTermo = new javax.swing.JTextField();
        lblLivro = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro / consulta Escritura");
        setToolTipText("");

        lblFolha.setText("Folha");

        txtOutorgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOutorgadoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/editar.png"))); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        txtId.setEnabled(false);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/limpar.png"))); // NOI18N
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        lblTermo4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo4.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo4.setText("*");

        lblOutorgante.setText("Outorgante");

        lblTermo5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo5.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo5.setText("*");

        lblTermo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo1.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo1.setText("*");

        txtBuscarOutorgante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarOutorganteKeyReleased(evt);
            }
        });

        lblOutorgado.setText("Outorgado");

        lblTermo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo2.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo2.setText("*");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/buscar pequeno.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/inserir_1.png"))); // NOI18N
        btnInserir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        txtBuscarOutorgado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarOutorgadoKeyReleased(evt);
            }
        });

        lblTermo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo3.setForeground(new java.awt.Color(255, 0, 0));
        lblTermo3.setText("*");

        tblProcuracao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TERMO", "LIVRO", "FOLHA", "OUTORGANTE", "OUTORGADO"
            }
        ));
        tblProcuracao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProcuracaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProcuracao);

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/deletar.png"))); // NOI18N
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        lblBuscaOutorgante.setText("Outorgante");

        lblBuscarOutorgado.setText("Outorgado");

        lblTermo.setText("Termo");

        lblLivro.setText("Livro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTermo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTermo1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblOutorgante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblTermo2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblOutorgado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTermo3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtOutorgado)
                                    .addComponent(txtOutorgante, javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(130, 130, 130))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscarOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscaOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscaOutorgante)
                            .addComponent(txtBuscarOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscarOutorgado)
                            .addComponent(txtBuscarOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOutorgante)
                    .addComponent(txtOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOutorgado)
                    .addComponent(lblTermo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpar)
                            .addComponent(btnDeletar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInserir)
                            .addComponent(btnEditar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOutorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutorgadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOutorgadoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar();
        consultar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtBuscarOutorganteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarOutorganteKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarOutorganteKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        adicionar();
        consultar();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void txtBuscarOutorgadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarOutorgadoKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarOutorgadoKeyReleased

    private void tblProcuracaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProcuracaoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblProcuracaoMouseClicked

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        remover();
        consultar();
    }//GEN-LAST:event_btnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBuscaOutorgante;
    private javax.swing.JLabel lblBuscarOutorgado;
    private javax.swing.JLabel lblFolha;
    private javax.swing.JLabel lblLivro;
    private javax.swing.JLabel lblOutorgado;
    private javax.swing.JLabel lblOutorgante;
    private javax.swing.JLabel lblTermo;
    private javax.swing.JLabel lblTermo1;
    private javax.swing.JLabel lblTermo2;
    private javax.swing.JLabel lblTermo3;
    private javax.swing.JLabel lblTermo4;
    private javax.swing.JLabel lblTermo5;
    private javax.swing.JTable tblProcuracao;
    private javax.swing.JTextField txtBuscarOutorgado;
    private javax.swing.JTextField txtBuscarOutorgante;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtOutorgado;
    private javax.swing.JTextField txtOutorgante;
    private javax.swing.JTextField txtTermo;
    // End of variables declaration//GEN-END:variables
}
