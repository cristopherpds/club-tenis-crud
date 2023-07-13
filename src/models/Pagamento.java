package models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pagamento {
  private int idpagamento; 
  private Timestamp pagamento_data;
  private BigDecimal pagamento_valor;
  
  public int getIdpagamento() {
    return idpagamento;
  }
  public void setIdpagamento(int idpagamento) {
    this.idpagamento = idpagamento;
  }
  public Timestamp getPagamento_data() {
    return pagamento_data;
  }
  public void setPagamento_data(Timestamp pagamento_data) {
    this.pagamento_data = pagamento_data;
  }
  public BigDecimal getPagamento_valor() {
    return pagamento_valor;
  }
  public void setPagamento_valor(BigDecimal pagamento_valor) {
    this.pagamento_valor = pagamento_valor;
  } 
}
