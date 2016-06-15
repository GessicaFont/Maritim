/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.DAO;

import MODEL.Configurador;
import MODEL.Porto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gessica
 */
public class PortoDAO {
     public Configurador config;
    
    public PortoDAO(){ 
           config = new Configurador();
    }
    
    /**
    *
    * Instancia uma porto a partir dos dados de entrada e o retorna
    */
    public Porto montaPorto(ResultSet rs) throws SQLException{
        Porto porto = new Porto();
        porto.setId_Porto(rs.getInt("id_Porto"));
        
        porto.setNome(rs.getString("nome"));
        porto.setLocal(rs.getString("local"));          
        
        
        return porto;
    }
 
    /**
    *
    * Faz consulta ao banco e retorna um Porto de acordo com o id informado
    */    
   
    public Porto getPorto(int id){
        Porto porto = null;
        Connection conn = null;
        try{
            conn = config.conectar();
            if (conn == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Porto WHERE Id_Porto = ?");
            ps.setInt(1, id); 
            ResultSet rs = ps.executeQuery();
                
            if(rs.next()){
                porto = montaPorto(rs);
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return porto;
    }
    
    /**
    *
    * Através de consulta ao banco, recupera a lista que contém todos os portos da tabela Porto
    */
   
    public List<Porto> getListaPortos(){
        List<Porto> listaPortos = new ArrayList<Porto>();
        
        try{
            Connection conn = config.conectar();
            if (config == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Porto"); 
            ResultSet rs = ps.executeQuery();
                
            while(rs.next()){
                listaPortos.add(montaPorto(rs));
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listaPortos;
    }
    
    
    public boolean inserePorto(Porto porto){
        try(Connection conn = config.conectar()){
             
            //CallableStatement cs = conn.prepareCall("{call InserirPorto(?,?,?,?,?)}");
            String sql="insert into Porto (nome,local) values (?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);            
            cs.setString(1, porto.getNome());
            cs.setString(2, porto.getLocal());           
               
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }
    
    
    
    public boolean atualizaPorto(Porto porto){
        try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            String sql="insert into Porto (id_Porto,nome,local) values (?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, porto.getId_Porto());
            
            cs.setString(2, porto.getNome());
            cs.setString(3, porto.getLocal());
            
       
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
    *
    * Executa a Query que remove porto correspondente ao id de entrada do método. 
     * @param id
    */

    public boolean removePorto(int id){
        
         try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Porto WHERE id_porto=?"); 
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
         return true;
    }
    
}
