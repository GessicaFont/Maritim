/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.DAO;

import MODEL.Configurador;
import MODEL.Rota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO {

    public Configurador config;

    public RotaDAO() {
        config = new Configurador();
    }

    /**
     *
     * Instancia uma rota a partir dos dados de entrada e o retorna
     */
    public Rota montaRota(ResultSet rs) throws SQLException {
        Rota rota = new Rota();
        rota.setId_Rota(rs.getInt("id_rota"));

        rota.setId_Porto_Origem(rs.getInt("id_porto_origem"));
        rota.setId_Porto_Destino(rs.getInt("id_porto_destino"));

        return rota;
    }

    /**
     *
     * Faz consulta ao banco e retorna um Porto de acordo com o id informado
     */
    public Rota getPorto(int id) {
        Rota rota = null;
        Connection conn = null;
        try {
            conn = config.conectar();
            if (conn == null) {
                return null;
            }

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rota WHERE id_rota = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rota = montaRota(rs);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rota;
    }

    /**
     *
     * Através de consulta ao banco, recupera a lista que contém todos os portos
     * da tabela Porto
     */
    public List<Rota> getListaRotas() {
        List<Rota> listaRotas = new ArrayList<Rota>();

        try {
            Connection conn = config.conectar();
            if (config == null) {
                return null;
            }

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM rota");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listaRotas.add(montaRota(rs));
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaRotas;
    }

    public boolean insereRota(Rota rota) {
        try (Connection conn = config.conectar()) {

            //CallableStatement cs = conn.prepareCall("{call InserirPorto(?,?,?,?,?)}");
            String sql = "insert into rota (id_porto_origem,id_porto_destino) values (?,?)";
            PreparedStatement cs = conn.prepareStatement(sql);
            cs.setInt(1, rota.getId_Porto_Origem());
            cs.setInt(2, rota.getId_Porto_Destino());

            cs.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean atualizaRota(Rota rota) {
        try (Connection conn = config.conectar()) {
            if (conn == null) {
                return false;
            }

            String sql = "update rota set id_porto_origem = ? id_porto_destino = ? where id_rota = ?";
            PreparedStatement cs = conn.prepareStatement(sql);

            cs.setInt(1, rota.getId_Porto_Origem());
            cs.setInt(2, rota.getId_Porto_Destino());
            cs.setInt(3, rota.getId_Rota());

            cs.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * Executa a Query que remove rota correspondente ao id de entrada do
     * método.
     *
     * @param id
     */
    public boolean removeRota(int id) {

        try (Connection conn = config.conectar()) {
            if (conn == null) {
                return false;
            }

            PreparedStatement ps = conn.prepareStatement("DELETE FROM rota WHERE id_rota=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
