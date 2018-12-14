/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysbusca.telas;
import br.com.sysbusca.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author hugov
 */
public class TelaCasamento extends javax.swing.JInternalFrame {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    /**
     * Creates new form TelaUsuario
     */
    public TelaCasamento() {
        
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void consultar(){
       String sql = "select * from tbcasamento where esposo like ? and esposa like ?";
       
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscarEsposo.getText() + "%"); 
            pst.setString(2, txtBuscarEsposa.getText() + "%"); 
            rs = pst.executeQuery();
            
            // usar a biblioteca rs2 xml para preencher a tabela
            tblCasamento.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionar(){
        String sql = "insert into tbcasamento( termo, folha, livro, esposo, esposa, dataCas) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtEsposo.getText());
            pst.setString(5, txtEsposa.getText());
            pst.setString(6, txtDataCas.getText());
            txtBuscarEsposo.setText(txtEsposo.getText());
            
            
            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty()) 
                    || (txtLivro.getText().isEmpty()) || (txtEsposo.getText().isEmpty())
                    || (txtEsposa.getText().isEmpty())
                    || (txtDataCas.getText().isEmpty())) {
                 JOptionPane.showMessageDialog(null, "Preencha os Dados!!");
            }else{
            
            limparCampos();
            
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Registro salvo com Sucesso!");
            }
            } 
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void alterar(){
        String sql = "update tbcasamento set termo = ?, folha  = ?, livro  = ?, esposo  = ?, esposa  = ?, dataCas  = ?  where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTermo.getText());
            pst.setString(2, txtFolha.getText());
            pst.setString(3, txtLivro.getText());
            pst.setString(4, txtEsposo.getText());
            pst.setString(5, txtEsposa.getText());
            pst.setString(6, txtDataCas.getText());
            pst.setString(7, txtId.getText());
            txtBuscarEsposo.setText(txtEsposo.getText());
            
            
            if((txtTermo.getText().isEmpty()) || (txtFolha.getText().isEmpty()) 
                    || (txtLivro.getText().isEmpty()) || (txtEsposo.getText().isEmpty())
                    || (txtDataCas.getText().isEmpty())) {
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
            String sql = "delete from tbcasamento where id=?";
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
        int setar = tblCasamento.getSelectedRow();
        txtId.setText(tblCasamento.getModel().getValueAt(setar, 0).toString());
        txtTermo.setText(tblCasamento.getModel().getValueAt(setar, 1).toString());
        txtFolha.setText(tblCasamento.getModel().getValueAt(setar, 2).toString());
        txtLivro.setText(tblCasamento.getModel().getValueAt(setar, 3).toString());
        txtEsposo.setText(tblCasamento.getModel().getValueAt(setar, 4).toString());
        txtEsposa.setText(tblCasamento.getModel().getValueAt(setar, 5).toString());
        txtDataCas.setText(tblCasamento.getModel().getValueAt(setar, 6).toString());
        btnInserir.setEnabled(false);
    }
    
    private void limparCampos() {
        txtTermo.setText(null);
        txtFolha.setText(null);
        txtLivro.setText(null);
        txtEsposo.setText(null);
        txtEsposa.setText(null);
        txtDataCas.setText(null);
        txtId.setText(null);
        txtBuscarEsposo.setText(null);
        txtBuscarEsposa.setText(null);
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

        lblNoiva = new javax.swing.JLabel();
        txtEsposo = new javax.swing.JTextField();
        lblDataCas = new javax.swing.JLabel();
        txtDataCas = new javax.swing.JTextField();
        lblTermo = new javax.swing.JLabel();
        txtTermo = new javax.swing.JTextField();
        lblLivro = new javax.swing.JLabel();
        txtLivro = new javax.swing.JTextField();
        txtEsposa = new javax.swing.JTextField();
        btnInserir = new javax.swing.JButton();
        lblFolha = new javax.swing.JLabel();
        btnDeletar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        lblBuscarNoivo = new javax.swing.JLabel();
        txtBuscarEsposo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblNoivo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCasamento = new javax.swing.JTable();
        txtBuscarEsposa = new javax.swing.JTextField();
        lblBuscarNoiva = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtFolha = new javax.swing.JTextField();
        lblTermo1 = new javax.swing.JLabel();
        lblTermo2 = new javax.swing.JLabel();
        lblTermo3 = new javax.swing.JLabel();
        lblTermo4 = new javax.swing.JLabel();
        lblTermo5 = new javax.swing.JLabel();
        lblTermo6 = new javax.swing.JLabel();
        btnDeletar1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro / consulta Casamento");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lblNoiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNoiva.setText("Noiva");

        lblDataCas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDataCas.setText("Data Casamento");

        lblTermo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTermo.setText("Termo");

        lblLivro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLivro.setText("Livro");

        txtEsposa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEsposaActionPerformed(evt);
            }
        });

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/inserir_1.png"))); // NOI18N
        btnInserir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        lblFolha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFolha.setText("Folha");

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

        lblBuscarNoivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBuscarNoivo.setText("Noivo");

        txtBuscarEsposo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEsposoKeyReleased(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/buscar pequeno.png"))); // NOI18N
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblNoivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNoivo.setText("Noivo");

        tblCasamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "TERMO", "LIVRO", "FOLHA", "NOIVO", "NOIVA", "DATA CAS."
            }
        ));
        tblCasamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCasamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCasamento);

        txtBuscarEsposa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEsposaActionPerformed(evt);
            }
        });
        txtBuscarEsposa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEsposaKeyReleased(evt);
            }
        });

        lblBuscarNoiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBuscarNoiva.setText("Noiva");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
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

        btnDeletar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/limpar.png"))); // NOI18N
        btnDeletar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBuscarNoivo)
                            .addComponent(lblBuscarNoiva, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscarEsposo, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(txtBuscarEsposa))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblTermo)
                            .addGap(12, 12, 12)
                            .addComponent(lblTermo1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTermo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(lblLivro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTermo2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFolha)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTermo3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblDataCas)
                            .addGap(1, 1, 1)
                            .addComponent(lblTermo6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDataCas, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblNoiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNoivo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTermo4)
                                .addComponent(lblTermo5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEsposa)
                                .addComponent(txtEsposo, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarEsposo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBuscarNoivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscarNoiva)
                            .addComponent(txtBuscarEsposa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblLivro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTermo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTermo3, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                        .addComponent(lblFolha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTermo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTermo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTermo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtLivro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoivo)
                    .addComponent(txtEsposo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoiva)
                    .addComponent(txtEsposa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermo5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataCas)
                        .addComponent(txtDataCas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTermo6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInserir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeletar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEsposaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEsposaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEsposaActionPerformed

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

    private void txtBuscarEsposoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEsposoKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarEsposoKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblCasamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCasamentoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblCasamentoMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        txtId.setVisible(false);
        consultar();
    }//GEN-LAST:event_formComponentShown

    private void txtBuscarEsposaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEsposaKeyReleased
        consultar();
    }//GEN-LAST:event_txtBuscarEsposaKeyReleased

    private void txtBuscarEsposaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEsposaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEsposaActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletar1ActionPerformed
        limparCampos();
      
    }//GEN-LAST:event_btnDeletar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnDeletar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBuscarNoiva;
    private javax.swing.JLabel lblBuscarNoivo;
    private javax.swing.JLabel lblDataCas;
    private javax.swing.JLabel lblFolha;
    private javax.swing.JLabel lblLivro;
    private javax.swing.JLabel lblNoiva;
    private javax.swing.JLabel lblNoivo;
    private javax.swing.JLabel lblTermo;
    private javax.swing.JLabel lblTermo1;
    private javax.swing.JLabel lblTermo2;
    private javax.swing.JLabel lblTermo3;
    private javax.swing.JLabel lblTermo4;
    private javax.swing.JLabel lblTermo5;
    private javax.swing.JLabel lblTermo6;
    private javax.swing.JTable tblCasamento;
    private javax.swing.JTextField txtBuscarEsposa;
    private javax.swing.JTextField txtBuscarEsposo;
    private javax.swing.JTextField txtDataCas;
    private javax.swing.JTextField txtEsposa;
    private javax.swing.JTextField txtEsposo;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtTermo;
    // End of variables declaration//GEN-END:variables
}
