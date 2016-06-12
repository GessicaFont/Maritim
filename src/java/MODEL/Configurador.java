/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.Data;

/**
 *
 * @author gessica
 */
public @Data
class Configurador {
///private String url,driver,login,senha;

    String databaseURL = "jdbc:postgresql://localhost:5432/Maritima";
    String usuario = "postgres";
    String senha = "1234";
    String driverName = "org.postgresql.Driver";
    //Connection conn;

    public Configurador() {
        databaseURL = "jdbc:postgresql://localhost:5432/Maritima";
        usuario = "postgres";
        senha = "1234";
        driverName = "org.postgresql.Driver";
    }

    public Connection conectar() throws SQLException {
        Connection conexao;

        try {
            Class.forName(driverName).newInstance();
            conexao = DriverManager.getConnection(databaseURL, usuario, senha);
            return conexao;

        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - erro de SQL");
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - driver não encontrado");
            return null;
        } catch (InstantiationException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - erro de instanciação do driver");
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("Não foi possível estabelecer uma conexão com o banco de dados - acesso ilegal no driver");
            return null;
        }

    }
}

//    public Configurador(){
//        try {  
//  
//            String url = "jdbc:postgresql://localhost:5432/pcs-sgbd";  
//            String usuario = "postgres";  
//            String senha = "123456";  
//  
//            Class.forName("org.postgresql.Driver");  
//  
//            Connection con;  
//  
//            con = DriverManager.getConnection(url, usuario, senha);  
//  con.setCatalog("pcs-sgbd");
//            System.out.println("Conexão realizada com sucesso.");  
//  
//            Statement stm = con.createStatement();  
//  
//           // stm.executeQuery("INSERT INTO teste VALUES (1,'Cynthia')");  
//  
//          //  stm.executeUpdate("INSERT INTO teste VALUES (1,'Cynthia')");  
//            //Editado 21/09/2011 para correção: executeQuery é usado para pesquisa, executeUpdate deve ser usado para inserir  
//            con.close();  
//  
//        } catch (Exception e) {  
//             e.printStackTrace();  
//        }  
//    }  
//    }

