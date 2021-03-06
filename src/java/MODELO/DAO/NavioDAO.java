/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.DAO;

import MODEL.Navio;
import MODEL.Configurador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author gessica
 */
public class NavioDAO {

    public Configurador config;

    public NavioDAO() {
        config = new Configurador();
    }

    /**
     *
     * Instancia uma navio a partir dos dados de entrada e o retorna
     */
    public Navio montaNavio(ResultSet rs) throws SQLException {
        Navio navio = new Navio();
        navio.setId_Navio(rs.getInt("id_Navio"));

        navio.setNome(rs.getString("nome"));
        navio.setStatus(rs.getString("status"));
        navio.setCap_Maxima(rs.getDouble("cap_maxima"));

        return navio;
    }

    /**
     *
     * Faz consulta ao banco e retorna um Navio de acordo com o id informado
     */
    public Navio getNavio(int id) {
        Navio navio = null;
        Connection conn = null;
        try {
            conn = config.conectar();
            
            if (conn == null) {
                return null;
            }

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM navio WHERE id_navio = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                navio = montaNavio(rs);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return navio;
    }

    /**
     *
     * Através de consulta ao banco, recupera a lista que contém todos os navios
     * da tabela Navio
     */
    public List<Navio> getListaNavios() {
        List<Navio> listaNavios = new ArrayList<Navio>();

        try {
            Connection conn = config.conectar();
            if (conn == null) {
                return null;
            }
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from navio");

            while (rs.next()) {
                listaNavios.add(montaNavio(rs));
            }
            
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaNavios;
    }

    /**
     *
     * Objeto Navio é inserido no banco através de uma Stored Procedure
     *
     * @param navio
     */
    public boolean insereNavio(Navio navio) {
        try (Connection conn = config.conectar()) {

            //CallableStatement cs = conn.prepareCall("{call InserirNavio(?,?,?,?,?)}");
            String sql = "insert into navio (nome,status,cap_maxima) values (?,?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setString(1, navio.getNome());
            cs.setString(2, navio.getStatus());
            cs.setDouble(3, navio.getCap_Maxima());

            cs.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * Navio é alterado no banco através de uma Stored Procedure
     *
     * @param navio
     *
     */
    public boolean atualizaNavio(Navio navio) {
        try (Connection conn = config.conectar()) {
            if (conn == null) {
                return false;
            }

            String sql = "update navio set nome = ?, status = ?, cap_maxima = ? where id_navio = ?";
            PreparedStatement cs = conn.prepareStatement(sql);           

            cs.setString(1, navio.getNome());
            cs.setString(2, navio.getStatus());
            cs.setDouble(3, navio.getCap_Maxima());
             cs.setInt(4, navio.getId_Navio());

            cs.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * Executa a Query que remove navio correspondente ao id de entrada do
     * método.
     *
     * @param id
     */
    public boolean removeNavio(int id) {

        try (Connection conn = config.conectar()) {
            if (conn == null) {
                return false;
            }

            PreparedStatement ps = conn.prepareStatement("DELETE FROM navio WHERE id_navio=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
