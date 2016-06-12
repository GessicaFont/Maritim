/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.DAO;

import MODEL.Configurador;
import MODEL.Agente;
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
public class AgenteDAO {
    public Configurador config;
    
    public AgenteDAO(){ 
           config = new Configurador();
    }
    
    /**
    *
    * Instancia uma agente a partir dos dados de entrada e o retorna
    */
    public Agente montaAgente(ResultSet rs) throws SQLException{
        Agente agente = new Agente();
        agente.setCod_Agente(rs.getInt("Cod_Agente"));
        agente.setId_Porto(rs.getInt("id_Porto"));
        agente.setNome(rs.getString("nome"));
        agente.setTelefone(rs.getString("fone"));
        
        return agente;
    }
 
    /**
    *
    * Faz consulta ao banco e retorna um Agente de acordo com o id informado
    */    
   
    public Agente getAgente(int id){
        Agente agente = null;
        Connection conn = null;
        try{
            conn = config.conectar();
            if (conn == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Agente_Receptor WHERE Cod_Agente = ?");
            ps.setInt(1, id); 
            ResultSet rs = ps.executeQuery();
                
            if(rs.next()){
                agente = montaAgente(rs);
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return agente;
    }
    
    /**
    *
    * Através de consulta ao banco, recupera a lista que contém todos os agentes da tabela Agente
    */
   
    public List<Agente> getListaAgentes(){
        List<Agente> listaAgentes = new ArrayList<Agente>();
        
        try{
            Connection conn = config.conectar();
            if (config == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Agente_Receptor"); 
            ResultSet rs = ps.executeQuery();
                
            while(rs.next()){
                listaAgentes.add(montaAgente(rs));
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listaAgentes;
    }
    
    
    public boolean insereAgente(Agente agente){
        try(Connection conn = config.conectar()){
             
            //CallableStatement cs = conn.prepareCall("{call InserirAgente(?,?,?,?,?)}");
            String sql="insert into Agente_Receptor (Cod_Agente,id-Porto,nome,fone) values (?,?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, agente.getCod_Agente());
            cs.setInt(1, agente.getId_Porto());
            cs.setString(2, agente.getNome());
            cs.setString(3, agente.getTelefone());
          
        
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }
    
    
    
    public boolean atualizaAgente(Agente agente){
        try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            String sql="insert into Agente_Receptor (Cod_Agente,Id_Porto,nome,telefone) values (?,?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, agente.getCod_Agente());
                        cs.setInt(1, agente.getId_Porto());

            cs.setString(2, agente.getNome());
            cs.setString(3, agente.getTelefone());
           
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
    *
    * Executa a Query que remove agente correspondente ao id de entrada do método. 
     * @param id
    */

    public boolean removeAgente(int id){
        
         try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Agente_Receptor WHERE id=?"); 
            ps.setInt(1, id);
            ps.executeQuery();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
         return true;
    }
    
}
