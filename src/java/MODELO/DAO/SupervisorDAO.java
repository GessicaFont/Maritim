/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.DAO;

import MODEL.Configurador;
import MODEL.Supervisor;
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
public class SupervisorDAO {
     public Configurador config;
    
    public SupervisorDAO(){ 
           config = new Configurador();
    }
    
    /**
    *
    * Instancia uma supervisor a partir dos dados de entrada e o retorna
    */
    public Supervisor montaSupervisor(ResultSet rs) throws SQLException{
        Supervisor supervisor = new Supervisor();
        supervisor.setId_Supervisor(rs.getInt("id_Supervisor"));
        
        supervisor.setNome(rs.getString("nome"));
        supervisor.setEmail(rs.getString("email"));
        supervisor.setEmail(rs.getString("senha"));
     
        
        
        
        return supervisor;
    }
 
    /**
    *
    * Faz consulta ao banco e retorna um Supervisor de acordo com o id informado
    */    
   
    public Supervisor getSupervisor(int id){
        Supervisor supervisor = null;
        Connection conn = null;
        try{
            conn = config.conectar();
            if (conn == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Supervisor WHERE Id_Supervisor = ?");
            ps.setInt(1, id); 
            ResultSet rs = ps.executeQuery();
                
            if(rs.next()){
                supervisor = montaSupervisor(rs);
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return supervisor;
    }
    
    /**
    *
    * Através de consulta ao banco, recupera a lista que contém todos os supervisors da tabela Supervisor
    */
   
    public List<Supervisor> getListaSupervisors(){
        List<Supervisor> listaSupervisors = new ArrayList<Supervisor>();
        
        try{
            Connection conn = config.conectar();
            if (config == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Supervisor"); 
            ResultSet rs = ps.executeQuery();
                
            while(rs.next()){
                listaSupervisors.add(montaSupervisor(rs));
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listaSupervisors;
    }
    
    
    public boolean insereSupervisor(Supervisor supervisor){
        try(Connection conn = config.conectar()){
             
            //CallableStatement cs = conn.prepareCall("{call InserirSupervisor(?,?,?,?,?)}");
            String sql="insert into Supervisor (id_Supervisor,nome,email,senha) values (?,?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, supervisor.getId_Supervisor());
            
            cs.setString(2, supervisor.getNome());
            cs.setString(3, supervisor.getEmail());
            cs.setString(3, supervisor.getSenha());
       
        
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }
    
    
    
    public boolean atualizaSupervisor(Supervisor supervisor){
        try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            String sql="insert into Supervisor (id_Supervisor,nome,email,senha) values (?,?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, supervisor.getId_Supervisor());
            
            cs.setString(2, supervisor.getNome());
            cs.setString(3, supervisor.getEmail());
            cs.setString(3, supervisor.getSenha());
       
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
    *
    * Executa a Query que remove supervisor correspondente ao id de entrada do método. 
     * @param id
    */

    public boolean removeSupervisor(int id){
        
         try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Supervisor WHERE id=?"); 
            ps.setInt(1, id);
            ps.executeQuery();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
         return true;
    }
    
}
