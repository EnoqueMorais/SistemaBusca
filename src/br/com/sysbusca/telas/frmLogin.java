/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysbusca.telas;
//Temos que importar colocarndo o caminho completo onde se enconta o modulo
import br.com.sysbusca.dal.ModuloConexao;
import java.awt.Color;
import java.sql.*; //Importa todas as bibliotecas do sql, é bom importar esse pacote tambem
import javax.swing.JOptionPane;
/**
 *
 * @author EnOqUe
 */
public class frmLogin extends javax.swing.JFrame {
    //Inicialização das chamadas da conexão
        Connection conexao = null; //chamadando a conexão no momento não vai receber nada
        PreparedStatement pst = null;//PreparedStatement Carrega algumas configurações,e criamos uma variavel chamada de pst que não recebre nada
        ResultSet rs = null;
        
        //craindo função chamada logar para verificar se cadastro de logo
        public void logar(){
            /*Vamos fazer uam consultar SQL por de uma String chamada sql, onde ele vai selecionar todas 
            as colunas da tabela tbusuarios onde o campo login vai receber um valor e senha também */
            String sql = "select * from tbusuarios where login = ? and senha = ?";
            try {
                pst = conexao.prepareStatement(sql);//passando a consultar a 
                pst.setString(1, txt_usuarios.getText());/*pegando as informações que forem digitados no campo login
                temos que passa a propriedade 1 para guarda a informação com campo 1 
                e nome da variavel craida no frmLogin*/
                pst.setString(2, txt_senha.getText());
                //executar a query - consulta
                rs = pst.executeQuery();
                if(rs.next()){
                    /*Vamos redirecionar para a tela principal, e devemos instaciar a tela principal
                    não precisamos importar nada porque a classe tela principal estar dentro do nosso projeto*/
                    String perfil = rs.getString(6);/*Criamos uma string cahamda de perfil
                    onde ela vai receber um getString onde ela vai pegar a tabela usuarios onde o campo 
                    6 e a coluna com nome perfil*/
                   /*System.out.println(perfil);Na saida irá mostar o valor que é guarda na coluna
                    perfil de acordo com o usuario*/
                    if(perfil.equals("admin")){
                        //se meu Perfil tiver um valor igual a admin entra nesta condição
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.menuCadUsu.setEnabled(true);
                        //Habilitando o submenu Usuarios já que somente admin tem aesso
                        TelaPrincipal.lblUsuario.setText(rs.getString(2)); /*caso Usuario seja usuario comun irá aparcer na tela
                        principal TelaPrincipal.lblUsuario.setText(rs.getString(2)) 2 significa o coluna do 2 do bd 
                        coluna usuarios*/
                       // TelaPrincipal.lblUsuario.setForeground(Color.red);
                        TelaPrincipal.lblUsuario.setForeground(Color.red);
                        this.dispose();//Fecha o formulario de login
                        conexao.close();//Fechar a conexão apos login
                    }else{ //se o perfil tiver valor diferente de admin entra nesta condição
                          TelaPrincipal principal = new TelaPrincipal();
                          principal.setVisible(true);
                          TelaPrincipal.lblUsuario.setText(rs.getString(2)); /*caso Usuario seja usuario comun irá aparcer na tela
                          principal TelaPrincipal.lblUsuario.setText(rs.getString(2)) 2 significa o coluna do 2 do bd 
                          coluna usuarios*/
                          this.dispose();//Fecha o formulario de login
                          conexao.close();//Fechar a conexão apos login
                    
                    }
                    
                }else{/*se não retorna uma messagem por meio do  JOptionPane.showMessageDialog(null, "Usuário ou senha invalida!!");
                    caso o login e senha estão invalidado irá aparece a messagem que está entre aspas, na primeira é pra selecionar o painel, 
                    mas iremos usar o padrão que que o null*/
                    JOptionPane.showMessageDialog(null, "Usuário ou senha invalida!!");
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);//Caso tenha algum erro será exibido
            }
        }


    /**
     * Creates new form frmLogin
     */
    public frmLogin() {
        initComponents();
        conexao = ModuloConexao.conector(); 
        //A variavel conexao recebe moduloconexão e metodo conector
        //Se conectar ele me mostra a string da conexão
        System.out.println(conexao);
        if(conexao!= null){//se conexão for diferente de nula ira aparecer na tela de login o nome Conectado
            //esse é comanda para selecionar uma imagem como icones e adicionamos o caminho da imagem
            lbl_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/dbok.png")));
        }else{//se não aparecera desconectado
            lbl_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/dberro.png")));
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

        txt_usuarios = new javax.swing.JTextField();
        txt_senha = new javax.swing.JPasswordField();
        btn_Login = new javax.swing.JButton();
        lbl_status = new javax.swing.JLabel();
        lb_fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Busca - SysBusca");
        setResizable(false);
        getContentPane().setLayout(null);

        txt_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuariosActionPerformed(evt);
            }
        });
        getContentPane().add(txt_usuarios);
        txt_usuarios.setBounds(180, 110, 333, 40);

        txt_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_senhaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_senha);
        txt_senha.setBounds(180, 180, 333, 40);

        btn_Login.setFont(new java.awt.Font("Verdana", 2, 24)); // NOI18N
        btn_Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/cadeado icone 40x40.png"))); // NOI18N
        btn_Login.setText("Login");
        btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Login);
        btn_Login.setBounds(290, 230, 223, 60);

        lbl_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sysbusca/icones/dberro.png"))); // NOI18N
        getContentPane().add(lbl_status);
        lbl_status.setBounds(20, 10, 70, 50);

        lb_fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sysbusca/img/login.jpg"))); // NOI18N
        getContentPane().add(lb_fundo);
        lb_fundo.setBounds(0, 0, 540, 310);

        setSize(new java.awt.Dimension(540, 325));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuariosActionPerformed

    private void txt_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_senhaActionPerformed

    private void btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginActionPerformed
        // TODO add your handling code here:
        logar();
    }//GEN-LAST:event_btn_LoginActionPerformed

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Login;
    private javax.swing.JLabel lb_fundo;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JTextField txt_usuarios;
    // End of variables declaration//GEN-END:variables
}
