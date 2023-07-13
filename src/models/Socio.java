package models;

import java.util.Date;
import java.util.List;

public class Socio {
    private int idsocio;
    private String socio_nome;
    private Date socio_data_admissao;
    private String socio_num_identidade;
    private Date socio_data_nacimento;
    private String socio_telefone;
    private String socio_endereco_rua;
    private String socio_endereco_cidade;
    private String socio_endereco_codigo_postal;
    private List<InscreveSe> inscricoes;
    private Modalidade modalidade;
    private InscreveSe inscreveSe;

    
    public Modalidade getModalidade() {
        return modalidade;
    }
    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }
    public InscreveSe getInscreveSe() {
        return inscreveSe;
    }
    public void setInscreveSe(InscreveSe inscreveSe) {
        this.inscreveSe = inscreveSe;
    }
    public int getIdsocio() {
        return idsocio;
    }
    public void setIdsocio(int idsocio) {
        this.idsocio = idsocio;
    }
    public String getSocio_nome() {
        return socio_nome;
    }
    public void setSocio_nome(String socio_nome) {
        this.socio_nome = socio_nome;
    }
    public Date getSocio_data_admissao() {
        return socio_data_admissao;
    }
    public void setSocio_data_admissao(Date socio_data_admissao) {
        this.socio_data_admissao = socio_data_admissao;
    }
    public String getSocio_num_identidade() {
        return socio_num_identidade;
    }
    public void setSocio_num_identidade(String socio_num_identidade) {
        this.socio_num_identidade = socio_num_identidade;
    }
    public Date getSocio_data_nacimento() {
        return socio_data_nacimento;
    }
    public void setSocio_data_nacimento(Date socio_data_nacimento) {
        this.socio_data_nacimento = socio_data_nacimento;
    }
    public String getSocio_telefone() {
        return socio_telefone;
    }
    public void setSocio_telefone(String socio_telefone) {
        this.socio_telefone = socio_telefone;
    }
    public String getSocio_endereco_rua() {
        return socio_endereco_rua;
    }
    public void setSocio_endereco_rua(String socio_endereco_rua) {
        this.socio_endereco_rua = socio_endereco_rua;
    }
    public String getSocio_endereco_cidade() {
        return socio_endereco_cidade;
    }
    public void setSocio_endereco_cidade(String socio_endereco_cidade) {
        this.socio_endereco_cidade = socio_endereco_cidade;
    }
    public String getSocio_endereco_codigo_postal() {
        return socio_endereco_codigo_postal;
    }
    public void setSocio_endereco_codigo_postal(String socio_endereco_codigo_postal) {
        this.socio_endereco_codigo_postal = socio_endereco_codigo_postal;
    }
    public List<InscreveSe> getInscricoes() {
        return inscricoes;
    }
    public void setInscricoes(List<InscreveSe> inscricoes) {
        this.inscricoes = inscricoes;
    }
}


