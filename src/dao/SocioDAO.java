package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import models.InscreveSe;
import models.Modalidade;
import models.Socio;

public class SocioDAO {

  public void inscribirModalidade(Socio socio, InscreveSe inscricao, Modalidade modalidade) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO inscreve_se(socio_idsocio, modalidade_idmodalidade, pagamento_idpagamento) VALUES (?, ?, ?)";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, socio.getIdsocio());
      pstm.setInt(2, modalidade.getIdmodalidade());
      pstm.setInt(3, inscricao.getPagamento_idpagamento());

      pstm.execute();
    } catch (SQLException e) {
      e.printStackTrace();
      e.getErrorCode();
    }
  }

  public List<Socio> findSociosInModalidade() {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT i.idinscreve_se, s.socio_nome, m.modalidade_nome " +
        "FROM inscreve_se i " +
        "JOIN socio s ON i.socio_idsocio = s.idsocio " +
        "JOIN modalidade m ON i.modalidade_idmodalidade = m.idmodalidade;";
    List<Socio> sociosInModalidad = new ArrayList<Socio>();

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        Socio socio = new Socio();
        Modalidade modalidad = new Modalidade();
        InscreveSe inscricao = new InscreveSe();

        inscricao.setIdinscreve_se(rst.getInt("i.idinscreve_se"));
        socio.setSocio_nome(rst.getString("s.socio_nome"));
        modalidad.setModalidade_nome(rst.getString("m.modalidade_nome"));
        socio.setModalidade(modalidad);
        socio.setInscreveSe(inscricao);

        sociosInModalidad.add(socio);
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
    return sociosInModalidad;
  }

  public void save(Socio socio) {

    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "INSERT INTO socio(socio_nome, socio_data_admissao, socio_num_identidade,"
        + "socio_data_nacimento, socio_telefone, socio_endereco_rua, socio_endereco_cidade,"
        + "socio_endereco_codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, socio.getSocio_nome());
      pstm.setDate(2, new Date(socio.getSocio_data_admissao().getTime()));
      pstm.setString(3, socio.getSocio_num_identidade());
      pstm.setDate(4, new Date(socio.getSocio_data_nacimento().getTime()));
      pstm.setString(5, socio.getSocio_telefone());
      pstm.setString(6, socio.getSocio_endereco_rua());
      pstm.setString(7, socio.getSocio_endereco_cidade());
      pstm.setString(8, socio.getSocio_endereco_codigo_postal());

      pstm.execute();
      System.out.println("Socio adicionado com succeso!");
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

  public void updateSocio(Socio socio) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "UPDATE socio SET socio_nome = ?  , socio_num_identidade = ?, socio_data_nacimento = ?, socio_telefone = ?, socio_endereco_rua = ?, socio_endereco_cidade = ?, socio_endereco_codigo_postal = ? WHERE idsocio =?";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setString(1, socio.getSocio_nome());
      pstm.setString(2, socio.getSocio_num_identidade());
      pstm.setDate(3, new Date(socio.getSocio_data_nacimento().getTime()));
      pstm.setString(4, socio.getSocio_telefone());
      pstm.setString(5, socio.getSocio_endereco_rua());
      pstm.setString(6, socio.getSocio_endereco_cidade());
      pstm.setString(7, socio.getSocio_endereco_codigo_postal());

      pstm.setInt(8, socio.getIdsocio());

      pstm.execute();

      System.out.println("Socio atualizado com sucesso!");
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

  public List<Socio> findSocios() {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;
    String sql = "SELECT * FROM socio LIMIT 2";
    List<Socio> socios = new ArrayList<Socio>();

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      rst = pstm.executeQuery();

      while (rst.next()) {
        Socio socio = new Socio();
        socio.setIdsocio(rst.getInt("idsocio"));
        socio.setSocio_nome(rst.getString("socio_nome"));
        socio.setSocio_data_admissao(rst.getDate("socio_data_admissao"));
        socio.setSocio_num_identidade(rst.getString("socio_num_identidade"));
        socio.setSocio_data_nacimento(rst.getDate("socio_data_nacimento"));
        socio.setSocio_telefone(rst.getString("socio_telefone"));
        socio.setSocio_endereco_rua(rst.getString("socio_endereco_rua"));
        socio.setSocio_endereco_cidade(rst.getString("socio_endereco_cidade"));
        socio.setSocio_endereco_codigo_postal(rst.getString("socio_endereco_codigo_postal"));

        socios.add(socio);
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
    return socios;
  }

  public void deleteByID(int idsocio) {
    Connection conn = null;
    PreparedStatement pstm = null;
    String sql = "DELETE FROM socio WHERE idsocio = ?;";

    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, idsocio);
      pstm.execute();
      System.out.println("Socio deletado com sucesso!");
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
