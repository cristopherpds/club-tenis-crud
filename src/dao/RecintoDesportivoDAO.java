package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.RecintoDesportivo;

public class RecintoDesportivoDAO {

  public void save(RecintoDesportivo recintoDesportivo) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO recinto_desportivo(recinto_desportivo_nome) VALUES(?)";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, recintoDesportivo.getRecinto_desportivo_nome());
      pstm.execute();
      System.out.println("Recinto Desportivo adicionado com sucesso");
    } catch (SQLException e) {
      System.out.println("Error al insertar el recinto desportivo en la base de datos: " + e.getMessage());
      e.printStackTrace();
      e.getErrorCode();
    } finally {
      try {
        if (pstm != null) {
          pstm.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
        e.getErrorCode();
      }
    }
  }

  public void uRecintoDesportivo(RecintoDesportivo recintoDesportivo) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "UPDATE recinto_desportivo SET recinto_desportivo_nome = ? WHERE idrecinto_desportivo = ?";
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, recintoDesportivo.getRecinto_desportivo_nome());
      pstm.setInt(2, recintoDesportivo.getIdrecinto_desportivo());
      pstm.execute();

      System.out.println("Recinto desportivo atualizado com sucesso!");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstm != null) {
          pstm.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public List<RecintoDesportivo> fRecintoDesportivos() {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT * FROM recinto_desportivo";
    List<RecintoDesportivo> recintos = new ArrayList<RecintoDesportivo>();

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        RecintoDesportivo recinto = new RecintoDesportivo();
        recinto.setIdrecinto_desportivo(rst.getInt("idrecinto_desportivo"));
        recinto.setRecinto_desportivo_nome(rst.getString("recinto_desportivo_nome"));
        
        recintos.add(recinto);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      e.getErrorCode();
    } finally {
      try {
        if (rst != null) {
          rst.close();
        }
        if (pstm != null) {
          pstm.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return recintos;
  }

  public void deleteByID(int idrecinto_desportivo) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "DELETE FROM recinto_desportivo WHERE idrecinto_desportivo = ?;";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, idrecinto_desportivo);
      pstm.execute();
      System.out.println("Recinto desportivo deletado com sucesso!");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstm != null) {
          pstm.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
