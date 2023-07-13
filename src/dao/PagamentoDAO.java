package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Pagamento;

public class PagamentoDAO {

  public void save(Pagamento pagamento) {

    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO  pagamento(pagamento_data, pagamento_valor) VALUES(?,?)";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setTimestamp(1, new Timestamp(pagamento.getPagamento_data().getTime()));
      pstm.setBigDecimal(2, pagamento.getPagamento_valor());

      pstm.execute();
      System.out.println("Pagamento realizado com succeso!");
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

  public void updatePagamento(Pagamento p) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "UPDATE pagamento SET pagamento_date = ?, pagamento_valor = ? WHERE idpagamento = ?";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setTimestamp(1, p.getPagamento_data());
      pstm.setBigDecimal(2, p.getPagamento_valor());

      pstm.setInt(3, p.getIdpagamento());
      pstm.execute();

      System.out.println("Pagamento atualizado com sucesso!");
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

  public List<Pagamento> findPagamentos() {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT * FROM pagamento";
    List<Pagamento> pagamentos = new ArrayList<Pagamento>();
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        Pagamento p = new Pagamento();
        p.setIdpagamento(rst.getInt("idpagamento"));
        p.setPagamento_data(rst.getTimestamp("pagamento_data"));
        p.setPagamento_valor(rst.getBigDecimal("pagamento_valor"));

        pagamentos.add(p);
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
    return pagamentos;
  }

  public void deleteById(int idpagamento) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "DELETE FROM pagamento WHERE idpagamento = ?";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, idpagamento);

      pstm.execute();
      System.out.println("Pagamento deletado com sucesso!");
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
