package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.Reserva;

public class ReservaDAO {

  public void save(Reserva reserva) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO reserva (res_data, "
        + "recinto_desportivo_idrecinto_desportivo, socio_idsocio, pagamento_idpagamento)"
        + "VALUES (?, ?, ?, ?)";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setDate(1, reserva.getResData());
      pstm.setInt(2, reserva.getRecintoDesportivoId());
      pstm.setInt(3, reserva.getSocioId());
      pstm.setInt(4, reserva.getPagamentoId());

      pstm.execute();
      System.out.println("Reserva adicionada com sucesso!");
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

  public List<Reserva> findReserva() {

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT * FROM reserva";
    List<Reserva> reservas = new ArrayList<Reserva>();

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(rst.getInt("idreserva"));
        reserva.setResData(rst.getDate("res_data"));
        reserva.setRecintoDesportivoId(rst.getInt("recinto_desportivo_idrecinto_desportivo"));
        reserva.setSocioId(rst.getInt("socio_idsocio"));
        reserva.setPagamentoId(rst.getInt("pagamento_idpagamento"));

        reservas.add(reserva);
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
    return reservas;
  }

  public void updateReserva(Reserva reserva) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "UPDATE reserva SET  res_data = ?, recinto_desportivo_idrecinto_desportivo = ?, socio_idsocio = ?, pagamento_idpagamento = ? WHERE idreserva = ?";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setDate(1, reserva.getResData());
      pstm.setInt(2, reserva.getRecintoDesportivoId());
      pstm.setInt(3, reserva.getSocioId());
      pstm.setInt(4, reserva.getPagamentoId());

      pstm.setInt(5, reserva.getIdReserva());

      pstm.execute();
      System.out.println("Reserva actualizada com suucesso!");
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

  public void deletById(int idreserva) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "DELETE FROM reserva WHERE idreserva = ?;";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, idreserva);
      pstm.execute();
      System.out.println("Reserva deletada com sucesso!");
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
}
