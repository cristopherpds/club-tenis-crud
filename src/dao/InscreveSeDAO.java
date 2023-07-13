package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.InscreveSe;

public class InscreveSeDAO {

  public void save(InscreveSe inscreveSe) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO inscreve_se(socio_idsocio, modalidade_idmodalidade, pagamento_idpagamento) VALUES (?,?,?)";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, inscreveSe.getSocio_idsocio());
      pstm.setInt(2, inscreveSe.getModalidade_idmodalidade());
      pstm.setInt(3, inscreveSe.getPagamento_idpagamento());

      pstm.execute();
      System.out.println("Inscricao realizada com succeso!");
    } catch (SQLException e) {
      System.out.println("Error al insertar  en la base de datos: " + e.getMessage());
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

  public void updateInscreveSe(InscreveSe inscreveSe) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "UPDADE inscreve_se SET socio_idsocio = ?, modalidade_idmodalidade = ? , pagamento_idpagamento = ? WHERE idinscreve_se = ?";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, inscreveSe.getSocio_idsocio());
      pstm.setInt(2, inscreveSe.getModalidade_idmodalidade());
      pstm.setInt(3, inscreveSe.getPagamento_idpagamento());

      pstm.setInt(4, inscreveSe.getIdinscreve_se());

      pstm.execute();
      System.out.println("Inscicao atualizado com sucesso!");
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

  public List<InscreveSe> findInscreveSe() {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT * FROM	inscreve_se";
    List<InscreveSe> inscritos = new ArrayList<InscreveSe>();

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        InscreveSe inscrito = new InscreveSe();
        inscrito.setSocio_idsocio(rst.getInt("socio_idsocio"));
        inscrito.setModalidade_idmodalidade(rst.getInt("modalidade_idmodalidade"));
        inscrito.setPagamento_idpagamento(rst.getInt("pagamento_idpagamento"));

        inscritos.add(inscrito);
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
    return inscritos;
  }

  public void deleteByID(int idinscreve_se) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "DELETE FROM inscreve_se WHERE idinscreve_se = ? ;";
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.execute();
      System.out.println("Inscricao deletado com sucesso!");
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
