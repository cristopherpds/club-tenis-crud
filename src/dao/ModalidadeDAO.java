package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Modalidade;

public class ModalidadeDAO {
  public void save(Modalidade modalidade) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO modalidade (modalidade_nome, modalidade_vagas)VALUES (?, ?),";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setString(0, modalidade.getModalidade_nome());
      pstm.setInt(2, modalidade.getModalidade_vagas());

      pstm.execute();
      System.out.println("Modalidade adicionado com succeso!");
    } catch (SQLException e) {
      System.out.println("Error al insertar el socio en la base de datos: " + e.getMessage());
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

  public void updateModalidade(Modalidade modalidade) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "UPDATE modalidade SET modalidade_nome = ?, modalidade_vagas = ? WHERE idmodalidade = ? ";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, modalidade.getModalidade_nome());
      pstm.setInt(2, modalidade.getModalidade_vagas());

      pstm.setInt(2, modalidade.getIdmodalidade());

      pstm.execute();

      System.out.println("Modalidade actualizada com sucesso!");

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

  public List<Modalidade> findModalidade() {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT * FROM modalidade LIMIT 5";
    List<Modalidade> modalidades = new ArrayList<Modalidade>();

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        Modalidade modalidade = new Modalidade();
        modalidade.setIdmodalidade(rst.getInt("idmodalidade"));
        modalidade.setModalidade_nome(rst.getString("modalidade_nome"));
        modalidade.setModalidade_vagas(rst.getInt("modalidade_vaga"));

        modalidades.add(modalidade);
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
    return modalidades;
  }

  public void deleteByID(int idmodalidade) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "DELETE FROM modalidade WHERE idmodalidade = ?";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, idmodalidade);

      pstm.execute();

      System.out.println("Modalidade datetada com sucesso!");
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
