package models;

public class InscreveSe {
  int idinscreve_se;
  int socio_idsocio;
  int modalidade_idmodalidade;
  int pagamento_idpagamento;
  private Modalidade modalidade;

  
  public int getIdinscreve_se() {
    return idinscreve_se;
  }
  public void setIdinscreve_se(int idinscreve_se) {
    this.idinscreve_se = idinscreve_se;
  }
  public int getSocio_idsocio() {
    return socio_idsocio;
  }
  public void setSocio_idsocio(int socio_idsocio) {
    this.socio_idsocio = socio_idsocio;
  }
  public int getModalidade_idmodalidade() {
    return modalidade_idmodalidade;
  }
  public void setModalidade_idmodalidade(int modalidade_idmodalidade) {
    this.modalidade_idmodalidade = modalidade_idmodalidade;
  }
  public int getPagamento_idpagamento() {
    return pagamento_idpagamento;
  }
  public void setPagamento_idpagamento(int pagamento_idpagamento) {
    this.pagamento_idpagamento = pagamento_idpagamento;
  }
  public Modalidade getModalidade() {
    return modalidade;
  }
  public void setModalidade(Modalidade modalidade) {
    this.modalidade = modalidade;
  }
}
