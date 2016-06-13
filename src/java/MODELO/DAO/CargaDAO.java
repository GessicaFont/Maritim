/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.DAO;
import MODEL.Carga;
import MODEL.Configurador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import java.sql.Date;

/**
 *
 * @author gessica
 */
public class CargaDAO {
    public Configurador config;
    
    public CargaDAO(){ 
           config = new Configurador();
    }
    
    /**
    *
    * Instancia uma carga a partir dos dados de entrada e o retorna
    */
    public Carga montaCarga(ResultSet rs) throws SQLException{
        Carga carga = new Carga();
        carga.setId_Carga(rs.getInt("id_carga"));
        carga.setId_Navio(rs.getInt("id_navio"));
        carga.setOrigem(rs.getString("origem"));
        carga.setDestino(rs.getString("destino"));
        carga.setPeso(rs.getDouble("peso"));
        carga.setData_Max(DateTime.parse(rs.getString("data_max")));
        carga.setEmbarcada(rs.getBoolean("embarcada"));
        carga.setTipo(rs.getInt("tipo"));
        carga.setTemp_Max(rs.getDouble("temp_max"));
        carga.setData_Validade(DateTime.parse(rs.getString("data_validade")));
        
        
        
        return carga;
    }
 
    /**
    *
    * Faz consulta ao banco e retorna um Carga de acordo com o id informado
    */    
   
    public Carga getCarga(int id){
        Carga carga = null;
        Connection conn = null;
        try{
            conn = config.conectar();
            if (conn == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Carga WHERE Id_Carga = ?");
            ps.setInt(1, id); 
            ResultSet rs = ps.executeQuery();
                
            if(rs.next()){
                carga = montaCarga(rs);
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return carga;
    }
    
    /**
    *
    * Através de consulta ao banco, recupera a lista que contém todos os cargas da tabela Carga
    */
   
    public List<Carga> getListaCargas(){
        List<Carga> listaCargas = new ArrayList<Carga>();
        
        try{
            Connection conn = config.conectar();
            if (config == null){
                return null;
            }
        
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Carga"); 
            ResultSet rs = ps.executeQuery();
                
            while(rs.next()){
                listaCargas.add(montaCarga(rs));
            }
            conn.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listaCargas;
    }
    
    /**
    *
    * Objeto Carga é inserido no banco através de uma Stored Procedure
     * @param carga
    */
    
    public boolean insereCarga(Carga carga){
        try(Connection conn = config.conectar()){
             
            Date sqlData_Validade = new Date(carga.getData_Validade().toDate().getTime());
            Date sqlData_Max = new Date(carga.getData_Max().toDate().getTime());
            
            //CallableStatement cs = conn.prepareCall("{call InserirCarga(?,?,?,?,?)}");
            String sql="insert into Carga (id_navio,origem,destino,peso,data_max,embarcada,tipo,temp_max,data_validade) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, carga.getId_Navio());
            cs.setString(2, carga.getOrigem());
            cs.setString(3, carga.getDestino());
            cs.setDouble(4, carga.getPeso());
            cs.setDate(5, sqlData_Max);
            cs.setBoolean(6,carga.isEmbarcada());
            cs.setInt(7, carga.getTipo());
            cs.setDouble(8, carga.getTemp_Max());
            cs.setDate(9, sqlData_Validade);
       
        
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }
    
    /**
    *
    * Carga é alterado no banco através de uma Stored Procedure
     * @param carga
     *  
    */
    
    public boolean atualizaCarga(Carga carga){
        try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            String sql="insert into Carga (id_Carga,id_Navio,origem,destino,peso,data,embarcada,tipo,temp,data_validade) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, carga.getId_Carga());
            cs.setInt(2, carga.getId_Navio());
            cs.setString(3, carga.getOrigem());
            cs.setString(4, carga.getDestino());
            cs.setDouble(5, carga.getPeso());
        //    cs.setBoolean(6,carga.getEmbarcada());
        cs.setInt(6, carga.getTipo());
        //cs.setDouble(7, carga.getTemp_Max());
            cs.execute();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
    *
    * Executa a Query que remove carga correspondente ao id de entrada do método. 
     * @param id
    */

    public boolean removeCarga(int id){
        
         try(Connection conn = config.conectar()){
            if (conn == null){
                return false;
            }
        
            PreparedStatement ps = conn.prepareStatement("DELETE FROM carga WHERE id_carga=?"); 
            ps.setInt(1, id);
            ps.executeQuery();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
         return true;
    }
    
}
