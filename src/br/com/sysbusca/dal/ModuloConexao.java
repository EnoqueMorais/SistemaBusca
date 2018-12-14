/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysbusca.dal;
import java.sql.*; //Importa todas as bibliotecas do sql

/**
 *
 * @author EnOqUe
 */
public class ModuloConexao {
     public static Connection conector() {
        /*classe estatica cda classe connection 
        chamada de conector*/
        java.sql.Connection conexao = null;/*criamos um objeto da classe connection
        chamado de conexão e iniciando como nulo
         */
        //chamar driver
        String driver = "com.mysql.jdbc.Driver";
        // a String driver carrega o caminho do driver
        
        //Informações referente ao BD local
        //String url ="jdbc:mysql://127.0.0.1:3306/bdinfoq";
        
        String url = "jdbc:mysql://localhost:3306/sysbusca";
        // a String url carrega o caminho local do BD e nome do BD
        String user = "root";//o usuario local do xamp é root
        String password = ""; //não tem senha 
        
        /*
         //Informações referente ao BD hospedado
        String url = "jdbc:mysql://databases-auth.000webhost.com:3306/id7922287_bdinfoq";
        String user = "id7922287_enoque";//o usuario local do xamp é root
        String password = "enoque123"; //não tem senha */
        
        /*Utlizamos o try caso ocorra alguma adiversidade não esperada e para é necessário 
        que o sistema retorne alguma coisa. Possibilidade de algo não acontecer,exemplo cadastro, exclusão 
        consultar e etc.*/
        try {//Irá utiliza a classe com um forName e passaremos como parametro o driver que é jdbc
            Class.forName(driver);
            /* a varialvel conexão vai receber a configuração do DriverManager e vai pegar meio
            do getConnection a url, user e password*/
            conexao = DriverManager.getConnection(url,user,password);
            return conexao; //se tudo ocorre certo é retornado a conexão
        } catch (Exception e) { //e caso der erro não retorna nada
            //Caso ocorra algum erro eu poso retornar uma mensagem a meu querer
            System.out.println(e);
            return null; //neste caso não retorna nada. 
            
            //Temos que chamar a moduloconexão no frmLogin, se não tem como ter acesso ao BD
        }
    }
}

