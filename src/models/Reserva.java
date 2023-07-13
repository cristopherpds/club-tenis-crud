package models;

import java.sql.Date;


public class Reserva {
    private int idreserva;
    private Date resData;
    private int recintoDesportivoId;
    private int socioId;
    private int pagamentoId;

    public int getIdReserva() {
      return idreserva;
    }
    public void setIdReserva(int idreserva) {
      this.idreserva = idreserva;
    }
    public Date getResData() {
      return resData;
    }
    public void setResData(Date resData) {
      this.resData = resData;
    }
    public int getRecintoDesportivoId() {
      return recintoDesportivoId;
    }
    public void setRecintoDesportivoId(int recintoDesportivoId) {
      this.recintoDesportivoId = recintoDesportivoId;
    }
    public int getSocioId() {
      return socioId;
    }
    public void setSocioId(int socioId) {
      this.socioId = socioId;
    }
    public int getPagamentoId() {
      return pagamentoId;
    }
    public void setPagamentoId(int pagamentoId) {
      this.pagamentoId = pagamentoId;
    }
}
